package com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreatePaymentRequest {

    @NotNull
    private Long bookingId;

    @NotNull
    @Positive
    private Double amount;

    @NotBlank
    private String paymentMethod; // UPI, CARD, etc.

}
