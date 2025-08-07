package com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.feign;

import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.dto.BookingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("SF-BOOKINGMANAGEMENT")
public interface BookingServiceClient {

    @GetMapping("/api/bookings/{bookingId}")
    BookingResponse getBookingById(@PathVariable("bookingId") Long bookingId);
}
