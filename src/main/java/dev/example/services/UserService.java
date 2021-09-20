package dev.example.services;

import dev.example.dao.interfaces.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements BaseServiceInterface
{
    private final static Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserDao adminUserDao;

    @Autowired
    public UserService(UserDao adminUserDao) {
        this.adminUserDao = adminUserDao;
        print();

        logger.debug("UserService DEBUG: created new instance");
        logger.info("UserService INFO: created new instance");
        logger.error("UserService ERROR: created new instance");
    }

    @Override
    public void print() {
        System.out.println(adminUserDao.findById(1111));
        System.out.println(adminUserDao.findById(20));

        System.out.println(adminUserDao.findByName("Mary"));
    }
}
