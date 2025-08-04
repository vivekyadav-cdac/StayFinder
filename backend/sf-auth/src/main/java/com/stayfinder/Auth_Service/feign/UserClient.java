package com.stayfinder.Auth_Service.feign;

import com.stayfinder.Auth_Service.dto.RegisterUserRequestDTO;
import com.stayfinder.Auth_Service.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient("SF-USERMANAGEMENT")
public interface UserClient {

    @GetMapping("/api/v1/user/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) ;
    @PostMapping("/api/v1/user/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody RegisterUserRequestDTO request) ;
}
