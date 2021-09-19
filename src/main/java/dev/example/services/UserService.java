package dev.example.services;

import dev.example.dao.interfaces.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements BaseServiceInterface
{
    private final UserDao adminUserDao;

    @Autowired
    public UserService(UserDao adminUserDao) {
        this.adminUserDao = adminUserDao;
        print();
    }

    @Override
    public void print() {
        System.out.println(adminUserDao.findById(1111));
        System.out.println(adminUserDao.findById(20));

        System.out.println(adminUserDao.findByName("Mary"));
    }
}
