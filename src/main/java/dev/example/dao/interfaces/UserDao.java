package dev.example.dao.interfaces;

import dev.example.entities.User;

public interface UserDao {
    User findById(int id);
    User findByName(String name);
}
