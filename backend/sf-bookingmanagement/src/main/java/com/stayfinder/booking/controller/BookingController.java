package com.stayfinder.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stayfinder.booking.dto.BookingResponse;
import com.stayfinder.booking.dto.CreateBookingRequest;
import com.stayfinder.booking.service.BookingService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // POST /bookings
    @PostMapping
    public BookingResponse createBooking(@RequestBody CreateBookingRequest request) {
        return bookingService.bookRoom(request);
    }

    // GET /bookings/tenant/{tenantId}
    @GetMapping("/tenant/{tenantId}")
    public List<BookingResponse> getTenantBookings(@PathVariable Long tenantId) {
        return bookingService.getBookingsByTenantId(tenantId);
    }

    // DELETE /bookings/{id}
    @DeleteMapping("/{id}")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return "Booking cancelled successfully.";
    }
}
