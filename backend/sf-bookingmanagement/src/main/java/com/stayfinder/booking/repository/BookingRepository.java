package com.stayfinder.booking.repository;

import com.stayfinder.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByTenantId(Integer tenantId);
}
