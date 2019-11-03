package com.joseyustiz.springsecurityjpajwt.config;

import com.joseyustiz.springsecurityjpajwt.persistency.UserRepo;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by jyustiz on 02-11-19 for project spring-security-jpa-jwt.
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = UserRepo.class)
public class JpaConfiguration {
}
