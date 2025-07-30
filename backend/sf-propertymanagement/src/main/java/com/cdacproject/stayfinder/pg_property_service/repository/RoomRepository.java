package com.cdacproject.stayfinder.pg_property_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cdacproject.stayfinder.pg_property_service.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Page<Room> findByPgId(Long pgId, Pageable pageable);
}
