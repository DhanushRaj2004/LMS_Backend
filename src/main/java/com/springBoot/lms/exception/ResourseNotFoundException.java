package com.springBoot.lms.exception;

public class ResourseNotFoundException extends RuntimeException{

    private String message;

    public ResourseNotFoundException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
