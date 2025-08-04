package com.stayfinder.Auth_Service.services.implementation;


import com.stayfinder.Auth_Service.dto.AuthenticationRequest;
import com.stayfinder.Auth_Service.dto.AuthenticationResponse;
import com.stayfinder.Auth_Service.dto.RegisterUserRequestDTO;
import com.stayfinder.Auth_Service.dto.UserDTO;

import com.stayfinder.Auth_Service.exceptions.UserAlreadyExistsException;
import com.stayfinder.Auth_Service.feign.UserClient;
import com.stayfinder.Auth_Service.services.interfaces.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    private final JwtServiceImpl jwtServiceImpl;

    private final AuthenticationManager authenticationManager;


    private final UserClient userClient;

    public AuthenticationResponse register(RegisterUserRequestDTO request) {


        request.setPassword(passwordEncoder.encode(request.getPassword()));
        UserDTO userDto;
        try {
            userDto = userClient.registerUser(request).getBody();
        }catch(RuntimeException e){
            throw new UserAlreadyExistsException("User Already exist, Please login!! ");
        }
        assert userDto != null;
        var jwtToken = jwtServiceImpl.generateToken(userDto);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDTO userDto = userClient.getUserByEmail(request.getEmail()).getBody();
        assert userDto != null;
        var jwtToken = jwtServiceImpl.generateToken(userDto);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
