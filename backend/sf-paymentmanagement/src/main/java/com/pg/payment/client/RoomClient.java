package com.pg.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pg.payment.model.Room;

@FeignClient(name = "room-service", url = "http://localhost:8081")
public interface RoomClient {
    @GetMapping("/api/rooms/{roomId}")
    Room getRoomById(@PathVariable Integer roomId);
}