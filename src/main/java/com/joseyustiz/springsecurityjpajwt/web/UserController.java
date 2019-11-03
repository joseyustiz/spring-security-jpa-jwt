package com.joseyustiz.springsecurityjpajwt.web;

import com.joseyustiz.springsecurityjpajwt.model.RegisteredUser;
import com.joseyustiz.springsecurityjpajwt.model.SignUpRequest;
import com.joseyustiz.springsecurityjpajwt.model.UserContactInfo;
import com.joseyustiz.springsecurityjpajwt.model.exception.EmailUsedException;
import com.joseyustiz.springsecurityjpajwt.model.exception.UserNotFoundException;
import com.joseyustiz.springsecurityjpajwt.service.GetUserContactInfoService;
import com.joseyustiz.springsecurityjpajwt.service.UserSignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by jyustiz on 02-11-19 for project spring-security-jpa-jwt.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserSignUpService userSignUpService;
    private final GetUserContactInfoService getUserContactInfoService;

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisteredUser> signUp(@RequestBody @Valid SignUpRequest signUpRequest) throws EmailUsedException {
        log.info("Request - signUpRequest=[{}]", signUpRequest);
        RegisteredUser registeredUser = userSignUpService.signUp(signUpRequest);
        log.info("Response - registeredUser=[{}]", registeredUser);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping(value = "/info")
    public UserContactInfo info(@RequestParam String email) throws UserNotFoundException {
        return getUserContactInfoService.getInfo(email);
    }

}
