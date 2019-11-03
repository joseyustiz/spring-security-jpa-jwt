package com.joseyustiz.springsecurityjpajwt.persistency.entity;

import com.joseyustiz.springsecurityjpajwt.model.UserPhone;
import org.springframework.stereotype.Component;

/**
 * Created by jyustiz on 03-11-19 for project spring-security-jpa-jwt.
 */
@Component
public class UserPhoneMapper {
    Phone mapToPhoneJpaEntity(UserPhone userPhone) {
        return Phone.builder()
                .number(userPhone.getNumber())
                .cityCode(userPhone.getCityCode())
                .countryCode(userPhone.getCountryCode())
                .build();
    }
}
