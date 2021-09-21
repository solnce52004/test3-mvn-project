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
        return new User(id, "Ivan", 100);
    }

    @Override
    @PrintUser
    public User findByName(String name) {
        //заглушка
        return new User(789, name, 100);
    }
}
