package com.github.polijun.wavup.exception;

public class ImageNotFoundException extends /* RuntimeException implements */ ApiRequestException{
    public ImageNotFoundException() {
        super("Image not found");
    }
}
