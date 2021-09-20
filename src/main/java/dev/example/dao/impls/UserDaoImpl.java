package dev.example.dao.impls;

import dev.example.aop.annotations.PrintUser;
import dev.example.dao.interfaces.UserDao;
import dev.example.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    @PrintUser(isPrint = true)
    public User findById(int id) {
        logger.debug("UserDaoImpl DEBUG: findById()");
        logger.info("UserDaoImpl INFO: findById()");
        logger.error("UserDaoImpl ERROR: findById()");
        
        //заглушка
        return new User(id, "Ivan", 100);
    }

    @Override
    @PrintUser
    public User findByName(String name) {
        logger.debug("UserDaoImpl DEBUG: findByName()");
        logger.info("UserDaoImpl INFO: findByName()");
        logger.error("UserDaoImpl ERROR: findByName()");
        
        //заглушка
        return new User(789, name, 100);
    }
}
