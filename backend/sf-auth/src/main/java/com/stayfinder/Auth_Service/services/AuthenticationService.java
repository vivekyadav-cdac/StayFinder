package com.stayfinder.Auth_Service.services;


import com.stayfinder.Auth_Service.exceptions.UserAlreadyExistsException;
import com.stayfinder.Auth_Service.dto.AuthenticationRequest;
import com.stayfinder.Auth_Service.dto.AuthenticationResponse;
import com.stayfinder.Auth_Service.dto.RegisterRequest;
import com.stayfinder.Auth_Service.models.User;
import com.stayfinder.Auth_Service.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        User user = new User();
        BeanUtils.copyProperties(request, user);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email already exists.");
        }

        User addedUser = repository.save(user);

        var jwtToken = jwtService.generateToken(addedUser);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User does not exists. Please register"));
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
