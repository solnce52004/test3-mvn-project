package dev.example.dao.interfaces;

import dev.example.dto.User;

import java.util.List;

public interface UserDao {
    void createUserByName(String name);

    long createUser(String name);

    User findById(long id);

    User findByName(String name);

    List<User> findAllUsers();

    void truncateUsers();

    void createUserByObject(User user);
}
