package com.pg.payment.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "id")
	  private Integer paymentId;

	  @Column(name = "booking_id")
	  private Integer bookingId;

	  private BigDecimal amount;
//	  private LocalDate date;

	  @Enumerated(EnumType.STRING)
	  private PaymentMethodEnum method;

	  @Enumerated(EnumType.STRING)
	  private PaymentStatusEnum status;

	  @Column(name = "created_at")
	  private LocalDateTime createdAt;
	  
	  
	  
	  public Payment() {
		super();
		// TODO Auto-generated constructor stub
	  }
	  
	  public Payment(Integer paymentId, Integer bookingId, BigDecimal amount, PaymentMethodEnum method,
			PaymentStatusEnum status, LocalDateTime createdAt) {
		super();
		this.paymentId = paymentId;
		this.bookingId = bookingId;
		this.amount = amount;
//		this.date = date;
		this.method = method;
		this.status = status;
		this.createdAt = createdAt;
	  }
	  
	  public Integer getPaymentId() {
		return paymentId;
	}

	  public void setPaymentId(Integer paymentId) {
		  this.paymentId = paymentId;
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

//	  public LocalDate getDate() {
//		  return date;
//	  }

//	  public void setDate(LocalDate date) {
//		  this.date = date;
//	  }

	  public PaymentMethodEnum getMethod() {
		  return method;
	  }

	  public void setMethod(PaymentMethodEnum method) {
		  this.method = method;
	  }

	  public PaymentStatusEnum getStatus() {
		  return status;
	  }

	  public void setStatus(PaymentStatusEnum status) {
		  this.status = status;
	  }

	  public LocalDateTime getCreatedAt() {
		  return createdAt;
	  }

	  public void setCreatedAt(LocalDateTime createdAt) {
		  this.createdAt = createdAt;
	  }


	  public enum PaymentMethodEnum {
		    CASH, CREDIT_CARD, ONLINE
		}

		public enum PaymentStatusEnum {
		    PENDING, COMPLETED, FAILED
		}

		@Override
		public String toString() {
			return "Payment [paymentId=" + paymentId + ", bookingId=" + bookingId + ", amount=" + amount
					+ ", method=" + method + ", status=" + status + ", createdAt=" + createdAt + "]";
		}
		
}
