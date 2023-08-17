package com.project.elearning.exeption;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException{
    private final HttpStatus code;

    public HttpStatus getStatus() {
        return code;
    }

    public AppException(String message, HttpStatus code) {
        super(message);
        this.code=code;

    }


}
