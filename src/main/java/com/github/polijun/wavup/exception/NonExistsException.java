package com.github.polijun.wavup.exception;

public class NonExistsException extends RuntimeException {
    public NonExistsException(String message) {
        super(message + " does not exist");
    }
}
