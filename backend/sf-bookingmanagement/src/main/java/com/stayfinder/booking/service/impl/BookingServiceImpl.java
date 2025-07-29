package com.stayfinder.booking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stayfinder.booking.dto.BookingResponse;
import com.stayfinder.booking.dto.CreateBookingRequest;
import com.stayfinder.booking.entity.Booking;
import com.stayfinder.booking.entity.BookingStatus;
import com.stayfinder.booking.entity.Pg;
import com.stayfinder.booking.entity.Room;
import com.stayfinder.booking.entity.User;
import com.stayfinder.booking.repository.BookingRepository;
import com.stayfinder.booking.repository.RoomRepository;
import com.stayfinder.booking.repository.UserRepository;
import com.stayfinder.booking.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
	
	 @Autowired
	    private BookingRepository bookingRepository;

	    @Autowired
	    private RoomRepository roomRepository;

	    @Autowired
	    private UserRepository userRepository;

	    @Override
	    public BookingResponse bookRoom(CreateBookingRequest request) {
	        Room room = roomRepository.findById(request.getRoomId())
	                .orElseThrow(() -> new RuntimeException("Room not found"));

	        if (!room.isAvailable()) {
	            throw new RuntimeException("Room is not available");
	        }

	        User tenant = userRepository.findById(request.getTenantId())
	                .orElseThrow(() -> new RuntimeException("Tenant not found"));

	        Booking booking = Booking.builder()
	                .room(room)
	                .tenant(tenant)
	                .startDate(request.getStartDate())
	                .endDate(request.getEndDate())
	                .status(BookingStatus.BOOKED)
	                .build();

	        Booking saved = bookingRepository.save(booking);

	        // Mark room unavailable
	        room.setAvailable(false);
	        roomRepository.save(room);

	        return BookingResponse.builder()
	                .id(saved.getId())
	                .tenantId(tenant.getId())
	                .tenantName(tenant.getName())
	                .pgId(room.getPg().getId())
	                .pgName(room.getPg().getName())
	                .roomId(room.getId())
	                .roomNumber(room.getNumber())
	                .status(saved.getStatus())
	                .startDate(saved.getStartDate())
	                .endDate(saved.getEndDate())
	                .createdAt(saved.getCreatedAt())
	                .build();
	    }

	    @Override
	    public void cancelBooking(Long bookingId) {
	        Booking booking = bookingRepository.findById(bookingId)
	                .orElseThrow(() -> new RuntimeException("Booking not found"));

	        booking.setStatus(BookingStatus.CANCELLED);
	        bookingRepository.save(booking);

	        // Mark room as available again
	        Room room = booking.getRoom();
	        room.setAvailable(true);
	        roomRepository.save(room);
	    }

	    @Override
	    public List<BookingResponse> getBookingsByTenantId(Long tenantId) {
	        List<Booking> bookings = bookingRepository.findByTenantId(tenantId);

	        return bookings.stream().map(b -> {
	            Room r = b.getRoom();
	            Pg pg = r.getPg();
	            return BookingResponse.builder()
	                    .id(b.getId())
	                    .tenantId(tenantId)
	                    .tenantName(b.getTenant().getName())
	                    .pgId(pg.getId())
	                    .pgName(pg.getName())
	                    .roomId(r.getId())
	                    .roomNumber(r.getNumber())
	                    .status(b.getStatus())
	                    .startDate(b.getStartDate())
	                    .endDate(b.getEndDate())
	                    .createdAt(b.getCreatedAt())
	                    .build();
	        }).collect(Collectors.toList());
	    }
	}
