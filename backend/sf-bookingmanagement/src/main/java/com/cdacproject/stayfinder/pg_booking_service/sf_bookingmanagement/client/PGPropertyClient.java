package com.cdacproject.stayfinder.pg_booking_service.sf_bookingmanagement.client;

import com.cdacproject.stayfinder.pg_booking_service.sf_bookingmanagement.dto.PGDto;
import com.cdacproject.stayfinder.pg_booking_service.sf_bookingmanagement.dto.RoomDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SF-PGPROPERTY")
public interface PGPropertyClient {

    @GetMapping("/api/pgs/{pgId}")
    PGDto getPGById(@PathVariable("pgId") Long pgId);

    @GetMapping("/api/pgs/{pgId}/rooms/{roomId}")
    RoomDto getRoomByPgIdAndRoomId(
            @PathVariable("pgId") Long pgId,
            @PathVariable("roomId") Long roomId
    );
}

