package com.katran.objectcreator;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Boris on 30.05.2016.
 */
public class DatabaseConnectionService {

    private String user;
    private String password;
    private String dbURL;

    private Connection connection;

    @PostConstruct
    public void init(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(dbURL, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

}
