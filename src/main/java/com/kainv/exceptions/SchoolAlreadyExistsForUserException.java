package com.kainv.exceptions;

public class SchoolAlreadyExistsForUserException extends RuntimeException {
    public SchoolAlreadyExistsForUserException() {
    }

    public SchoolAlreadyExistsForUserException(String message) {
        super(message);
    }
}
