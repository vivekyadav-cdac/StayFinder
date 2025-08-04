package com.cdacproject.stayfinder.pg_property_service.service;

import com.cdacproject.stayfinder.pg_property_service.dto.RoomResponseDto;
import com.cdacproject.stayfinder.pg_property_service.exception.ResourceNotFoundException;
import com.cdacproject.stayfinder.pg_property_service.model.PG;
import com.cdacproject.stayfinder.pg_property_service.model.Room;
import com.cdacproject.stayfinder.pg_property_service.repository.PGRepository;
import com.cdacproject.stayfinder.pg_property_service.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PGRepository pgRepository;

    public RoomResponseDto addRoom(Long pgId, Room room) {
        PG pg = pgRepository.findById(pgId)
                .orElseThrow(() -> new ResourceNotFoundException("PG not found with id: " + pgId));
        room.setPg(pg);
        Room saved = roomRepository.save(room);
        return toResponseDto(saved);
    }

    public Page<RoomResponseDto> getRoomsByPG(Long pgId, Pageable pageable) {
        return roomRepository.findByPgId(pgId, pageable)
                .map(this::toResponseDto);
    }

    public Optional<RoomResponseDto> getRoomById(Long id) {
        return roomRepository.findById(id).map(this::toResponseDto);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    // Entity to DTO mapper
    public RoomResponseDto toResponseDto(Room room) {
        RoomResponseDto dto = new RoomResponseDto();
        dto.setId(room.getId());
        dto.setNumber(room.getNumber());
        dto.setType(room.getType());
        dto.setRent(room.getRent());
        dto.setAvailable(room.isAvailable());
        dto.setImageUrl(room.getImageUrl());
        return dto;
    }
}
