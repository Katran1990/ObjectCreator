package com.katran.app.database;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Boris on 30.05.2016.
 */
public class DatabaseConnectionService {

    private String user;
    private String password;
    private String host;

    private Connection connection;

    @PostConstruct
    public void init(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(host, user, password);
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

    public void setHost(String host) {
        this.host = host;
    }

    public Connection getConnection() {
        return connection;
    }

}
