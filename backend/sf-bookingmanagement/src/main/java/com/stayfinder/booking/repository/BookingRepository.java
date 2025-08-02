package com.stayfinder.booking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stayfinder.booking.entity.Booking;
import com.stayfinder.booking.entity.BookingStatus;
import com.stayfinder.booking.entity.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
	// Find all bookings for a specific tenant
    List<Booking> findByTenant(User tenant);

    // Optional: Find bookings by tenant ID
    List<Booking> findByTenantId(Long tenantId);

    // Optional: Filter by tenant and status
    List<Booking> findByTenantIdAndStatus(Long tenantId, BookingStatus status);
    
    // Find is room available for booking
    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId AND b.status = 'BOOKED' AND " + "(:startDate < b.endDate AND :endDate > b.startDate)")
    List<Booking> findOverlappingBookings(Long roomId, LocalDate startDate, LocalDate endDate);

}
