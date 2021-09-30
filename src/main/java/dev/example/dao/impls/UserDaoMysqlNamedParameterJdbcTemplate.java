package dev.example.dao.impls;

import dev.example.dao.interfaces.UserDao;
import dev.example.entities.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class UserDaoMysqlNamedParameterJdbcTemplate implements UserDao {
    public static final Logger LOG = LogManager.getLogger(UserDaoMysqlNamedParameterJdbcTemplate.class);
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public UserDaoMysqlNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void createUserByName(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("role", User.DEF_ROLE);
        namedParameterJdbcTemplate.update(
                "INSERT INTO `users` (`name`, `role`) VALUES (:name, :role)",
                params
        );
    }

    @Override
    public long createUser(String name) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("role", User.DEF_ROLE);

        System.out.println(params);


        namedParameterJdbcTemplate.update(
                "INSERT INTO `users` (`name`, `role`) VALUES (:name, :role)",
                params,
                keyHolder
        );

        Number key = null;
        long lastInsertId = 0L;

        try {
            key = keyHolder.getKey();
        } catch (NullPointerException e) {
            LOG.error("keyHolder.getKey() return null");
        }

        if (key != null) {
            lastInsertId = key.longValue();
        }

        LOG.info(lastInsertId);

        return lastInsertId;
    }

    @Override
    public User findById(long id) {
        return namedParameterJdbcTemplate.getJdbcOperations().query(
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
        return namedParameterJdbcTemplate.getJdbcOperations().query(
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

    public Map<String, Integer> countRoles() {
        return namedParameterJdbcTemplate.query(
                "SELECT `role`, count(*) as count_roles FROM `users` GROUP BY `role`",
                resultSet -> {
                    final TreeMap<String, Integer> map = new TreeMap<>();
                    while (resultSet.next()) {
                        map.put(
                                resultSet.getString("role"),
                                resultSet.getInt("count_roles")
                        );
                    }
                    return map;
                }
        );
    }

    @Override
    public List<User> findAllUsers() {
        return namedParameterJdbcTemplate.getJdbcOperations().query(
                "SELECT * FROM `users`",
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    @Override
    public void truncateUsers() {
        namedParameterJdbcTemplate.getJdbcOperations().update("TRUNCATE TABLE `users`");
    }

    @Override
    public void createUserByObject(User user) {
        var params = new MapSqlParameterSource();
        params.addValue("name", user.getName());
        params.addValue("role", user.getRole());

        namedParameterJdbcTemplate.update(
                "INSERT INTO `users` (`name`, `role`) VALUES (:name, :role)",
                params
        );
    }
}
