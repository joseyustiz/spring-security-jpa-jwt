package com.joseyustiz.springsecurityjpajwt.persistency;

import com.joseyustiz.springsecurityjpajwt.persistency.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.joseyustiz.springsecurityjpajwt.model.SystemUserDetails.USER_ROLE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jyustiz on 03-11-19 for project spring-security-jpa-jwt.
 */
@DataJpaTest
class UserRepoIntegrationTest {
    private static final String MY_EMAIL = "jose@joseyustiz.com";
    private static final Date NOW = new Date();
    private static final User ME = User.builder()
            .active(true)
            .created(NOW)
            .lastLogin(NOW)
            .email(MY_EMAIL)
            .name("Jose Yustiz")
            .modified(NOW)
            .password("PASSWORD")
            .phones(null)
            .roles(USER_ROLE)
            .token(UUID.randomUUID().toString())
            .build();
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepo repository;

    @Test
    @DisplayName("Testing successfully find the a user by email")
    void testFindByEmailSuccessful() {
        this.entityManager.persist(ME);

        Optional<User> user = this.repository.findByEmail(MY_EMAIL);

        assertThat(user).isPresent();
        assertThat(user.get().getEmail()).isEqualTo(MY_EMAIL);
    }

    @Test
    @DisplayName("Testing unsuccessfully find the a user by email")
    void testFindByEmailUnsuccessful() {
        this.repository.save(ME);

        Optional<User> user = this.repository.findByEmail("other@email.com");

        assertThat(user).isNotPresent();
    }
}
