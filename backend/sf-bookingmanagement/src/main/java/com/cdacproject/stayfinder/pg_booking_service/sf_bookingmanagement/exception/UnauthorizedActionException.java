package com.cdacproject.stayfinder.pg_booking_service.sf_bookingmanagement.exception;

public class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException(String message) {
        super(message);
    }
}
