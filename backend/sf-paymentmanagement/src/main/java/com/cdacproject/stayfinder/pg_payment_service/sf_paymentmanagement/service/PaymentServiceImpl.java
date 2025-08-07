package com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.service.impl;

import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.dto.*;
import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.entity.Payment;
import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.enums.PaymentMethodEnum;
import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.enums.PaymentStatusEnum;
import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.exception.ResourceNotFoundException;
import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.feign.BookingServiceClient;
import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.feign.PGPropertyServiceClient;
import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.repository.PaymentRepository;
import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingServiceClient bookingClient;
    private final PGPropertyServiceClient pgClient;

    @Override
    public PaymentResponse createPayment(CreatePaymentRequest request, Long tenantId) {
        // 1. Validate booking via Feign
        BookingResponse booking = bookingClient.getBookingById(request.getBookingId());

        if (booking == null) {
            throw new ResourceNotFoundException("Booking not found with ID: " + request.getBookingId());
        }

        // 2. Ensure current tenant owns the booking
        if (!booking.getTenantId().equals(tenantId)) {
            throw new SecurityException("You are not authorized to pay for this booking");
        }
        System.out.println("booking id"+request.getBookingId());
        // 3. Get PG Owner from Property service

        PGResponseDto pg = pgClient.getById(booking.getPgId()).getBody();
System.out.println("pg owner id "+pg.getOwnerId());
        if (pg == null) {
            throw new ResourceNotFoundException("PG not found for booking");
        }

        // 4. Create payment entity
        Payment payment = Payment.builder()
                .bookingId(request.getBookingId())
                .tenantId(tenantId)
                .ownerId(pg.getOwnerId())
                .amount(request.getAmount())
                .paymentMethod(PaymentMethodEnum.valueOf(request.getPaymentMethod()))
                .createdAt(LocalDateTime.now())
                .status(PaymentStatusEnum.SUCCESS) // later you can simulate status or integrate with Razorpay etc.
                .build();

        Payment saved = paymentRepository.save(payment);

        return mapToResponse(saved);
    }

    @Override
    public PaymentResponse getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with ID: " + id));
        return mapToResponse(payment);
    }

    @Override
    public List<PaymentResponse> getPaymentsByTenant(Long tenantId) {
        return paymentRepository.findByTenantId(tenantId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentResponse> getPaymentsByOwner(Long ownerId) {
        return paymentRepository.findByOwnerId(ownerId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private PaymentResponse mapToResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .bookingId(payment.getBookingId())
                .tenantId(payment.getTenantId())
                .ownerId(payment.getOwnerId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .status(payment.getStatus())
                .createdAt(payment.getCreatedAt())
                .updatedAt(payment.getUpdatedAt())
                .build();
    }
}
