package com.pg.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pg.payment.dto.RoomResponseDto;

@FeignClient(name = "SF-PROPERTY-SERVICE")
public interface RoomClient {
	
//	@PreAuthorize("hasAnyRole('OWNER','USER')")
    @GetMapping("/{roomId}")
    public RoomResponseDto getRoom(@PathVariable Long roomId);
}