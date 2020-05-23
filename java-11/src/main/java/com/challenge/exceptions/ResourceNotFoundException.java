package com.challenge.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Resource not found.";
    }
}
