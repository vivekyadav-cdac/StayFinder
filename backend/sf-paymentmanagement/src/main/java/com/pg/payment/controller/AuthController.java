package com.pg.payment.controller;

import com.pg.payment.security.JwtTokenUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username, @RequestParam String role, @RequestParam Integer tenantId) {
    	SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    	String token = Jwts.builder()
    	    .setSubject(username)
    	    .claim("roles", role)
    	    .claim("tenantId", tenantId)
    	    .setIssuedAt(new Date())
    	    .setExpiration(new Date(System.currentTimeMillis() + 3600000))
    	    .signWith(key)
    	    .compact();


        return ResponseEntity.ok(Map.of("token", token));
    }

}
