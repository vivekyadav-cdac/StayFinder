package com.stayfinder.booking.service;

import java.math.BigDecimal;
import java.util.List;

import com.stayfinder.booking.dto.PgDetailsResponse;
import com.stayfinder.booking.dto.PgSummaryResponse;
import com.stayfinder.booking.dto.SearchPgRequest;
import com.stayfinder.booking.entity.Pg;
import com.stayfinder.booking.entity.PgType;

public interface PgService {
	List<PgSummaryResponse> searchPgs(SearchPgRequest request);
	PgDetailsResponse getPgDetailsWithRooms(Long pgId);
	List<Pg> searchPgsByBudget(String city, PgType type, BigDecimal minRent, BigDecimal maxRent);
}
