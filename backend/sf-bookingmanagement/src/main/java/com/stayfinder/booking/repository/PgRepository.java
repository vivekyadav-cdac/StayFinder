package com.stayfinder.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stayfinder.booking.entity.Pg;
import com.stayfinder.booking.entity.PgType;

@Repository
public interface PgRepository extends JpaRepository<Pg, Long> {
	 // Search PGs by city
    List<Pg> findByCityIgnoreCase(String city);

    // Search PGs by type (BOYS, GIRLS, UNISEX)
    List<Pg> findByType(PgType type);

    // Optional: Search by location and type
    List<Pg> findByCityIgnoreCaseAndType(String city, PgType type);
}
