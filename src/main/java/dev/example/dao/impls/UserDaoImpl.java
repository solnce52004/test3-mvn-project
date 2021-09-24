package dev.example.dao.impls;

import dev.example.aop.annotations.PrintUser;
import dev.example.dao.interfaces.UserDao;
import dev.example.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

//    private static final Logger LOG = LogManager.getLogger(UserDaoImpl.class);
    private List<User> users = new ArrayList<>();

    public UserDaoImpl() {
        this.users.add(User.getDefaultUser());
        this.users.add(new User(2L, "Robbert", "admin"));
        this.users.add(new User(3L, "Patric", "support"));
    }

    @Override
    public void createUserByName(String name) {
        //
    }

    @Override
    public long createUser(String name) {
        final User user = new User(name);
        users.add(user);

        return user.getId();
    }

    @Override
    @PrintUser(isPrint = true)
    public User findById(long id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    @PrintUser
    public User findByName(String name) {
        return users.stream()
                .filter(u -> u.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return users;
    }

    @Override
    public void truncateUsers() {
        //
    }

    @Override
    public void createUserByObject(User user) {
        //
    }
}
