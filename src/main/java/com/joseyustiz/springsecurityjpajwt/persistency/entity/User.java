package com.joseyustiz.springsecurityjpajwt.persistency.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by jyustiz on 02-11-19 for project spring-security-jpa-jwt.
 */
@Entity(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Phone> phones;
    @Column(nullable = false)
    private Date created;
    @Column(nullable = false)
    private Date modified;
    @Column(nullable = false)
    private Date lastLogin;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private boolean active;
    @Column(nullable = false)
    private String roles;
}
