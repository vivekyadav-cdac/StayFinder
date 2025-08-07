package com.pg.payment.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.pg.payment.dto.BookingResponse;

@FeignClient(name = "SF-BOOKINGMANAGEMENT")
public interface BookingClient {

	@GetMapping
    public BookingResponse getTenantBookings(@RequestHeader("X-User-Id") Long tenantId);
	
	
}
