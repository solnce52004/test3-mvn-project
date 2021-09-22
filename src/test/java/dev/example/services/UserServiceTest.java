package dev.example.services;

import dev.example.dao.interfaces.UserDao;
import dev.example.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final Logger LOG = LogManager.getLogger(UserServiceTest.class);
    private static final User DEFAULT_USER = new User(User.DEF_ID, User.DEF_NAME, User.DEF_AGE);
    @Mock
    UserDao userDaoImpl;
    @InjectMocks
    UserService userService;

//    @BeforeEach
//    public void setupMock(@Mock UserDao userDaoImpl) {
//        userDaoImpl = mock(UserDao.class);
//        userService = new UserService(userDaoImpl);
//    }

    @BeforeEach
    public void set() {
        LOG.info("BeforeEach");
    }

    @Test
    void is_Mock_Not_Null_Test() {
        Assertions.assertNotNull(userDaoImpl);
        Assertions.assertNotNull(userService);
    }

    @Test
    void getUserByIdTest() {
        LOG.info(userService);
        when(userService.findUserById(User.DEF_ID))
                .thenReturn(DEFAULT_USER);
        Assertions.assertEquals(DEFAULT_USER, userService.findUserById(User.DEF_ID));
    }

    @Test
    @DisplayName("😱")
    void getUserByNameTest() {
        LOG.info(userService);

        when(userService.findUserByName(User.DEF_NAME))
                .thenReturn(DEFAULT_USER);

        final User userByName = userService.findUserByName(User.DEF_NAME);
        LOG.info(userByName);

        Assertions.assertEquals(DEFAULT_USER, userByName);
    }
}