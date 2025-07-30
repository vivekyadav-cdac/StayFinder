package com.cdacproject.stayfinder.pg_property_service.service;

import com.cdacproject.stayfinder.pg_property_service.dto.PGResponseDto;
import com.cdacproject.stayfinder.pg_property_service.dto.RoomResponseDto;
import com.cdacproject.stayfinder.pg_property_service.model.PG;
import com.cdacproject.stayfinder.pg_property_service.repository.PGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PGService {

    @Autowired
    private PGRepository pgRepository;

    public PG createPG(PG pg) {
        return pgRepository.save(pg);
    }

    // ✅ Pagination + filtering
    public Page<PGResponseDto> getAllPGs(String city, Pageable pageable) {
        Page<PG> page;
        if (city != null && !city.isEmpty()) {
            page = pgRepository.findByCity(city, pageable);
        } else {
            page = pgRepository.findAll(pageable);
        }
        return page.map(this::toResponseDto);
    }

    public Optional<PG> getPGById(Long id) {
        return pgRepository.findById(id);
    }

    public void deletePG(Long id) {
        pgRepository.deleteById(id);
    }

    public java.util.List<PG> getPGsByOwnerId(Long ownerId) {
        return pgRepository.findByOwnerId(ownerId);
    }

   
    public PGResponseDto toResponseDto(PG pg) {
        PGResponseDto dto = new PGResponseDto();
        dto.setId(pg.getId());
        dto.setName(pg.getName());
        dto.setType(pg.getType());
        dto.setAddress(pg.getAddress());
        dto.setCity(pg.getCity());
        dto.setState(pg.getState());
        dto.setPin(pg.getPin());
        dto.setContact(pg.getContact());
        dto.setOwnerId(pg.getOwnerId());
        dto.setImageUrl(pg.getImageUrl());

        if (pg.getRooms() != null) {
            dto.setRooms(pg.getRooms().stream().map(room -> {
                RoomResponseDto rDto = new RoomResponseDto();
                rDto.setId(room.getId());
                rDto.setNumber(room.getNumber());
                rDto.setType(room.getType());
                rDto.setRent(room.getRent());
                rDto.setAvailable(room.isAvailable());
                rDto.setImageUrl(room.getImageUrl());
                return rDto;
            }).collect(Collectors.toList()));
        }
        return dto;
    }
}
