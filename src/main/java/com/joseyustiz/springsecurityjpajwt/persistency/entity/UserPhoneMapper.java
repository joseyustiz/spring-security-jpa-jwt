package com.joseyustiz.springsecurityjpajwt.persistency.entity;

import com.joseyustiz.springsecurityjpajwt.model.UserPhone;
import lombok.NonNull;
import org.springframework.stereotype.Component;

/**
 * Created by jyustiz on 03-11-19 for project spring-security-jpa-jwt.
 */
@Component
public class UserPhoneMapper {
    Phone mapToPhoneJpaEntity(@NonNull UserPhone userPhone) {
        return Phone.builder()
                .number(userPhone.getNumber())
                .cityCode(userPhone.getCityCode())
                .countryCode(userPhone.getCountryCode())
                .build();
    }

    UserPhone mapToUserPhone(@NonNull Phone phone) {
        return UserPhone.builder().cityCode(phone.getCityCode()).countryCode(phone.getCountryCode()).number(phone.getNumber()).build();
    }
}
