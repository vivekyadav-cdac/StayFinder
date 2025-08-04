package com.stayfinder.booking.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {
	private static final String SECRET = "stayfindersecretstayfindersecretstayfindersecretstayfindersecret"; 
    // Must be at least 64 characters for HS512

	private SecretKey key;
	private JwtParser jwtParser;
	
	@PostConstruct
	public void init() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		this.key = Keys.hmacShaKeyFor(keyBytes);
        this.jwtParser = Jwts.parser().verifyWith(this.key).build();
	}
	
	public Claims extractAllClaims(String token) {
        token = cleanToken(token);
        return jwtParser.parseSignedClaims(token).getPayload();
    }

    public Long extractUserId(String token) {
        return ((Number) extractAllClaims(token).get("userId")).longValue();
    }

    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private String cleanToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }
}
