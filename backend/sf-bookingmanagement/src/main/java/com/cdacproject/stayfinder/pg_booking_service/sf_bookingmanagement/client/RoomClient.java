package com.cdacproject.stayfinder.pg_booking_service.sf_bookingmanagement.client;

import com.cdacproject.stayfinder.pg_booking_service.sf_bookingmanagement.config.FeignClientConfig;
import com.cdacproject.stayfinder.pg_booking_service.sf_bookingmanagement.dto.RoomDto;
import com.cdacproject.stayfinder.pg_booking_service.sf_bookingmanagement.dto.RoomResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("SF-PROPERTY-SERVICE")
public interface RoomClient {
    @PreAuthorize("hasAnyRole('OWNER','USER')")
    @GetMapping("api/pgs/{pgId}/rooms/{roomId}")
    public ResponseEntity<RoomResponseDto> getRoomByPgAndRoomId(
            @PathVariable Long pgId,
            @PathVariable Long roomId);
}
