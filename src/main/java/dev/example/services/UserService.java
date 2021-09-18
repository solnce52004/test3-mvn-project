package dev.example.services;

import dev.config.annotations.PrintUserId;
import dev.example.dao.interfaces.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao adminUserDao;

    @Autowired
    public UserService(UserDao adminUserDao) {
        this.adminUserDao = adminUserDao;
        printUser();
    }

    @PrintUserId
    private void printUser() {
        System.out.println(adminUserDao.findById(1111));
        System.out.println(adminUserDao.findById(20));
    }
}
