package com.pg.payment.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.pg.payment.model.Payment.PaymentMethodEnum;

public class PaymentRequest {
	  private Integer bookingId;
	  private BigDecimal amount;
	  private PaymentMethodEnum method;
	  
	  public PaymentRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	  
	  public PaymentRequest(Integer bookingId, BigDecimal amount, PaymentMethodEnum method) {
		super();
		this.bookingId = bookingId;
		this.amount = amount;
		this.method = method;
	}

	  public Integer getBookingId() {
		  return bookingId;
	  }
	  public void setBookingId(Integer bookingId) {
		  this.bookingId = bookingId;
	  }
	  public BigDecimal getAmount() {
		  return amount;
	  }
	  public void setAmount(BigDecimal amount) {
		  this.amount = amount;
	  }
	  public PaymentMethodEnum getMethod() {
		  return method;
	  }
	  public void setMethod(PaymentMethodEnum method) {
		  this.method = method;
	  }

	  @Override
	  public String toString() {
		return "PaymentRequest [bookingId=" + bookingId + ", amount=" + amount + ", method=" + method + "]";
	  } 
	  
}
