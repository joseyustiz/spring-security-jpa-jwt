package com.joseyustiz.springsecurityjpajwt.service;

import com.joseyustiz.springsecurityjpajwt.model.UserContactInfo;
import com.joseyustiz.springsecurityjpajwt.model.exception.UserNotFoundException;

/**
 * Created by jyustiz on 03-11-19 for project spring-security-jpa-jwt.
 */
public interface GetUserContactInfoService {
    UserContactInfo getInfo(String email) throws UserNotFoundException;
}
