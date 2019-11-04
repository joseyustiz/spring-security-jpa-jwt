package com.joseyustiz.springsecurityjpajwt.model;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by jyustiz on 02-11-19 for project spring-security-jpa-jwt.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @Size(min = 2, max = 255)
    @NotNull
    private String name;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Formato inválido")
    @NotNull(message = "Es requerido")
    private String email;
    /*  Explanation:
        ^                 # start-of-string
        (?=.*[0-9])       # a digit must occur at least once
        (?=.*[a-z])       # a lower case letter must occur at least once
        (?=.*[A-Z])       # an upper case letter must occur at least once
        (?=.*[@#$%^&+=])  # a special character must occur at least once
        (?=\S+$)          # no whitespace allowed in the entire string
        .{8,}             # anything, at least eight places though
        $
    */
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.{2,}[0-9])(?=\\S+$).{4,}$", message = "No cumple la complejidad mínima")
    @NotNull(message = "Es requerido")
    @ToString.Exclude
    private String password;
    @Valid
    private List<UserPhone> phones;
}
