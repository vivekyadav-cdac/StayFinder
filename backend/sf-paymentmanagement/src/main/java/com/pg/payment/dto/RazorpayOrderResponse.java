package com.pg.payment.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RazorpayOrderResponse {
    private String orderId;
    private String key;
    private BigDecimal amount;
}

