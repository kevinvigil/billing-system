package com.system.billingSystem.exeption;

import org.springframework.http.HttpStatus;

public class ErrorDetails {

    private HttpStatus status;
    private String message;
    private String details;

    public ErrorDetails(HttpStatus status, String message, String details) {
        super();
        this.status = status;
        this.message = message;
        this.details = details;
    }
}
