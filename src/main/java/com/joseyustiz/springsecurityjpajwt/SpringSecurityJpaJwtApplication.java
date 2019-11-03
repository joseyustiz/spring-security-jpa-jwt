package com.joseyustiz.springsecurityjpajwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.joseyustiz.springsecurityjpajwt.*")
public class SpringSecurityJpaJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJpaJwtApplication.class, args);
    }

}
