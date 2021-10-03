package dev.example.services;

import dev.example.dao.interfaces.UserDao;
import dev.example.dto.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final Logger LOG = LogManager.getLogger(UserServiceTest.class);
    private static final User DEFAULT_USER_WITH_EMPTY_NAME = new User(User.DEF_ID, "", User.DEF_ROLE);
    private static final User DEFAULT_USER = User.getDefaultUser();;

    @Mock
    UserDao userDaoMock;
    @InjectMocks
    UserService userService;

//    @BeforeEach
//    public void setupMock(@Mock UserDao userDaoMock) {
//        userDaoMock = mock(UserDao.class);
//        userService = new UserService(userDaoImpl);
//    }

//    @Test
//    void is_Mock_Not_Null() {
//        assertThat(userDaoMock).isNotNull();
//        assertThat(userService).isNotNull();
//    }

    @Test
    void checkUserPresence_By_Name_Should_Return_True() {
        given(userDaoMock.findByName(User.DEF_NAME))
                .willReturn(DEFAULT_USER);

        final boolean checkUserPresence = userService.checkUserPresence(DEFAULT_USER);
        assertThat(checkUserPresence).isTrue();
    }

    @Test
    void checkUserPresence_By_Name_Should_Return_False() {
        given(userDaoMock.findByName(User.DEF_NAME))
                .willReturn(null);

        final boolean checkUserPresence = userService.checkUserPresence(DEFAULT_USER);
        assertThat(checkUserPresence).isFalse();
    }

    @Test
    void checkUserPresence_By_Id_Should_Return_True() {
        given(userDaoMock.findById(User.DEF_ID))
                .willReturn(DEFAULT_USER_WITH_EMPTY_NAME);

        final boolean checkUserPresence = userService.checkUserPresence(DEFAULT_USER_WITH_EMPTY_NAME);
        assertThat(checkUserPresence).isTrue();
    }

    @Test
    @Disabled("при включении параллелизма times() меняется")
    void checkUserPresence_By_Id_Verify_Times() {
        given(userDaoMock.findById(User.DEF_ID))
                .willReturn(DEFAULT_USER_WITH_EMPTY_NAME);

        final boolean checkUserPresence = userService.checkUserPresence(DEFAULT_USER_WITH_EMPTY_NAME);
        assertThat(checkUserPresence).isTrue();

        verify(userDaoMock, times(0))
                .findByName(anyString());
        verify(userDaoMock, times(1))
                .findById(User.DEF_ID);
    }

    @Test
    @Disabled("при включении параллелизма captor ломается")
    void checkUserPresence_Test_Captor_By_Name() {
        given(userDaoMock.findByName(User.DEF_NAME))
                .willReturn(DEFAULT_USER);

        final boolean checkUserPresence = userService.checkUserPresence(DEFAULT_USER);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(userDaoMock).findByName(captor.capture());
        final String captorValue = captor.getValue();

        assertThat(captorValue).isEqualTo(User.DEF_NAME);
    }

    /**
     * только если пустое имя, будет проверка по ид
     */
    @Test
    @Disabled("при включении параллелизма captor ломается")
    void checkUserPresence_Test_Captor_By_Id() {
        given(userDaoMock.findById(User.DEF_ID))
                .willReturn(DEFAULT_USER_WITH_EMPTY_NAME);

        final boolean checkUserPresence = userService.checkUserPresence(DEFAULT_USER_WITH_EMPTY_NAME);

        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(userDaoMock).findById(captor.capture());
        final Long captorValue = captor.getValue();

        assertThat(captorValue).isEqualTo(User.DEF_ID);
    }

    @Test
    void getUserByIdTest() {
        when(userDaoMock.findById(User.DEF_ID))
                .thenReturn(DEFAULT_USER);
        Assertions.assertEquals(
                DEFAULT_USER,
                userService.findUserById(User.DEF_ID)
        );
    }

    @Test
    @DisplayName("😱")
    void getUserByNameTest() {
        when(userDaoMock.findByName(User.DEF_NAME))
                .thenReturn(DEFAULT_USER);

        Assertions.assertEquals(
                DEFAULT_USER,
                userService.findUserByName(User.DEF_NAME)
        );
    }

    @Test
    void if_user_id_is_negative_or_equal_to_zero_throw_exception_test() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> userService.findUserById(-1L)
        );
    }
}