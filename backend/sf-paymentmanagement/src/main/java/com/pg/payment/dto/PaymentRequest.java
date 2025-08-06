package com.pg.payment.dto;

import java.math.BigDecimal;

import com.pg.payment.model.PaymentMethodEnum;

import lombok.Data;

@Data
public class PaymentRequest {
	private Long bookingId;
	private BigDecimal amount;
	private PaymentMethodEnum method;
}
