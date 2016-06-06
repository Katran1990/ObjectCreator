package com.katran.app.database;

import com.katran.app.object.WebObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boris on 30.05.2016.
 */
public class WebObjectDAO {

    private final String INSERT_NEW = "INSERT INTO object_list (name_object) VALUES (?)";
    private final String GET_ALL = "SELECT * FROM object_list";

    @Autowired
    private DatabaseConnectionService connectionService;

    public List getObjects() throws SQLException {
        PreparedStatement pstmt = connectionService.getConnection().prepareStatement(GET_ALL);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<WebObject> objectList = new ArrayList<WebObject>();

        while (rs.next()){
            objectList.add(new WebObject(rs.getString("name_object")));
        }
        return objectList;
    }

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
