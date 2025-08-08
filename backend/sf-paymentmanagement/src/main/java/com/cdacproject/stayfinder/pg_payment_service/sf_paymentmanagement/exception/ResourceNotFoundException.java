package com.cdacproject.stayfinder.pg_payment_service.sf_paymentmanagement.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
