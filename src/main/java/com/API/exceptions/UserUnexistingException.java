package com.API.exceptions;

public class UserUnexistingException extends RuntimeException {
    public UserUnexistingException(String message)
    {
        super(message);
    }
}
