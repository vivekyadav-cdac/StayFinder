package com.cdacproject.stayfinder.pg_property_service.controller;

import com.cdacproject.stayfinder.pg_property_service.dto.PGDto;
import com.cdacproject.stayfinder.pg_property_service.dto.PGResponseDto;
import com.cdacproject.stayfinder.pg_property_service.model.PG;
import com.cdacproject.stayfinder.pg_property_service.service.FileStorageService;
import com.cdacproject.stayfinder.pg_property_service.service.PGService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/pgs")
public class PGController {

    @Autowired private PGService pgService;
    @Autowired private FileStorageService fileStorage;

    /**
     * Create PG with optional image upload (OWNER only)
     */
    @PreAuthorize("hasRole('OWNER')")
    @PostMapping(consumes = {"multipart/form-data"})
    public PGResponseDto create(@RequestPart("pg") @Valid PGDto pgDto,
                                @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        PG pg = new PG();
        pg.setName(pgDto.getName());
        pg.setType(pgDto.getType());
        pg.setAddress(pgDto.getAddress());
        pg.setCity(pgDto.getCity());
        pg.setState(pgDto.getState());
        pg.setPin(pgDto.getPin());
        pg.setContact(pgDto.getContact());
        pg.setOwnerId(pgDto.getOwnerId());

        if (image != null) {
            String url = fileStorage.saveFile(image);
            pg.setImageUrl(url);
        }

        PG saved = pgService.createPG(pg);
        return pgService.toResponseDto(saved); 
    }

    /**
     * Get all PGs with pagination & optional city filter (OWNER & USER)
     */
    @PreAuthorize("hasAnyRole('OWNER','USER')")
    @GetMapping
    public Page<PGResponseDto> getAll(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(required = false) String city) {

        PageRequest pageable = PageRequest.of(page, size);
        return pgService.getAllPGs(city, pageable); 
    }

    /**
     * Get PG by ID (OWNER & USER)
     */
    @PreAuthorize("hasAnyRole('OWNER','USER')")
    @GetMapping("/{id}")
    public PGResponseDto getById(@PathVariable Long id) {
        PG pg = pgService.getPGById(id)
                .orElseThrow(() -> new RuntimeException("PG not found"));
        return pgService.toResponseDto(pg); 
    }

    /**
     * Delete PG (OWNER only)
     */
    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pgService.deletePG(id);
    }
}
