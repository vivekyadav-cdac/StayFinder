package com.pg.payment.model;

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
    private Long id;
    private int pg_id;
    private String number;
    private String type;
    private double rent;
    @Column(name = "is_available")
    private boolean available;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}