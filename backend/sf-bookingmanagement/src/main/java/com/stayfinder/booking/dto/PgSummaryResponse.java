package com.stayfinder.booking.dto;

import com.stayfinder.booking.entity.PgType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PgSummaryResponse {
	private Long id;
    private String name;
    private String city;
    private String state;
    private PgType type;
    private Double latitude;
    private Double longitude;
}
