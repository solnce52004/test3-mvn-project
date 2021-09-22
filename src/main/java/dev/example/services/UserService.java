package dev.example.services;

import dev.example.dao.interfaces.UserDao;
import dev.example.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
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
