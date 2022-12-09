package com.API.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> defaultErrorHandler(HttpServletRequest req, Exception e ){
        return new ResponseEntity<ErrorMessage>(new ErrorMessage(e.getMessage(),1), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ContactUnexistingException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> notFoundHandler(HttpServletRequest req,Exception e){
        return new ResponseEntity<ErrorMessage>(new ErrorMessage(e.getMessage(),2),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ContactExistingException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> ValidationHandler(HttpServletRequest req,Exception e){
        return new ResponseEntity<ErrorMessage>(new ErrorMessage(e.getMessage(),3), HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(UserUnexistingException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> notFoundUser(HttpServletRequest req, Exception e ){
        return new ResponseEntity<ErrorMessage>(new ErrorMessage(e.getMessage(),4), HttpStatus.NOT_FOUND);
    }
   /*
      SOLO PIDE AGREGAR MESSAGE Y COD
     @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> badRequestHandler(HttpServletRequest req,Exception e){
        return new ResponseEntity<ErrorMessage>(new ErrorMessage(e.getMessage(),400),HttpStatus.BAD_REQUEST);
    }
   */

}
