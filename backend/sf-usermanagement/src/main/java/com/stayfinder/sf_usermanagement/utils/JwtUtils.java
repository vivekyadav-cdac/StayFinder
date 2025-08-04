package com.stayfinder.sf_usermanagement.utils;

import com.stayfinder.sf_usermanagement.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;

@Component
public class JwtUtils {

    @Value("${jwt.keystore.location}")
    private Resource keystore;

    @Value("${jwt.keystore.password}")
    private String keystorePassword;

    @Value("${jwt.keystore.alias}")
    private String keyAlias;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    @PostConstruct
    public void init() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        try (InputStream inputStream = keystore.getInputStream()) {
            keyStore.load(inputStream, keystorePassword.toCharArray());
            publicKey = keyStore.getCertificate(keyAlias).getPublicKey();
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getEmail(String token) {
        return getClaims(token).getSubject();
    }

    public Role getRole(String token) {
        return Role.valueOf(getClaims(token).get("role", String.class));
    }

    public Integer getUserId(String token) {
        if (getRole(token) == Role.TENANT) return getClaims(token).get("tenantId", Integer.class);
        if (getRole(token) == Role.OWNER) return getClaims(token).get("ownerId", Integer.class);
        return getClaims(token).get("adminId", Integer.class);
    }
}
