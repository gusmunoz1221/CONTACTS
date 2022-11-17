package com.API.exceptions;

public class ContactExistingException extends RuntimeException{
    public ContactExistingException(String message)
    {
        super(message);
    }
}
