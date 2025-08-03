package com.stayfinder.Auth_Service.feign;

import com.stayfinder.Auth_Service.dto.RegisterRequest;
import com.stayfinder.Auth_Service.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient("AUTH-SERVICE")
public interface UserClient {

    @GetMapping("/api/user/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email);

    @PostMapping("/api/user/create")
    public ResponseEntity<UserDto> createUser(@RequestBody RegisterRequest request);
}
