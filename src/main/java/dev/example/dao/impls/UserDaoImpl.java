package dev.example.dao.impls;

import dev.example.dao.interfaces.UserDao;
import dev.example.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    User user;

    @Autowired
    public UserDaoImpl(User user) {
        this.user = user;
    }

    @Override
    public User findById(int id) {
        if (id == user.getId()) {
            return user;
        }

        //заглушка
        return new User(id, "Ivan", 100);
    }
}
