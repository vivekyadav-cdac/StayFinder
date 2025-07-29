package com.stayfinder.booking.dto;

import java.util.List;

import com.stayfinder.booking.entity.PgType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PgDetailsResponse {
	private Long id;
    private String name;
    private String address;
    private String city;
    private String state;
    private PgType type;
    private String contact;
    private Double latitude;
    private Double longitude;
    private List<RoomResponse> rooms;

}
