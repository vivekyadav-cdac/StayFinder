package com.stayfinder.apigateway.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.Ordered;
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
public class JwtAuthenticationFilter implements WebFilter, Ordered {

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
    public Mono<Void> filter(@NotNull ServerWebExchange exchange, @NotNull WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        // Skip auth endpoints
        if (path.startsWith("/api/auth")) {
            return chain.filter(exchange);
        }

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.hasText(authHeader) || !authHeader.startsWith("Bearer ")) {
            return unauthorized(exchange, "Missing or invalid Authorization header");
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
                    .header("X-User-Id", String.valueOf(claims.get("userId", Integer.class)))
                    .build();

            ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
            System.out.println("────────────────────────────────────────────");
            System.out.println("📡 [API-Gateway] Forwarding Request Details:");
            System.out.println("➡️  URI          : " + mutatedRequest.getURI());
            System.out.println("📝 Method       : " + mutatedRequest.getMethod());
            System.out.println("📦 Content-Type : " + mutatedRequest.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE));
            System.out.println("🧾 Headers:");
            mutatedRequest.getHeaders().forEach((key, values) -> {
                for (String value : values) {
                    System.out.println("   🔸 " + key + ": " + value);
                }
            });
            System.out.println("────────────────────────────────────────────");

            return chain.filter(mutatedExchange);

        } catch (ExpiredJwtException e) {
            return unauthorized(exchange, "JWT token expired");
        } catch (Exception e) {
            return unauthorized(exchange, "Invalid JWT token");
        }
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().add("Content-Type", "application/json");
        byte[] bytes = ("{\"error\": \"" + message + "\"}").getBytes();
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                .bufferFactory().wrap(bytes)));
    }

    @Override
    public int getOrder() {
        // Order is important - make sure this runs early but after core filters
        return -10;
    }
}

