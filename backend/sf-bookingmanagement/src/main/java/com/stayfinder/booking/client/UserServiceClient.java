package com.stayfinder.booking.client;

import com.stayfinder.booking.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class UserServiceClient {

    private final WebClient.Builder webClientBuilder;
    private final JwtUtil jwtUtil;

    @Value("${user.service.url}")
    private String userServiceUrl;

    public String getUserById(Long id, String jwtToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwtToken);
        return webClientBuilder.build()
            .get().uri(userServiceUrl + "/api/users/" + id)
            .headers(h -> h.addAll(headers))
            .retrieve().bodyToMono(String.class).block();
    }
}
