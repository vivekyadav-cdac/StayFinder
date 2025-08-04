package com.pg.payment.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pg.payment.model.Booking;

// Use a dummy URL since you're not registered in Eureka
//@FeignClient(name = "booking-service", url = "http://dummy-booking-service.com") 
//public interface BookingClient {
//
//    @GetMapping("/api/bookings/{id}")
//    Booking getBookingById(@PathVariable("id") Integer bookingId);
//}
