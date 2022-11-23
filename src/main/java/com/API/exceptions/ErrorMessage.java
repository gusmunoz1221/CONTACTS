package com.API.exceptions;

import lombok.Data;
@Data
public class ErrorMessage
{
    private String message;
    private int code;

    public ErrorMessage(String message, int code){
        this.message = message;
        this.code = code;
    }
}
