package com.example.airbnbapi.controller.exception;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, Throwable cause, HttpStatus notFound) {
        super(message, cause);
    }



}
