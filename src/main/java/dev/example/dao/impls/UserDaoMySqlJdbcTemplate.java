package dev.example.dao.impls;

import dev.example.dao.interfaces.UserDao;
import dev.example.dto.User;
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
    public long createUser(String name) {
//
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(
//                "INSERT INTO `users` (`name`, `role`) VALUES (?, ?)",
//                name, User.DEF_ROLE
//        );
        return 0;
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
                (resultSet, i) -> {
                    final var user = new User();
                    user.setId(resultSet.getLong(1));
                    user.setName(resultSet.getString(2));
                    user.setRole(resultSet.getString(3));
                    return user;
                },
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

    @Override
    public void truncateUsers() {
        jdbcTemplate.update("TRUNCATE TABLE `users`");
    }

    @Override
    public void createUserByObject(User user) {
        //
    }
}
