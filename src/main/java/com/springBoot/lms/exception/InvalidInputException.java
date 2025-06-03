package com.springBoot.lms.exception;

public class InvalidInputException extends RuntimeException{

    private String message;

    public InvalidInputException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
