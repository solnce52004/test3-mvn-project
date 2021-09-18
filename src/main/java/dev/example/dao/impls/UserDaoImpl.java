package dev.example.dao.impls;

import dev.example.dao.interfaces.UserDao;
import dev.example.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public User findById(int id) {
        //заглушка
        return new User(id, "Ivan", 100);
    }
}
