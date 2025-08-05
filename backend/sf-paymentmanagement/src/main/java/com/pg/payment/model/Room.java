package com.pg.payment.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pg_id")
    private PG pg;
    
    private String number;
    private String type;
    private BigDecimal rent;
    @Column(name = "is_available")
    private boolean available;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}