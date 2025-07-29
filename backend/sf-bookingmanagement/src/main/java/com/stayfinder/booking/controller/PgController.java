package com.stayfinder.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stayfinder.booking.dto.PgDetailsResponse;
import com.stayfinder.booking.dto.PgSummaryResponse;
import com.stayfinder.booking.dto.SearchPgRequest;
import com.stayfinder.booking.entity.PgType;
import com.stayfinder.booking.service.PgService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pgs")
public class PgController {

    @Autowired
    private PgService pgService;

    // GET /pgs/search?location=Pune&gender=MALE&budget=6000
    @GetMapping("/search")
    public List<PgSummaryResponse> searchPgs(
            @RequestParam String location,
            @RequestParam String gender,
            @RequestParam Double budget
    ) {
        SearchPgRequest request = new SearchPgRequest();
        request.setCity(location);
        request.setType(PgType.valueOf(gender.toUpperCase())); // Important
        request.setMinRent(0.0); // Optional: default min
        request.setMaxRent(budget);

        return pgService.searchPgs(request);
    }

    // GET /pgs/{id}
    @GetMapping("/{id}")
    public PgDetailsResponse getPgDetails(@PathVariable Long id) {
        return pgService.getPgDetailsWithRooms(id);
    }
}
