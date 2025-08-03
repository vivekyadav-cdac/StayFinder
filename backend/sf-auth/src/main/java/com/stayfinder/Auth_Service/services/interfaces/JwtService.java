package com.stayfinder.Auth_Service.services.interfaces;

import com.stayfinder.Auth_Service.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    public String extractUsername(String token);

    public String generateToken(@NotNull UserDto userDto);

    public boolean isTokenValid(String token, UserDetails userDetails);


    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
}
