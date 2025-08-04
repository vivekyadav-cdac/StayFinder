package com.pg.payment.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

    private final SecretKey key;

    public JwtTokenUtil(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public List<GrantedAuthority> extractRoles(String token) {
        Claims claims = getClaims(token);
        String rolesString = (String) claims.get("roles");

        if (rolesString == null || rolesString.trim().isEmpty()) {
            return List.of();
        }

        return Arrays.stream(rolesString.split(","))
                .map(String::trim)
                .filter(role -> !role.isEmpty())
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role.toUpperCase())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            System.err.println("JWT Validation failed: Token expired. " + e.getMessage());
        } catch (io.jsonwebtoken.security.SignatureException e) {
            System.err.println("JWT Validation failed: Invalid signature. " + e.getMessage());
        } catch (JwtException | IllegalArgumentException e) {
            System.err.println("JWT Validation failed: " + e.getMessage());
        }
        return false;
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Integer extractTenantId(String token) {
        Claims claims = getClaims(token);
        return claims.get("tenantId", Integer.class);
    }
}