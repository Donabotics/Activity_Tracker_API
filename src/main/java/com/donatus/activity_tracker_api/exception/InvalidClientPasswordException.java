package com.donatus.activity_tracker_api.exception;

public class InvalidClientPasswordException extends RuntimeException {
    public InvalidClientPasswordException(String message) {
        super(message);
    }
}
