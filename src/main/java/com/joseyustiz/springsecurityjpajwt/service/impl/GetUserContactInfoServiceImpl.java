package com.joseyustiz.springsecurityjpajwt.service.impl;

import com.joseyustiz.springsecurityjpajwt.model.UserContactInfo;
import com.joseyustiz.springsecurityjpajwt.model.exception.UserNotFoundException;
import com.joseyustiz.springsecurityjpajwt.persistency.UserRepo;
import com.joseyustiz.springsecurityjpajwt.persistency.entity.User;
import com.joseyustiz.springsecurityjpajwt.persistency.entity.UserMapper;
import com.joseyustiz.springsecurityjpajwt.service.GetUserContactInfoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by jyustiz on 03-11-19 for project spring-security-jpa-jwt.
 */
@Service
@RequiredArgsConstructor
public class GetUserContactInfoServiceImpl implements GetUserContactInfoService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public UserContactInfo getInfo(@NonNull String email) throws UserNotFoundException {
        Optional<User> user = userRepo.findByEmail(email);

        return user.map(userMapper::mapToUserContactInfo)
                .orElseThrow(() -> new UserNotFoundException("email: " + email + " no pertenece a un usuario registrado"));
    }
}
