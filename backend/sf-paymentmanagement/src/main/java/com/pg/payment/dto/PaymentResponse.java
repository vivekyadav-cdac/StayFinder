package com.pg.payment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private Integer paymentId;
    private BigDecimal amount;
    private String paymentStatus;
    private String method;
    private LocalDateTime paidAt;

    private Integer bookingId;
    private String roomNumber;
    private BigDecimal rent;

    private String tenantName;
    private String tenantEmail;

    private String pgName;
}
