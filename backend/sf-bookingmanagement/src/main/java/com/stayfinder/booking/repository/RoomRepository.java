package com.stayfinder.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stayfinder.booking.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	// Find rooms by PG ID
    List<Room> findByPgId(Long pgId );

    // Find available rooms only
    List<Room> findByPgIdAndIsAvailableTrue(Long pgId);
}
