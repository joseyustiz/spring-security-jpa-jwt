package com.joseyustiz.springsecurityjpajwt.service.impl;

import com.joseyustiz.springsecurityjpajwt.model.RegisteredUser;
import com.joseyustiz.springsecurityjpajwt.model.SignUpRequest;
import com.joseyustiz.springsecurityjpajwt.model.SystemUserDetails;
import com.joseyustiz.springsecurityjpajwt.model.exception.EmailUsedException;
import com.joseyustiz.springsecurityjpajwt.persistency.UserRepo;
import com.joseyustiz.springsecurityjpajwt.persistency.entity.User;
import com.joseyustiz.springsecurityjpajwt.persistency.entity.UserMapper;
import com.joseyustiz.springsecurityjpajwt.service.UserSignUpService;
import com.joseyustiz.springsecurityjpajwt.util.JwtUtil;
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
    private final JwtUtil jwtTokenUtil;
    @Override
    public RegisteredUser signUp(SignUpRequest signUpRequest) throws EmailUsedException {
        Optional<User> storedUser = userRepo.findByEmail(signUpRequest.getEmail());
        if (storedUser.isPresent()) {
            throw new EmailUsedException("email: El correo ya registrado");
        }

        User user = userMapper.mapToJpaEntity(signUpRequest);
        user.setToken(jwtTokenUtil.generateToken(new SystemUserDetails(user)));
        log.info("User to persist=[{}]", user);
        User savedUser = userRepo.save(user);
        return userMapper.mapToRegisteredUser(savedUser);
    }
}
