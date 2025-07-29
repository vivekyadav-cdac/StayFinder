package com.stayfinder.booking.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pgs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pg {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key to User (Owner)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    private String name;
    private String address;
    private String city;
    private String state;

    @Enumerated(EnumType.STRING)
    private PgType type;

    private String contact;
    private Double latitude;
    private Double longitude;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // One PG can have many Rooms
    @OneToMany(mappedBy = "pg", cascade = CascadeType.ALL)
    private List<Room> rooms;
}
