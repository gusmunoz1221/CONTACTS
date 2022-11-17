package com.API.exceptions;

public class ErrorMessage
{
    private String message;
    private int code;

    public ErrorMessage(String message, int code){
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
