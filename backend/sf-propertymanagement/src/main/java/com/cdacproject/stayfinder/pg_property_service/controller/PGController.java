package com.cdacproject.stayfinder.pg_property_service.controller;

import com.cdacproject.stayfinder.pg_property_service.dto.PGDto;
import com.cdacproject.stayfinder.pg_property_service.dto.PGResponseDto;
import com.cdacproject.stayfinder.pg_property_service.exception.ErrorResponse;
import com.cdacproject.stayfinder.pg_property_service.exception.ResourceNotFoundException;
import com.cdacproject.stayfinder.pg_property_service.mapper.PGMapper;
import com.cdacproject.stayfinder.pg_property_service.model.PG;
import com.cdacproject.stayfinder.pg_property_service.service.FileStorageService;
import com.cdacproject.stayfinder.pg_property_service.service.PGService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/pgs")
public class PGController {

    private final PGService pgService;
    private final FileStorageService fileStorage;
    private final PGMapper pgMapper;

    @Value("${gateway.base-url}")
    private String gatewayUrl;

    private static final Logger log = LoggerFactory.getLogger(PGController.class);

    public PGController(PGService pgService, FileStorageService fileStorage, PGMapper pgMapper) {
        this.pgService = pgService;
        this.fileStorage = fileStorage;
        this.pgMapper = pgMapper;
    }

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> create(@RequestPart("pg") @Valid PGDto pgDto,
                                    @RequestPart(value = "image", required = false) MultipartFile image,
                                    HttpServletRequest request) throws IOException {

        String userIdHeader = request.getHeader("X-User-Id");
        if (userIdHeader == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(401, "Missing user ID header", request.getRequestURI()));
        }

        Long ownerId = Long.valueOf(userIdHeader);
        log.info("Create PG requested by User ID: {}", ownerId);

        PG pg = pgMapper.toEntity(pgDto);


        pg.setOwnerId(ownerId);

        if (image != null) {
            String filename = fileStorage.saveFile(image);
            pg.setImageUrl(filename);
        }

        PG saved = pgService.createPG(pg, ownerId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pgMapper.toResponseDto(saved, gatewayUrl));
    }


    @PreAuthorize("hasAnyRole('OWNER','USER')")
    @GetMapping
    public ResponseEntity<Page<PGResponseDto>> getAll(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestParam(required = false) String city) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<PGResponseDto> result = pgService.getAllPGs(city, pageable)
                .map(pg -> pgMapper.toResponseDto(pg, gatewayUrl));
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('OWNER','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<PGResponseDto> getById(@PathVariable Long id) {
        PG pg = pgService.getPGById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PG not found with id: " + id));
        return ResponseEntity.ok(pgMapper.toResponseDto(pg, gatewayUrl));
    }

    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, HttpServletRequest request) {
        String userIdHeader = request.getHeader("X-User-Id");
        if (userIdHeader == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(401, "Missing user ID header", request.getRequestURI()));
        }
        Long ownerId = Long.valueOf(userIdHeader);

        log.warn("User {} requested delete PG with ID {}", ownerId, id);
        pgService.deletePG(id, ownerId);
        return ResponseEntity.noContent().build();
    }
}
