package dev.example.util.validators;

import dev.example.entities.User;
import dev.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator implements BaseValidatorInterface{

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(User user) throws Exception {
        return userService.checkUserPresence(user) && !user.getName().isEmpty();
    }
}
