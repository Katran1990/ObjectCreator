package com.katran.app.database;

import com.katran.app.object.WebObject;
import org.springframework.beans.factory.annotation.Autowired;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Boris on 30.05.2016.
 */
public class WebObjectDAO {

    private final String INSERT_NEW = "INSERT INTO object_list (name_object) VALUES (?)";

    @Autowired
    private DatabaseConnectionService connectionService;

//    public List getObjects() {
//        return null;
//    }

//    public WebObject getObject(int id) {
//        return null;
//    }

//    public void updateObject(WebObject object) {
//
//    }

//    public void deleteObject(WebObject object) {
//
//    }

    public void addObject(WebObject object) throws SQLException {
        PreparedStatement pstmt = connectionService.getConnection().prepareStatement(INSERT_NEW);
        pstmt.setString(1, object.toString());
        pstmt.execute();

    }

}
