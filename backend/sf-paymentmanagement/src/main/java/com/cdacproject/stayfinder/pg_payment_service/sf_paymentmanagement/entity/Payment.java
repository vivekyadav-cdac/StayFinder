package com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.entity;


import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.enums.PaymentMethodEnum;
import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.enums.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;

    private Long tenantId;

    private Long ownerId;

    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false)
    private PaymentMethodEnum paymentMethod; // e.g., UPI, CARD, NETBANKING

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatusEnum status;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
