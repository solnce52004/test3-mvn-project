package dev.example.dao.impls;

import dev.example.dao.interfaces.UserDao;
import dev.example.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserDaoImplTest {

//    @Mock
    UserDao userDao;

    @BeforeEach
    void setUp() {
        userDao = new UserDaoImpl();
    }

    @Test
    void createUser_Return_Id_On_Success_Is_Positive_Test()  throws Exception{
        final long userId = userDao.createUser(User.DEF_NAME);
        assertThat(userId).isPositive();
    }

    @Test
    void findById() {
    }

    @Test
    void findByName_Should_Return_True() throws Exception {
        final User byName = userDao.findByName(User.DEF_NAME);
        assertThat(byName).isNotNull();
        assertThat(byName.getName()).isEqualTo(User.DEF_NAME);
    }
    @Test
    void findByName_Should_Return_Null() throws Exception {
        final User byName = userDao.findByName(User.DEF_NAME + "123");
        assertThat(byName).isNull();
    }

    @Test
    void findAllUsers() {
    }
}