package dev.example.dao.impls;

import dev.example.dao.interfaces.UserDao;
import dev.example.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoMySqlJdbcTemplate implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoMySqlJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createUserByName(String name) {
        jdbcTemplate.update(
                "INSERT INTO `users` (`name`, `role`) VALUES (?, ?)",
                name, User.DEF_ROLE
        );
    }

    @Override
    public User findById(long id) {
        return jdbcTemplate.query(
                "SELECT * FROM `users` WHERE id = ?",
                new BeanPropertyRowMapper<>(User.class),
                id
        )
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public User findByName(String name) {
        return jdbcTemplate.query(
                "SELECT * FROM `users` WHERE `name` = ? LIMIT 1",
                new BeanPropertyRowMapper<>(User.class),
                name
        )
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return jdbcTemplate.query(
                "SELECT * FROM `users`",
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    public void truncateUsers() {
        jdbcTemplate.update("TRUNCATE TABLE `users`");
    }

    /**
     * @deprecated
     * @see #createUserByName(String name)
     */
    @Override
    @Deprecated(forRemoval = false)
    public long createUser(String name) {
        return 0;
    }
}
