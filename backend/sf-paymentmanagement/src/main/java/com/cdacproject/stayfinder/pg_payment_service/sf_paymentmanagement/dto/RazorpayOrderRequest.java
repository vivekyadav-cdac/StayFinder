package com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RazorpayOrderRequest {
    private Long bookingId;
    private Double amount;
}
