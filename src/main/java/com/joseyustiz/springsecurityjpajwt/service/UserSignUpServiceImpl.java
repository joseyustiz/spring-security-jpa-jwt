package com.joseyustiz.springsecurityjpajwt.service;

import com.joseyustiz.springsecurityjpajwt.model.RegisteredUser;
import com.joseyustiz.springsecurityjpajwt.model.SignUpRequest;
import com.joseyustiz.springsecurityjpajwt.model.exception.EmailUsedException;
import com.joseyustiz.springsecurityjpajwt.persistency.UserRepo;
import com.joseyustiz.springsecurityjpajwt.persistency.entity.User;
import com.joseyustiz.springsecurityjpajwt.persistency.entity.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by jyustiz on 02-11-19 for project spring-security-jpa-jwt.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserSignUpServiceImpl implements UserSignUpService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public RegisteredUser signUp(SignUpRequest signUpRequest) throws EmailUsedException {
        Optional<User> storedUser = userRepo.findByEmail(signUpRequest.getEmail());
        if (storedUser.isPresent()) {
            throw new EmailUsedException("email: El correo ya registrado");
        }

        User user = userMapper.mapToJpaEntity(signUpRequest);
        log.info("User to persist=[{}]", user);
        User savedUser = userRepo.save(user);
        return userMapper.mapToRegisteredUser(savedUser);
    }
}
