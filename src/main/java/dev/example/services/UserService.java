package dev.example.services;

import dev.example.dao.interfaces.UserDao;
import dev.example.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger LOG = LogManager.getLogger(UserService.class);
    private final UserDao userDao;


    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;

        // для проверки аспектов,
        // требуется непосредстверный вызов отслеживаемых методов
        // (в данном случае еще и помеченных кастомной аннотацией)
        try {
            LOG.info(this.findUserByName("hgfjghjhgjh"));
            LOG.info(this.findUserByName("Robbert"));
            LOG.info(this.findUserById(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User findUserById(long id) throws Exception {
        if (id <= 0L) {
            throw new IllegalArgumentException();
        }

        return userDao.findById(id);
    }

    public User findUserByName(String name) throws Exception {
        if (name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return userDao.findByName(name);
    }

    public boolean checkUserPresence(User user) throws Exception{
        final User byName = !user.getName().isEmpty()
                ? userDao.findByName(user.getName())
                : userDao.findById(user.getId());

        return byName != null;
    }
}
