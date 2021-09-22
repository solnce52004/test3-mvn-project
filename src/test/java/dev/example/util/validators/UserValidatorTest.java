package dev.example.util.validators;

import dev.example.config.TestConfig;
import dev.example.entities.User;
import dev.example.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = TestConfig.class,
        loader = AnnotationConfigContextLoader.class
)
class UserValidatorTest {

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;

    public static final long DEFAULT_USER_ID = 1;
    public static final String DEFAULT_USER_NAME = "Bob";
    public static final String EMPTY_USER_NAME = "";
    public static final User USER = mock(User.class);

    @Test
    void isValid_Should_Return_True_If_Name_Is_Empty() throws Exception {
        when(USER.getId()).thenReturn(DEFAULT_USER_ID);
        when(USER.getName()).thenReturn(EMPTY_USER_NAME);
        when(userService.checkUserPresence(USER)).thenReturn(true);

        assertThat(userValidator.isValid(USER)).isFalse();
    }

    @Test
    void isValid_Should_Return_True_If_Name_Is_Not_Empty() throws Exception {
        when(USER.getId()).thenReturn(DEFAULT_USER_ID);
        when(USER.getName()).thenReturn(DEFAULT_USER_NAME);
        when(userService.checkUserPresence(USER)).thenReturn(true);

        assertThat(userValidator.isValid(USER)).isTrue();
    }
}