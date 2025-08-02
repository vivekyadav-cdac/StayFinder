package com.stayfinder.booking.client;

import com.stayfinder.booking.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class PgServiceClient {

    private final WebClient.Builder webClientBuilder;
    private final JwtUtil jwtUtil;

    @Value("${pg.service.url}")
    private String pgServiceUrl;

    public String getPgById(Long id, String jwtToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwtToken);
        return webClientBuilder.build()
            .get().uri(pgServiceUrl + "/api/pgs/" + id)
            .headers(h -> h.addAll(headers))
            .retrieve().bodyToMono(String.class).block();
    }
}
