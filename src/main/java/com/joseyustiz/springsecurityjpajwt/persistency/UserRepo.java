package com.joseyustiz.springsecurityjpajwt.persistency;

import com.joseyustiz.springsecurityjpajwt.persistency.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by jyustiz on 02-11-19 for project spring-security-jpa-jwt.
 */
@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

}
