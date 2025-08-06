package com.pg.payment.dto;
import java.time.LocalDateTime;

import com.pg.payment.model.PaymentMethodEnum;
import com.pg.payment.model.PaymentStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private Long id;
    private double amount;
    private PaymentMethodEnum method;
    private PaymentStatusEnum status;
    private LocalDateTime createdAt;
}
