package com.example.test.exception;

public class EmployerNotFoundException extends RuntimeException {
    public EmployerNotFoundException(String message) {
        super(message);
    }
}
