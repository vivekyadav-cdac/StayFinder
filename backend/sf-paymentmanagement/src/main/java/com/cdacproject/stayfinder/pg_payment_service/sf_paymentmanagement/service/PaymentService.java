package com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.service;

import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.dto.CreatePaymentRequest;
import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.dto.PaymentResponse;

import java.util.List;

public interface PaymentService {
    PaymentResponse createPayment(CreatePaymentRequest request, Long tenantId);
    PaymentResponse getPaymentById(Long id);
    List<PaymentResponse> getPaymentsByTenant(Long tenantId);
    List<PaymentResponse> getPaymentsByOwner(Long ownerId);
}
