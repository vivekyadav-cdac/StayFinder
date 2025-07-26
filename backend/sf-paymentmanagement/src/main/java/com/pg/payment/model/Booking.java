package com.pg.payment.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {
	@Id
	@Column(name = "id")
	private Integer bookingId;
	
	private Integer room_id;
	private Integer tenant_id;
	
	@Enumerated(EnumType.STRING)
	  private BookingStatusEnum status;
	
	private LocalDate start_date;
	private LocalDate end_date;
	
	@Column(name = "created_at")
	  private LocalDateTime createdAt;
	
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Booking(Integer bookingId, Integer room_id, Integer tenant_id, BookingStatusEnum status, LocalDate start_date,
			LocalDate end_date, LocalDateTime createdAt) {
		super();
		this.bookingId = bookingId;
		this.room_id = room_id;
		this.tenant_id = tenant_id;
		this.status = status;
		this.start_date = start_date;
		this.end_date = end_date;
		this.createdAt = createdAt;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Integer getRoom_id() {
		return room_id;
	}

	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}

	public Integer getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(Integer tenant_id) {
		this.tenant_id = tenant_id;
	}

	public BookingStatusEnum getStatus() {
		return status;
	}

	public void setStatus(BookingStatusEnum status) {
		this.status = status;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public enum BookingStatusEnum {
	    PENDING, COMPLETED, FAILED
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", room_id=" + room_id + ", tenant_id=" + tenant_id + ", status=" + status
				+ ", start_date=" + start_date + ", end_date=" + end_date + ", createdAt=" + createdAt + "]";
	}

}
