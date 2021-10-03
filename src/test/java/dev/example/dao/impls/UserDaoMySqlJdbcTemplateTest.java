package dev.example.dao.impls;

import dev.example.config.TestPersistenceConfig;
import dev.example.dto.User;
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
    UserDaoMySqlJdbcTemplate userDao;

    @BeforeEach
    void setUp() {
        userDao.truncateUsers();
        userDao.createUserByName(User.DEF_NAME);
    }

    @Test
    void createUserByName() {
        Assertions.assertThat(userDao.findByName(User.DEF_NAME))
                .isNotNull();
    }

    @Test
    void findById() {
        final long existUserId = userDao.findByName(User.DEF_NAME).getId();
        Assertions.assertThat(userDao.findById(existUserId))
                .isNotNull();
    }

    @Test
    void findByName() {
        Assertions.assertThat(userDao.findByName(User.DEF_NAME))
                .isNotNull();
    }

    @Test
    void findAllUsers() {
        Assertions.assertThat(userDao.findAllUsers())
                .isNotEmpty();
    }

    @Test
    void truncateUsersTable() {
        userDao.truncateUsers();
        Assertions.assertThat(userDao.findAllUsers())
                .isEmpty();
    }
}