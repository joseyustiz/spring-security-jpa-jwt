package com.joseyustiz.springsecurityjpajwt.model.exception;

/**
 * Created by jyustiz on 02-11-19 for project spring-security-jpa-jwt.
 */
public class EmailUsedException extends BadRequestException {

    public EmailUsedException(String message) {
        super(message);
    }
}
