package com.joseyustiz.springsecurityjpajwt.model;

import com.joseyustiz.springsecurityjpajwt.model.exception.EmailUsedException;
import com.joseyustiz.springsecurityjpajwt.persistency.UserRepo;
import com.joseyustiz.springsecurityjpajwt.persistency.entity.User;
import com.joseyustiz.springsecurityjpajwt.persistency.entity.UserMapper;
import com.joseyustiz.springsecurityjpajwt.persistency.entity.UserPhoneMapper;
import com.joseyustiz.springsecurityjpajwt.service.UserSignUpService;
import com.joseyustiz.springsecurityjpajwt.service.impl.UserSignUpServiceImpl;
import com.joseyustiz.springsecurityjpajwt.util.JwtUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.joseyustiz.springsecurityjpajwt.model.SystemUserDetails.USER_ROLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by jyustiz on 03-11-19 for project spring-security-jpa-jwt.
 */
class UserSignUpServiceImplTest {
    private static final String MY_EMAIL = "jose@joseyustiz.com";
    private static final SignUpRequest SIGN_UP_REQUEST = SignUpRequest.builder().email(MY_EMAIL).name("Jose Yustiz").password("Password12").build();
    private static JwtUtil jwtUtil = new JwtUtil();
    private final Date NOW = new Date();
    private final User ME = User.builder().id(UUID.randomUUID()).active(true).created(NOW).lastLogin(NOW).email(MY_EMAIL).name("Jose Yustiz")
            .modified(NOW).password("Password12").phones(null).roles(USER_ROLE).token(UUID.randomUUID().toString()).build();
    private UserRepo userRepo = mock(UserRepo.class);
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @BeforeAll
    static void init() {
        ReflectionTestUtils.setField(jwtUtil, "secretKey", "SECRET");
    }

    @Test
    void signUpSuccessful() throws EmailUsedException {
        //GIVEN
        UserSignUpService userSignUpService = new UserSignUpServiceImpl(userRepo, new UserMapper(passwordEncoder, new UserPhoneMapper()), jwtUtil);
        when(userRepo.findByEmail(MY_EMAIL)).thenReturn(Optional.empty());
        when(userRepo.save(any())).thenReturn(ME);

        //WHEN
        RegisteredUser registeredUser = userSignUpService.signUp(SIGN_UP_REQUEST);

        //THEN
        assertThat(registeredUser.getId()).isEqualTo(ME.getId());
        assertThat(registeredUser.getCreated()).isEqualTo(ME.getCreated());
        assertThat(registeredUser.getLastLogin()).isEqualTo(ME.getLastLogin());
        assertThat(registeredUser.getModified()).isEqualTo(ME.getModified());
        assertThat(registeredUser.getToken()).isNotBlank();
    }

    @Test
    void verifyEmailIsNotUsedThrowsEmailUsedExceptionWhenEmailIsUsed() {
        when(userRepo.findByEmail(MY_EMAIL)).thenReturn(Optional.of(ME));
        UserSignUpService userSignUpService = new UserSignUpServiceImpl(userRepo, null, null);
        Exception e = assertThrows(EmailUsedException.class, () -> userSignUpService.verifyEmailIsNotUsed(SIGN_UP_REQUEST));
        assertThat(e.getMessage()).isEqualTo("email: El correo ya registrado");

    }

    @Test
    void verifyEmailIsNotUsed() throws EmailUsedException {
        when(userRepo.findByEmail(MY_EMAIL)).thenReturn(Optional.empty());
        UserSignUpService userSignUpService = new UserSignUpServiceImpl(userRepo, null, null);
        userSignUpService.verifyEmailIsNotUsed(SIGN_UP_REQUEST);
    }

    @Test
    void mapUserToSaveSuccessfully() {
        UserSignUpService userSignUpService = new UserSignUpServiceImpl(null, new UserMapper(passwordEncoder, new UserPhoneMapper()), jwtUtil);

        User user = userSignUpService.mapUserToSave(SIGN_UP_REQUEST);
        assertThat(user.getToken()).isNotBlank();
        assertThat(user.getEmail()).isEqualTo(SIGN_UP_REQUEST.getEmail());
        assertThat(user.getName()).isEqualTo(SIGN_UP_REQUEST.getName());
        assertThat(user.isActive()).isTrue();
        assertThat(user.getLastLogin()).isNotNull();
        assertThat(user.getCreated()).isNotNull();
        assertThat(user.getModified()).isNotNull();
        assertThat(user.getRoles()).isNotBlank();
        assertThat(user.getPhones()).isNull();
    }
}
