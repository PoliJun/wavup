package com.github.polijun.wavup.exception;

public class AlreadyExistsException extends ApiRequestException {
    public AlreadyExistsException(String message) {
        super(message + " Already exists");
    }
}
