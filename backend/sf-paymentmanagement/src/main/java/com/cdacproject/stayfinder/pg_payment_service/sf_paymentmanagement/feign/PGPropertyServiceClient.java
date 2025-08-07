package com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.feign;


import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.dto.PGResponseDto;
import com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.exception.ResourceNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("SF-PROPERTY-SERVICE")
public interface PGPropertyServiceClient {

    @GetMapping("/api/pgs/{id}")
    public ResponseEntity<PGResponseDto> getById(@PathVariable Long id);
}
