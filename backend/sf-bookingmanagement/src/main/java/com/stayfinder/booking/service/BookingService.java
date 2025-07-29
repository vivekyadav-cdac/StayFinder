package com.stayfinder.booking.service;

import java.util.List;

import com.stayfinder.booking.dto.BookingResponse;
import com.stayfinder.booking.dto.CreateBookingRequest;

public interface BookingService {
    BookingResponse bookRoom(CreateBookingRequest request);
    void cancelBooking(Long bookingId);
    List<BookingResponse> getBookingsByTenantId(Long tenantId);
}
