package com.stayfinder.Auth_Service.services.interfaces;

import com.stayfinder.Auth_Service.dto.AuthenticationRequest;
import com.stayfinder.Auth_Service.dto.AuthenticationResponse;
import com.stayfinder.Auth_Service.dto.RegisterRequest;
import com.stayfinder.Auth_Service.dto.UserDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthenticationService {
    public AuthenticationResponse register(RegisterRequest request);

    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
