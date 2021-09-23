package dev.example.dao.impls;

import dev.example.config.TestConfig;
import dev.example.dao.interfaces.UserDao;
import dev.example.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = TestConfig.class,
        loader = AnnotationConfigContextLoader.class
)
class UserDaoJdbcMysqlTest {

    @Autowired
    UserDaoJdbcMysql userDaoJdbcMysql;

    @Test
    void dirtyCreateUser() {
        Assertions.assertThat(userDaoJdbcMysql.dirtyCreateUser(User.DEF_NAME))
                .isPositive();
    }

    @Test
    void createUser() throws Exception {
        Assertions.assertThat(userDaoJdbcMysql.createUser(User.DEF_NAME))
                .isPositive();
    }

    @Test
    void findById() {
    }

    @Test
    void findByName() {
    }

    @Test
    void findAllUsers() {
    }
}