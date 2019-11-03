package com.joseyustiz.springsecurityjpajwt.service;

import com.joseyustiz.springsecurityjpajwt.model.SystemUserDetails;
import com.joseyustiz.springsecurityjpajwt.persistency.UserRepo;
import com.joseyustiz.springsecurityjpajwt.persistency.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by jyustiz on 02-11-19 for project spring-security-jpa-jwt.
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));

        return user.map(SystemUserDetails::new).get();
    }
}
