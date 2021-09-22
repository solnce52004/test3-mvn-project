package dev.example.util.validators;

import dev.example.entities.User;

public interface BaseValidatorInterface {
    boolean isValid(User user) throws Exception;
}
