package com.stayfinder.booking.dto;

import com.stayfinder.booking.entity.PgType;

import lombok.Data;

@Data
public class SearchPgRequest {
	private String city;
    private PgType type;
    private Double minRent;
    private Double maxRent;
}
