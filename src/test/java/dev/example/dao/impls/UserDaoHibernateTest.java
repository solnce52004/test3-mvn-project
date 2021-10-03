package dev.example.dao.impls;

import dev.example.config.HibernateConfig;
import dev.example.dto.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = HibernateConfig.class,
        loader = AnnotationConfigContextLoader.class
)
class UserDaoHibernateTest {

    @Autowired
    UserDaoHibernate userDaoHibernate;

    @Test
    void createUserByName() {
        //
    }

    @Test
    void createUser() {
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

    @Test
    void truncateUsers() {
    }

    @Test
    void createUserByObject() {
        userDaoHibernate.createUserByObject(new User( "dfgd002", "ttt"));
    }
}