package com.katran.objectcreator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Boris on 30.05.2016.
 */
public class DatabaseConnectionService {

    private String user;
    private String password;
    private String dbURL;
    private Connection connection;

    public DatabaseConnectionService() throws SQLException {
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
