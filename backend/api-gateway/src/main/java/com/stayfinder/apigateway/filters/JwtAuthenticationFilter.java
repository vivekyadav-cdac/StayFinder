package com.stayfinder.apigateway.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.Certificate;

@Component
public class JwtAuthenticationFilter implements WebFilter {
    @Value("${spring.jwt.keystore.location}")
    private String keystoreLocation;

    @Value("${spring.jwt.keystore.password}")
    private String keystorePassword;

    @Value("${spring.jwt.keystore.alias}")
    private String keystoreAlias;

    private PublicKey publicKey;

    @PostConstruct
    public void loadPublicKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new ClassPathResource(keystoreLocation.replace("classpath:", "")).getInputStream(), keystorePassword.toCharArray());

        Certificate cert = keyStore.getCertificate(keystoreAlias);
        this.publicKey = cert.getPublicKey();
    }


    @Override
    public @NotNull Mono<Void> filter(@NotNull ServerWebExchange exchange, @NotNull WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // Skip auth for login/register paths
        if (request.getURI().getPath().contains("/api/auth")) {
            return chain.filter(exchange);
        }

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasText(authHeader) || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            ServerHttpRequest mutatedRequest = request.mutate()
                    .header("X-User-Email", claims.getSubject())
                    .header("X-User-Role", claims.get("role", String.class))
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());

        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

}