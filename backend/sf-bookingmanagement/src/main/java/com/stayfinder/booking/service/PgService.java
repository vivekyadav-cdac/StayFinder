package com.stayfinder.booking.service;

import java.util.List;

import com.stayfinder.booking.dto.PgDetailsResponse;
import com.stayfinder.booking.dto.PgSummaryResponse;
import com.stayfinder.booking.dto.SearchPgRequest;

public interface PgService {
	List<PgSummaryResponse> searchPgs(SearchPgRequest request);
	PgDetailsResponse getPgDetailsWithRooms(Long pgId);
}
