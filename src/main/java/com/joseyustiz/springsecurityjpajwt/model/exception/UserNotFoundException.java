package com.joseyustiz.springsecurityjpajwt.model.exception;

/**
 * Created by jyustiz on 03-11-19 for project spring-security-jpa-jwt.
 */
public class UserNotFoundException extends BadRequestException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
