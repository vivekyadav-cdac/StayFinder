package com.stayfinder.booking.entity;

import java.math.BigDecimal;
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
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    // Foreign key to PG
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "pg_id")
	    private Pg pg;

	    private String number;

	    @Enumerated(EnumType.STRING)
	    private RoomType type;

	    private BigDecimal rent;

	    @Column(name = "is_available")
	    private boolean isAvailable;

	    @Column(name = "created_at")
	    private LocalDateTime createdAt = LocalDateTime.now();

	    // One Room can have many Bookings
	    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	    private List<Booking> bookings;
	
	
}
