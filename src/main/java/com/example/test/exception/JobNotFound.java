package com.example.test.exception;

public class JobNotFound extends RuntimeException {
    public JobNotFound(String message) {
        super(message);
    }
}
