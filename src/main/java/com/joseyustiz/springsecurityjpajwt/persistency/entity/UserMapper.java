package com.joseyustiz.springsecurityjpajwt.persistency.entity;

import com.joseyustiz.springsecurityjpajwt.model.RegisteredUser;
import com.joseyustiz.springsecurityjpajwt.model.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

import static com.joseyustiz.springsecurityjpajwt.model.SystemUserDetails.USER_ROLE;
import static java.util.stream.Collectors.toList;

/**
 * Created by jyustiz on 02-11-19 for project spring-security-jpa-jwt.
 */
@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    private final UserPhoneMapper userPhoneMapper;

    public User mapToJpaEntity(SignUpRequest signUpRequest) {
        Date now = new Date();
        return User.builder()
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .phones(signUpRequest.getPhones() == null ? null : signUpRequest.getPhones().stream().map(userPhoneMapper::mapToPhoneJpaEntity).collect(toList()))
                .token(UUID.randomUUID().toString())
                .active(true)
                .roles(USER_ROLE)
                .created(now)
                .lastLogin(now)
                .modified(now)
                .build();
    }

    public RegisteredUser mapToRegisteredUser(User user) {
        return RegisteredUser.builder()
                .id(user.getId())
                .created(user.getCreated())
                .modified(user.getModified())
                .lastLogin(user.getLastLogin())
                .token(user.getToken())
                .active(user.isActive())
                .build();
    }
}
