package com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@Configuration
public class AuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // For now, return a dummy auditor. Later, tie it to tenant ID or security context.
        return () -> Optional.of("system");
    }
}