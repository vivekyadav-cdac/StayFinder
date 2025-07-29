package com.stayfinder.booking.dto;

import java.math.BigDecimal;

import com.stayfinder.booking.entity.RoomType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomResponse {
	private Long id;
    private String number;
    private RoomType type;
    private BigDecimal rent;
    private boolean isAvailable;
	
}
