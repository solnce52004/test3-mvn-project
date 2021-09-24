package dev.example.db.connections.jdbc;

import dev.example.db.connections.BaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class JdbcMySqlConnection implements BaseConnection {
    public static final Logger LOG = LogManager.getLogger(JdbcMySqlConnection.class);
    private Connection connect = null;

    @Override
    public Connection getConnect(String db) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/"
                            + db
                            + "?characterEncoding=utf8",
                    "root",
                    "Zerkalo82"
            );
            LOG.info("Подключение установлено. База: {}", db );
        } catch (ClassNotFoundException e) {
            LOG.error("Драйвер не найден");
        } catch (SQLException e) {
            LOG.error("Ошибка допключения к базе");
            e.getErrorCode();
            e.printStackTrace();
        }

        return connect;
    }

    @Override
    public boolean closeConnect() {
        if (connect != null) {
            try {
                connect.close();
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
