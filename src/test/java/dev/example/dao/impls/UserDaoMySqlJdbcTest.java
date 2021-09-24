package dev.example.dao.impls;

import dev.example.config.TestConfig;
import dev.example.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = TestConfig.class,
        loader = AnnotationConfigContextLoader.class
)
class UserDaoMySqlJdbcTest {

    @Autowired
    UserDaoMySqlJdbc userDaoMySqlJdbc;

    @Test
    void dirtyCreateUser() {
        Assertions.assertThat(userDaoMySqlJdbc.dirtyCreateUser(User.DEF_NAME))
                .isPositive();
    }

    @Test
    void createUser() {
        Assertions.assertThat(userDaoMySqlJdbc.createUser(User.DEF_NAME))
                .isPositive();
    }

}