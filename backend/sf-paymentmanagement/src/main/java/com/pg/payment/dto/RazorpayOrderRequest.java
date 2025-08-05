package com.pg.payment.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class RazorpayOrderRequest {
    private Integer bookingId;
    private BigDecimal amount;
}

