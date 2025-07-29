package com.stayfinder.booking.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CreateBookingRequest {
	private Long tenantId;
    private Long roomId;
    private LocalDate startDate;
    private LocalDate endDate;
}

