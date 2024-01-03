package com.github.polijun.wavup.exception;

public class DuplicateUsernameException extends RuntimeException{
    public DuplicateUsernameException(String msg) {
        super("Username already exists: " + msg);
    }
}
