package dev.example.dao.impls;

import dev.example.dao.interfaces.UserDao;
import dev.example.entities.Users;
import dev.example.dto.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoHibernate implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createUserByName(String name) {
//
    }

    @Override
    public long createUser(String name) {
        return 0;
    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public User findByName(String name) {
        return null;
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public void truncateUsers() {
//
    }

    @Override
    public void createUserByObject(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Users users = new Users(
                user.getName(),
                user.getRole()
        );

        session.save(users);
        session.getTransaction().commit();
        session.close();
    }
}
