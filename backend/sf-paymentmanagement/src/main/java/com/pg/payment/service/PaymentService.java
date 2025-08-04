package com.pg.payment.service;

import com.pg.payment.dto.PaymentRequest;
import com.pg.payment.model.Booking;
import com.pg.payment.model.Payment;
import com.pg.payment.model.Room;
import com.pg.payment.model.Payment.PaymentStatusEnum;
import com.pg.payment.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repo;

    // Using mocked data now; in real usage, uncomment this and remove hardcoded block
//    @Autowired
//    private BookingClient bookingClient;

    public List<Payment> getAllHistory() {
        return repo.findAll();
    }

    public Payment processPayment(PaymentRequest request, Integer tenantId) {
        // === Hardcoded Booking object for mocking ===
        Booking booking = new Booking();
        booking.setBookingId(request.getBookingId());
        booking.setTenant_id(tenantId);

        Room room = new Room();
        room.setRent(5000.0);  // Assume fixed rent

        booking.setRoom(room);

        // === Real implementation with FeignClient ===
//        Booking booking = bookingClient.getBookingById(request.getBookingId());
//
//        if (booking == null) {
//            throw new RuntimeException("Booking not found.");
//        }
//
//        if (!booking.getTenant_id().equals(tenantId)) {
//            throw new RuntimeException("Unauthorized: You cannot pay for another tenant's booking.");
//        }

        BigDecimal rent = BigDecimal.valueOf(room.getRent());

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(rent);
        payment.setMethod(request.getMethod());
        payment.setStatus(PaymentStatusEnum.COMPLETED);
        payment.setCreatedAt(LocalDateTime.now());

        return repo.save(payment);
    }

    public List<Payment> getHistoryByBookingId(Integer bookingId) {
        return repo.findByBookingId(bookingId);
    }

    public void deletePayment(Integer paymentId) {
        if (!repo.existsById(paymentId)) {
            throw new RuntimeException("Payment ID not found: " + paymentId);
        }
        repo.deleteById(paymentId);
    }

    public List<Payment> getPaymentsByTenant(Integer tenantId) {
        return repo.findByTenantId(tenantId);
    }

    public List<Payment> getPaymentsByOwner(Integer ownerId) {
        return repo.findByOwnerId(ownerId);
    }
}
