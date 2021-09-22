package dev.example.dao.impls;

import dev.example.aop.annotations.PrintUser;
import dev.example.dao.interfaces.UserDao;
import dev.example.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    @PrintUser(isPrint = true)
    public User findById(int id) {
        //заглушка
        return new User(id, User.DEF_NAME, User.DEF_AGE);
    }

    @Override
    @PrintUser
    public User findByName(String name) {
        //заглушка
        return new User(User.DEF_ID, name, User.DEF_AGE);
    }
}
