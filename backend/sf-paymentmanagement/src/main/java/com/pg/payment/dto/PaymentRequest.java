package com.pg.payment.dto;

import java.math.BigDecimal;
import com.pg.payment.model.Payment.PaymentMethodEnum;
import lombok.Data;

@Data
public class PaymentRequest {
	private Integer bookingId;
	private BigDecimal amount;
	private PaymentMethodEnum method;

}
