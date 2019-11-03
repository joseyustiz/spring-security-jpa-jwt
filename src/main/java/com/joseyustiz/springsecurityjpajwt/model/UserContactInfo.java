package com.joseyustiz.springsecurityjpajwt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by jyustiz on 03-11-19 for project spring-security-jpa-jwt.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserContactInfo {
    private String name;
    private String email;
    private List<UserPhone> phones;

}
