package com.API.exceptions;

public class ContactUnexistingException extends RuntimeException{
    public ContactUnexistingException(String message)
    {
        super(message);
    }
}
