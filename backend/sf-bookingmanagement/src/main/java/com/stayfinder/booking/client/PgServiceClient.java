package com.stayfinder.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PG-SERVICE")
public interface PgServiceClient {
    @PutMapping("/api/rooms/{roomId}/availability/false")
    void markRoomAsBooked(@PathVariable Integer roomId);

    @PutMapping("/api/rooms/{roomId}/availability/true")
    void markRoomAsAvailable(@PathVariable Integer roomId);
}
