package dev.example.dao.interfaces;

import dev.example.entities.User;

import java.util.List;

public interface UserDao {
    void createUserByName(String name);

    /**
     * @deprecated
     * @see #createUserByName(String name)
     */
    @Deprecated(forRemoval = false)
    long createUser(String name);

    User findById(long id);

    User findByName(String name);

    List<User> findAllUsers();
}
