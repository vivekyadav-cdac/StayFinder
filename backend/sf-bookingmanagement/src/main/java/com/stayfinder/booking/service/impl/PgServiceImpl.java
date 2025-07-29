package com.stayfinder.booking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stayfinder.booking.dto.PgDetailsResponse;
import com.stayfinder.booking.dto.PgSummaryResponse;
import com.stayfinder.booking.dto.RoomResponse;
import com.stayfinder.booking.dto.SearchPgRequest;
import com.stayfinder.booking.entity.Pg;
import com.stayfinder.booking.entity.Room;
import com.stayfinder.booking.exception.ResourceNotFoundException;
import com.stayfinder.booking.repository.PgRepository;
import com.stayfinder.booking.repository.RoomRepository;
import com.stayfinder.booking.service.PgService;

@Service
public class PgServiceImpl implements PgService {
	
	 @Autowired
	 private PgRepository pgRepository;

	 @Autowired
	 private RoomRepository roomRepository;

	@Override
	public List<PgSummaryResponse> searchPgs(SearchPgRequest request) {
		List<Pg> pgs = pgRepository.findByCityIgnoreCaseAndType(request.getCity(), request.getType());
		
		return pgs.stream()
                .map(pg -> PgSummaryResponse.builder()
                        .id(pg.getId())
                        .name(pg.getName())
                        .city(pg.getCity())
                        .state(pg.getState())
                        .type(pg.getType())
                        .latitude(pg.getLatitude())
                        .longitude(pg.getLongitude())
                        .build())
                .collect(Collectors.toList());
	}

	@Override
	public PgDetailsResponse getPgDetailsWithRooms(Long pgId) {
		Pg pg = pgRepository.findById(pgId)
                .orElseThrow(() -> new ResourceNotFoundException("PG not found"));
		
		List<Room> rooms = roomRepository.findByPgId(pgId);
		
		List<RoomResponse> roomResponses = rooms.stream()
                .map(room -> RoomResponse.builder()
                        .id(room.getId())
                        .number(room.getNumber())
                        .type(room.getType())
                        .rent(room.getRent())
                        .isAvailable(room.isAvailable())
                        .build())
                .collect(Collectors.toList());
		
		return PgDetailsResponse.builder()
                .id(pg.getId())
                .name(pg.getName())
                .address(pg.getAddress())
                .city(pg.getCity())
                .state(pg.getState())
                .type(pg.getType())
                .contact(pg.getContact())
                .latitude(pg.getLatitude())
                .longitude(pg.getLongitude())
                .rooms(roomResponses)
                .build();
	}

}
