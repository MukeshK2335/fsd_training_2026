package com.atbs.exception;

public class FlightOwnerNotFoundException extends RuntimeException {
    public FlightOwnerNotFoundException(String message) {
        super(message);
    }
}
