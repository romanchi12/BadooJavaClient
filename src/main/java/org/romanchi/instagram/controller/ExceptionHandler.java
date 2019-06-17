package org.romanchi.instagram.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.romanchi.instagram.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionHandler {
    @Data
    @AllArgsConstructor
    private class ErrorDetails{
        private String message;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ApiException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public ErrorDetails apiException(ApiException apiException){
        return new ErrorDetails(apiException.getMessage());
    }
}
