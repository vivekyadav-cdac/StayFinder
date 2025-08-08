package com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RazorpayOrderResponse {
    private String orderId;
    private String key;
    private BigDecimal amount;
}
