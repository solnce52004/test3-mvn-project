package dev.example.dao.impls;

import dev.example.dao.interfaces.UserDao;
import dev.example.db.connections.BaseConnection;
import dev.example.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class UserDaoMySqlJdbc implements UserDao {

    private final Connection connect;

    @Autowired
    public UserDaoMySqlJdbc(
            @Qualifier("jdbcMySqlConnection") BaseConnection mySqlConnect,
            @Value("${prod_db}") String db
    ) {
        this.connect = mySqlConnect.getConnect(db);
    }

    /**
     * метод написан в академических целях естессно (как и весь данный package, и проект)))
     */
    public long dirtyCreateUser(String name) {
        Statement statement = null;
        long returnedKey = 0L;

        try {
            statement = connect.createStatement();
            statement.executeUpdate(
                    "INSERT INTO `users` (`name`, `role`)" +
                            " VALUES ('" + name + "', '" + User.DEF_ROLE + "')",//'именно одинарные
                    Statement.RETURN_GENERATED_KEYS
            );
            ResultSet resultSet = statement.getGeneratedKeys();
            returnedKey = resultSet.next()
                    ? resultSet.getLong(1)
                    : 0L;

            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
//            mySqlConnect.closeConnect();
        }

        return returnedKey;
    }

    @Override
    public void createUserByName(String name) {
        //
    }

    @Override
    public long createUser(String name) {
        PreparedStatement preparedStatement = null;
        long returnedKey = 0L;

        try {
            preparedStatement = connect.prepareStatement(
                    "INSERT INTO `users` (`name`, `role`) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, User.DEF_ROLE);
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            returnedKey = resultSet.next()
                    ? resultSet.getLong(1)
                    : 0L;

            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
//            mySqlConnect.closeConnect();
        }

        return returnedKey;
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
        //
    }
}
