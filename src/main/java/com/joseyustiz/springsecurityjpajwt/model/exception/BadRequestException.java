package com.joseyustiz.springsecurityjpajwt.model.exception;

/**
 * Created by jyustiz on 03-11-19 for project spring-security-jpa-jwt.
 */
public class BadRequestException extends Exception {
    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
