package com.stayfinder.booking.service.impl;

import com.stayfinder.booking.client.PgServiceClient;
import com.stayfinder.booking.dto.BookingResponse;
import com.stayfinder.booking.dto.CreateBookingRequest;
import com.stayfinder.booking.entity.Booking;
import com.stayfinder.booking.entity.BookingStatus;
import com.stayfinder.booking.exception.ResourceNotFoundException;
import com.stayfinder.booking.repository.BookingRepository;
import com.stayfinder.booking.service.BookingService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final PgServiceClient pgServiceClient;

    @Override
    public BookingResponse createBooking(CreateBookingRequest request, Integer tenantId) {
        Booking booking = Booking.builder()
                .roomId(request.getRoomId())
                .tenantId(tenantId)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .status(BookingStatus.BOOKED)
                .build();

        Booking saved = bookingRepository.save(booking);
        pgServiceClient.markRoomAsBooked(request.getRoomId());

        return mapToResponse(saved);
    }

    @Override
    public void cancelBooking(Integer bookingId, Integer tenantId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id " + bookingId));
        if (!booking.getTenantId().equals(tenantId)) {
            throw new RuntimeException("You can only cancel your own bookings.");
        }
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
        pgServiceClient.markRoomAsAvailable(booking.getRoomId());
    }

    @Override
    public List<BookingResponse> getBookingsByTenant(Integer tenantId) {
        return bookingRepository.findByTenantId(tenantId)
                .stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private BookingResponse mapToResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .roomId(booking.getRoomId())
                .tenantId(booking.getTenantId())
                .status(booking.getStatus())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .createdAt(booking.getCreatedAt())
                .build();
    }
}
