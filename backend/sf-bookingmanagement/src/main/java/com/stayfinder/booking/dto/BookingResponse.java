package com.stayfinder.booking.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.stayfinder.booking.entity.BookingStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponse {
	private Long id;
    private Long tenantId;
    private String tenantName;
    private Long pgId;
    private String pgName;
    private Long roomId;
    private String roomNumber;
    private BookingStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;

}
