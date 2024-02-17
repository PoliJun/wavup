package com.github.polijun.wavup.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message){
        super(message + " Not Found");
    }
}
