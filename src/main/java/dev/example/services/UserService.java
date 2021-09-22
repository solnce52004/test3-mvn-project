package dev.example.services;

import dev.example.dao.interfaces.UserDao;
import dev.example.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements BaseServiceInterface
{
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;

        // для тестирования логов и аспектов
//        printUserById();
//        printUserByName();
    }

//    private void printUserById() {
//        System.out.println(getUserById(1111));
//    }
//
//    private void printUserByName() {
//        System.out.println(getUserByName("Mary"));
//    }

    @Override
    public User findUserById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User findUserByName(String name) {
        return userDao.findByName(name);
    }
}
