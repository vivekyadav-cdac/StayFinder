package com.cdacproject.stayfinder.pg_property_service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cdacproject.stayfinder.pg_property_service.model.PG;

public interface PGRepository extends JpaRepository<PG, Long> {
    Page<PG> findByCity(String city, Pageable pageable);
    List<PG> findByOwnerId(Long ownerId);
}
