package com.github.polijun.wavup.exception;

public class NonExistsException extends /* RuntimeException implements */ ApiRequestException{
    public NonExistsException(String message) {
        super(message + " does not exist");
    }
}
