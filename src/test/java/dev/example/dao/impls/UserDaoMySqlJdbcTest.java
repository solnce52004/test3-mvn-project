package dev.example.dao.impls;

import dev.example.config.TestConfig;
import dev.example.dto.User;
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
    UserDaoMySqlJdbc userDao;

    @Test
    void dirtyCreateUser() {
        Assertions.assertThat(userDao.dirtyCreateUser(User.DEF_NAME))
                .isPositive();
    }

    @Test
    void createUser() {
        Assertions.assertThat(userDao.createUser(User.DEF_NAME))
                .isPositive();
    }

}