package dev.example.util.validators;

import dev.example.config.TestConfig;
import dev.example.dto.User;
import dev.example.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.validation.Errors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
    @Value("${name}")
    public String DEFAULT_USER_NAME;
    public static final String EMPTY_USER_NAME = "";
    public static final User USER = mock(User.class);

    @Test
    void isValid_Should_Return_True_If_Name_Is_Empty() {
        when(USER.getId()).thenReturn(DEFAULT_USER_ID);
        when(USER.getName()).thenReturn(EMPTY_USER_NAME);
        when(userService.checkUserPresence(USER)).thenReturn(true);

        assertThat(userValidator.isValid(USER)).isFalse();
    }

    @Test
    void isValid_Should_Return_True_If_Name_Is_Not_Empty() {
        when(USER.getId()).thenReturn(DEFAULT_USER_ID);
        when(USER.getName()).thenReturn(DEFAULT_USER_NAME);
        when(userService.checkUserPresence(USER)).thenReturn(true);

        assertThat(userValidator.isValid(USER)).isTrue();
    }

    @Test
    void validate_Should_Accept_User_With_New_Name() {
        //как будто такой пользователь уже есть в базе
        when(userService.checkUserPresence(USER)).thenReturn(true);

        Errors errors = mock(Errors.class);
        //делаем вызов
        userValidator.validate(USER, errors);

        //проверяем, что вызова метода в подусловии никогда не происходило
        verify(errors, never())
                .rejectValue(
                        eq("id"),
                        any(),
                        any()
                );
    }
}