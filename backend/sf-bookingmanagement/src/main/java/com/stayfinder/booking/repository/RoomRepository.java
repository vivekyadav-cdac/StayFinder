package com.stayfinder.booking.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stayfinder.booking.entity.Pg;
import com.stayfinder.booking.entity.PgType;
import com.stayfinder.booking.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	// Find rooms by PG ID
    List<Room> findByPgId(Long pgId );

    // Find available rooms only
    List<Room> findByPgIdAndIsAvailableTrue(Long pgId);
    
    // Find available room by budget
    @Query("SELECT DISTINCT r.pg FROM Room r WHERE r.pg.city = :city AND r.pg.type = :type AND r.rent BETWEEN :minRent AND :maxRent")
    List<Pg> searchPgByBudget(@Param("city") String city,
            @Param("type") PgType type,
            @Param("minRent") BigDecimal minRent,
            @Param("maxRent") BigDecimal maxRent);
}
