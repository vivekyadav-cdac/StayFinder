package com.stayfinder.booking.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stayfinder.booking.dto.BookingResponse;
import com.stayfinder.booking.dto.CreateBookingRequest;
import com.stayfinder.booking.service.BookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingResponse bookRoom(
            @RequestHeader("X-User-Id") Integer tenantId,
            @Validated @RequestBody CreateBookingRequest request) {
        return bookingService.createBooking(request, tenantId);
    }

    @DeleteMapping("/{bookingId}")
    public void cancelBooking(
            @RequestHeader("X-User-Id") Integer tenantId,
            @PathVariable Integer bookingId) {
        bookingService.cancelBooking(bookingId, tenantId);
    }

    @GetMapping("/my")
    public List<BookingResponse> getMyBookings(
            @RequestHeader("X-User-Id") Integer tenantId) {
        return bookingService.getBookingsByTenant(tenantId);
    }
}
