package com.cdacproject.stayfinder.pg_property_service.controller;

import com.cdacproject.stayfinder.pg_property_service.dto.RoomDto;
import com.cdacproject.stayfinder.pg_property_service.dto.RoomResponseDto;
import com.cdacproject.stayfinder.pg_property_service.exception.ErrorResponse;
import com.cdacproject.stayfinder.pg_property_service.exception.ResourceNotFoundException;
import com.cdacproject.stayfinder.pg_property_service.mapper.RoomMapper;
import com.cdacproject.stayfinder.pg_property_service.model.Room;
import com.cdacproject.stayfinder.pg_property_service.service.FileStorageService;
import com.cdacproject.stayfinder.pg_property_service.service.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/pgs/{pgId}/rooms")
public class RoomController {

    private final RoomService roomService;
    private final RoomMapper roomMapper;
    private final FileStorageService fileStorage;

    @Value("${gateway.base-url}")
    private String gatewayUrl;

    private static final Logger log = LoggerFactory.getLogger(RoomController.class);

    public RoomController(RoomService roomService, RoomMapper roomMapper, FileStorageService fileStorage) {
        this.roomService = roomService;
        this.roomMapper = roomMapper;
        this.fileStorage = fileStorage;
    }

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> addRoom(@PathVariable Long pgId,
                                     @Valid @RequestPart("room") RoomDto roomDto,
                                     @RequestPart(value = "image", required = false) MultipartFile image,
                                     HttpServletRequest request) throws IOException {

        String userIdHeader = request.getHeader("X-User-Id");
        if (userIdHeader == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(401, "Missing X-User-Id header", request.getRequestURI()));
        }
        Long ownerId = Long.valueOf(userIdHeader);

        Room room = roomMapper.toEntity(roomDto);

        if (image != null) {
            String filename = fileStorage.saveFile(image);
            room.setImageUrl(filename);
        }

        Room saved = roomService.addRoom(pgId, ownerId, room);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomMapper.toResponseDto(saved, gatewayUrl));
    }

    @PreAuthorize("hasAnyRole('OWNER','USER')")
    @GetMapping
    public ResponseEntity<Page<RoomResponseDto>> getRooms(@PathVariable Long pgId,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size,
                                                          @RequestParam(required = false) Boolean available,
                                                          @RequestParam(required = false) Double minPrice,
                                                          @RequestParam(required = false) Double maxPrice) {
        Pageable pageable = PageRequest.of(page, size);

        Page<RoomResponseDto> rooms = roomService.getRoomsByPG(pgId, available, minPrice, maxPrice, pageable)
                .map(room -> roomMapper.toResponseDto(room, gatewayUrl));

        return ResponseEntity.ok(rooms);
    }

    @PreAuthorize("hasAnyRole('OWNER','USER')")
    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResponseDto> getRoom(@PathVariable Long roomId) {
        Room room = roomService.getRoomById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + roomId));
        return ResponseEntity.ok(roomMapper.toResponseDto(room, gatewayUrl));
    }

    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/{roomId}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long roomId, HttpServletRequest request) {
        String userIdHeader = request.getHeader("X-User-Id");
        if (userIdHeader == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(401, "Missing X-User-Id header", request.getRequestURI()));
        }
        Long ownerId = Long.valueOf(userIdHeader);

        roomService.deleteRoom(roomId, ownerId);
        return ResponseEntity.noContent().build();
    }
}
