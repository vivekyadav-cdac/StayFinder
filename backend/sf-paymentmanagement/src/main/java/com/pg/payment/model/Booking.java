package com.pg.payment.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer bookingId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private Integer tenant_id;

    @Enumerated(EnumType.STRING)
    private BookingStatusEnum status;

    private LocalDate start_date;
    private LocalDate end_date;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    
    public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
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
}