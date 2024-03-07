package com.testtask.testtaskrestapi.exception;



public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}