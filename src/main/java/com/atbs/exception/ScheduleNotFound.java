package com.atbs.exception;

public class ScheduleNotFound extends RuntimeException {
    public ScheduleNotFound(String message) {
        super(message);
    }
}
