package com.github.polijun.wavup.exception;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(String msg) {
        super("Username already exists: " + msg);
    }
}
