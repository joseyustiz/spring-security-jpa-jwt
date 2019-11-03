package com.joseyustiz.springsecurityjpajwt.service;

import com.joseyustiz.springsecurityjpajwt.model.RegisteredUser;
import com.joseyustiz.springsecurityjpajwt.model.SignUpRequest;
import com.joseyustiz.springsecurityjpajwt.model.exception.EmailUsedException;

/**
 * Created by jyustiz on 03-11-19 for project spring-security-jpa-jwt.
 */
public interface UserSignUpService {
    RegisteredUser signUp(SignUpRequest signUpRequest) throws EmailUsedException;
}
