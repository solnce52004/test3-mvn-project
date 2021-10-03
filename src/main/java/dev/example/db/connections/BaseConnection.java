package dev.example.db.connections;

import java.sql.Connection;

public interface BaseConnection {
    static final String PROJECT_DB = "test3_mvn_project";
    Connection getConnect(String db);
    boolean closeConnect();
}
