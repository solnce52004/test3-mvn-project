package dev.example.dao.impls;

import dev.example.config.TestConfig;
import dev.example.config.TestPersistenceConfig;
import dev.example.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = TestPersistenceConfig.class,
        loader = AnnotationConfigContextLoader.class
)
class UserDaoMySqlJdbcTemplateTest {

    @Autowired
    UserDaoMySqlJdbcTemplate userDaoMySqlJdbcTemplate;

    @BeforeEach
    void setUp() {
        userDaoMySqlJdbcTemplate.truncateUsers();
        userDaoMySqlJdbcTemplate.createUserByName(User.DEF_NAME);
    }

    @Test
    void createUserByName() {
        Assertions.assertThat(userDaoMySqlJdbcTemplate.findByName(User.DEF_NAME))
                .isNotNull();
    }

    @Test
    void findById() {
        final long existUserId = userDaoMySqlJdbcTemplate.findByName(User.DEF_NAME).getId();
        Assertions.assertThat(userDaoMySqlJdbcTemplate.findById(existUserId))
                .isNotNull();
    }

    @Test
    void findByName() {
        Assertions.assertThat(userDaoMySqlJdbcTemplate.findByName(User.DEF_NAME))
                .isNotNull();
    }

    @Test
    void findAllUsers() {
        Assertions.assertThat(userDaoMySqlJdbcTemplate.findAllUsers())
                .isNotEmpty();
    }

    @Test
    void truncateUsersTable() {
        userDaoMySqlJdbcTemplate.truncateUsers();
        Assertions.assertThat(userDaoMySqlJdbcTemplate.findAllUsers())
                .isEmpty();
    }
}