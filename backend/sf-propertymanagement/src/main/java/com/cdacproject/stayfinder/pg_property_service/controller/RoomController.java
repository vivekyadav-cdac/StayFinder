package com.cdacproject.stayfinder.pg_property_service.controller;

import com.cdacproject.stayfinder.pg_property_service.dto.RoomDto;
import com.cdacproject.stayfinder.pg_property_service.dto.RoomResponseDto;
import com.cdacproject.stayfinder.pg_property_service.model.Room;
import com.cdacproject.stayfinder.pg_property_service.service.FileStorageService;
import com.cdacproject.stayfinder.pg_property_service.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/pgs/{pgId}/rooms")
public class RoomController {

    @Autowired private RoomService roomService;
    @Autowired private FileStorageService fileStorage;

    /**
     * Add room with optional image (OWNER only)
     */
    @PreAuthorize("hasRole('OWNER')")
    @PostMapping(consumes = {"multipart/form-data"})
    public RoomResponseDto addRoom(@PathVariable Long pgId,
                                   @RequestPart("room") @Valid RoomDto roomDto,
                                   @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        Room room = new Room();
        room.setNumber(roomDto.getNumber());
        room.setType(roomDto.getType());
        room.setRent(roomDto.getRent());
        room.setAvailable(roomDto.isAvailable());

        if (image != null) {
            String url = fileStorage.saveFile(image);
            room.setImageUrl(url);
        }

        return roomService.addRoom(pgId, room);
    }

    /**
     * Get all rooms for a PG with pagination (OWNER & USER)
     */
    @PreAuthorize("hasAnyRole('OWNER','USER')")
    @GetMapping
    public Page<RoomResponseDto> getRooms(@PathVariable Long pgId,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return roomService.getRoomsByPG(pgId, pageable);
    }

    /**
     * Get single room by ID (OWNER & USER)
     */
    @PreAuthorize("hasAnyRole('OWNER','USER')")
    @GetMapping("/{roomId}")
    public RoomResponseDto getRoom(@PathVariable Long roomId) {
        return roomService.getRoomById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    /**
     * Delete room (OWNER only)
     */
    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/{roomId}")
    public void deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
    }
}
