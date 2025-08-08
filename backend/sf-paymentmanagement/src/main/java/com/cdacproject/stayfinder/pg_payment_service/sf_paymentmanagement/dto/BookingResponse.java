package com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.dto;

import lombok.Data;

@Data
public class BookingResponse {
    private Long id;
    private Long pgId;
    private Long tenantId;
    private Double amount;
    private String status; // Or BookingStatus enum if known
}
