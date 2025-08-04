package com.pg.payment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethodEnum method;

    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum status;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

	public enum PaymentMethodEnum {
        CASH, CREDIT_CARD, ONLINE
    }

    public enum PaymentStatusEnum {
        PENDING, COMPLETED, FAILED
    }
}