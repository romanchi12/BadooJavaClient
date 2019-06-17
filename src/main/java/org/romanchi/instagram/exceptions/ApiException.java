package org.romanchi.instagram.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
