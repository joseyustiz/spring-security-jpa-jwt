package com.joseyustiz.springsecurityjpajwt.persistency;

import com.joseyustiz.springsecurityjpajwt.model.UserPhone;
import com.joseyustiz.springsecurityjpajwt.persistency.entity.Phone;
import com.joseyustiz.springsecurityjpajwt.persistency.entity.UserPhoneMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by jyustiz on 03-11-19 for project spring-security-jpa-jwt.
 */
class UserPhoneMapperTest {
    private UserPhoneMapper userPhoneMapper = new UserPhoneMapper();

    @Test
    void mapToPhoneJpaEntitySuccessful() {
        UserPhone userPhone = UserPhone.builder().number("1234567").countryCode("56").cityCode("9").build();

        Phone phone = userPhoneMapper.mapToPhoneJpaEntity(userPhone);
        assertThat(phone.getNumber()).isEqualTo(userPhone.getNumber());
        assertThat(phone.getCityCode()).isEqualTo(userPhone.getCityCode());
        assertThat(phone.getCountryCode()).isEqualTo(userPhone.getCountryCode());
    }

    @Test
    void mapToPhoneJpaEntityThrowsExceptionWhenReceiveNullUserPhone() {
        assertThrows(NullPointerException.class, () -> userPhoneMapper.mapToPhoneJpaEntity(null));
    }

    @Test
    void mapToUserPhoneSuccessful() {
        Phone phone = Phone.builder().number("1234567").countryCode("56").cityCode("9").build();

        UserPhone userPhone = userPhoneMapper.mapToUserPhone(phone);
        assertThat(userPhone.getNumber()).isEqualTo(phone.getNumber());
        assertThat(userPhone.getCityCode()).isEqualTo(phone.getCityCode());
        assertThat(userPhone.getCountryCode()).isEqualTo(phone.getCountryCode());
    }

    @Test
    void mapToUserPhoneThrowsExceptionWhenReceiveNullUserPhone() {
        assertThrows(NullPointerException.class, () -> userPhoneMapper.mapToUserPhone(null));
    }
}
