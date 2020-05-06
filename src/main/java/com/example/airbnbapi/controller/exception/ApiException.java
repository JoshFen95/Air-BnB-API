package com.example.airbnbapi.controller.exception;

import org.springframework.http.HttpStatus;


public class ApiException {

    private final String message;
    private final HttpStatus httpStatus;

    public ApiException(String message, HttpStatus httpStatus) {
        this.message = message;
        // this.throwable = throwable;
        this.httpStatus = httpStatus;

    }

    public String getMessage() {
        return message;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
