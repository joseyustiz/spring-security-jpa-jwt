package com.joseyustiz.springsecurityjpajwt.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.*;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jyustiz on 02-11-19 for project spring-security-jpa-jwt.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredUser {
    @NonNull
    private UUID id;
    @NonNull
    private Date created;
    @NonNull
    private Date modified;
    @Getter(AccessLevel.NONE)
    @NonNull
    private Date lastLogin;
    @NonNull
    private String token;
    @Getter(AccessLevel.NONE)
    private boolean active;

    @JsonGetter("last_login")
    public Date getLastLogin() {
        return lastLogin;
    }

    @JsonGetter("isactive")
    public boolean isActive() {
        return active;
    }
}
