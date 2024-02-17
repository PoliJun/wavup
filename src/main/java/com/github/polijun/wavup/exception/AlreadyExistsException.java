package com.github.polijun.wavup.exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message + " Already exists");
    }
}
