package com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.dto;

import lombok.Data;

@Data
public class PgResponse {
    private Long id;
    private Long ownerId;
    private String name;
    private String city;
}
