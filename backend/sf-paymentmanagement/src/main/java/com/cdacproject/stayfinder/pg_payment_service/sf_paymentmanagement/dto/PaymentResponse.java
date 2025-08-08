package com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.dto;

import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.enums.PaymentMethodEnum;
import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.enums.PaymentStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PaymentResponse {

    private Long id;
    private Long bookingId;
    private Long tenantId;
    private Long ownerId;
    private Double amount;
    private PaymentMethodEnum paymentMethod;
    private PaymentStatusEnum status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
