package dev.example.dao.impls;

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
class UserDaoMysqlNamedParameterJdbcTemplateTest {

    @Autowired
    UserDaoMysqlNamedParameterJdbcTemplate userDao;

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
    void createUser() {
        Assertions.assertThat(userDao.createUser(User.DEF_NAME))
                .isPositive();
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

    @Test
    void countRoles() {
        userDao.truncateUsers();
        userDao.createUserByObject(new User("Bob", "admin"));
        userDao.createUserByObject(new User("Bob", "admin"));
        userDao.createUserByObject(new User("Rob", "quest"));
        userDao.createUserByObject(new User("Rob", "quest"));
        userDao.createUserByObject(new User("Dob", "support"));

        Assertions.assertThat(userDao.countRoles())
                .matches(m -> m.size() == 3);
    }

    @Test
    void createUserByObject(){
        userDao.truncateUsers();
        userDao.createUserByObject(new User("Bob", "admin"));
        Assertions.assertThat(userDao.findByName("Bob"))
                .isNotNull();
    }
}