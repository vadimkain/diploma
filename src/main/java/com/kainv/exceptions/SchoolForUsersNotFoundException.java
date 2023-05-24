package com.kainv.exceptions;

public class SchoolForUsersNotFoundException extends RuntimeException {
    public SchoolForUsersNotFoundException() {
    }

    public SchoolForUsersNotFoundException(String message) {
        super(message);
    }
}
