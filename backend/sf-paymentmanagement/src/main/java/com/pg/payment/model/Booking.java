package com.pg.payment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

	public enum BookingStatusEnum {
        PENDING, COMPLETED, FAILED
    }
}