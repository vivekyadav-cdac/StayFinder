package com.stayfinder.Auth_Service.services;


import com.stayfinder.Auth_Service.exceptions.UserAlreadyExistsException;
import com.stayfinder.Auth_Service.models.AuthenticationRequest;
import com.stayfinder.Auth_Service.models.AuthenticationResponse;
import com.stayfinder.Auth_Service.models.RegisterRequest;
import com.stayfinder.Auth_Service.models.User;
import com.stayfinder.Auth_Service.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final UserRepository repository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public  AuthenticationResponse register(RegisterRequest request) {

        User user = new User();
        BeanUtils.copyProperties(request, user);
        user.setPassword(passwordEncoder.encode(request.getPassword()));


        if(repository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email already exists.");
        }
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
