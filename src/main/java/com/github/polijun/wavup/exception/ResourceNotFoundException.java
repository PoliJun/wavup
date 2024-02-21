package com.github.polijun.wavup.exception;

public class ResourceNotFoundException extends /* RuntimeException implements */ ApiRequestException{
    public ResourceNotFoundException(String message){
        super(message + " Not Found");
    }
}
