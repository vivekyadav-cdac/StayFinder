package com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignClientConfig implements RequestInterceptor {

    public void apply(RequestTemplate template) {
        // Forward user details manually from current request context
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            String authHeader = request.getHeader("Authorization");
            String userId = request.getHeader("X-User-Id");
            String userEmail = request.getHeader("X-User-Email");
            String userRole = request.getHeader("X-User-Role");

            if (authHeader != null) {
                template.header("Authorization", authHeader);
            }
            if (userId != null) {
                template.header("X-User-Id", userId);
            }
            if (userEmail != null) {
                template.header("X-User-Email", userEmail);
            }
            if (userRole != null) {
                template.header("X-User-Role", userRole);
            }
        }
    }
}

