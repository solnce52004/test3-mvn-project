package dev.example.dao.interfaces;

import dev.example.entities.User;

import java.util.List;

public interface UserDao {
    long createUser(String name) throws Exception;

    User findById(long id) throws Exception;

    User findByName(String name) throws Exception;

    List<User> findAllUsers();
}
