package com.example.airbnbapi.controller.exception;

import org.springframework.http.HttpStatus;

public class DataBaseException extends RuntimeException {

    public DataBaseException(String message) {
        super(message);
    }

    public DataBaseException(String message, Throwable cause, HttpStatus notFound) {
        super(message, cause);
    }


    public DataBaseException(String message, Throwable e) {
        super(message,e);
    }
}
