package com.stayfinder.booking.service;

import com.stayfinder.booking.dto.BookingResponse;
import com.stayfinder.booking.dto.CreateBookingRequest;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(CreateBookingRequest request, Integer tenantId);
    void cancelBooking(Integer bookingId, Integer tenantId);
    List<BookingResponse> getBookingsByTenant(Integer tenantId);
}
