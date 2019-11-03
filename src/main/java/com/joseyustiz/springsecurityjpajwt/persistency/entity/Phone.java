package com.joseyustiz.springsecurityjpajwt.persistency.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Created by jyustiz on 02-11-19 for project spring-security-jpa-jwt.
 */

@Entity(name = "phone")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Phone {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private String cityCode;
    private String countryCode;
}
