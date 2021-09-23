package dev.example.util.validators;

import dev.example.entities.User;
import dev.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean isValid(User user) throws Exception {
        return userService.checkUserPresence(user) && !user.getName().isEmpty();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!userService.checkUserPresence((User) target)) {
            errors.rejectValue(
                    "id",
                    "",
                    "This name already in use"
            );
        }
    }
}
