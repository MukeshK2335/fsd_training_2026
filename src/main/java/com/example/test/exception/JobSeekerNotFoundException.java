package com.example.test.exception;

public class JobSeekerNotFoundException extends RuntimeException {
    public JobSeekerNotFoundException(String message) {
        super(message);
    }
}
