package com.stayfinder.Auth_Service.controllers;

import com.stayfinder.Auth_Service.dto.AuthenticationRequest;
import com.stayfinder.Auth_Service.dto.AuthenticationResponse;
import com.stayfinder.Auth_Service.dto.RegisterRequest;
import com.stayfinder.Auth_Service.services.implementation.AuthenticationServiceImpl;
import com.stayfinder.Auth_Service.services.interfaces.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {

        return ResponseEntity.ok(authService.authenticate(request));

    }
}
