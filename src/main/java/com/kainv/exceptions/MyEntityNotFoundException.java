package com.kainv.exceptions;

public class MyEntityNotFoundException extends RuntimeException {
    public MyEntityNotFoundException() {
    }

    public MyEntityNotFoundException(String message) {
        super(message);
    }
}
