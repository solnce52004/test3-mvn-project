package dev.example.services;

import dev.example.entities.User;

public interface BaseServiceInterface {
    User findUserById(int id);

    User findUserByName(String name);
}
