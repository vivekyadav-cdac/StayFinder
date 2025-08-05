package com.cdacproject.stayfinder.pg_booking_service.sf_bookingmanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private String message;
}


