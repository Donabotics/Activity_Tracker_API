package com.donatus.activity_tracker_api.exception;

public class DuplicateEmailAddressException extends RuntimeException {
    public DuplicateEmailAddressException(String message) {
        super(message);
    }
}
