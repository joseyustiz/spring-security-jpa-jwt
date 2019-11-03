package com.joseyustiz.springsecurityjpajwt.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by jyustiz on 02-11-19 for project spring-security-jpa-jwt.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPhone {

    @NotNull(message = "Es requerido")
    @Pattern(regexp = "^[0-9]{7}$", message = "Formato inválido")
    private String number;
    @NotNull(message = "Es requerido")
    @Pattern(regexp = "^[0-9]{1,3}$", message = "Formato inválido")
    @Setter(AccessLevel.NONE)
    private String cityCode;
    @Pattern(regexp = "^[0-9]{1,6}$", message = "Formato inválido")// reference https://countrycode.org/
    @Setter(AccessLevel.NONE)
    private String countryCode;

    @JsonSetter("citycode")
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    @JsonSetter("countrycode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
