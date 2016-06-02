package com.katran.objectcreator;

import org.springframework.beans.factory.annotation.Autowired;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Boris on 30.05.2016.
 */
public class ObjectDAO {

    @Autowired
    private DatabaseConnectionService connectionService;

//    public List getObjects() {
//        return null;
//    }

//    public AssembledObject getObject(int id) {
//        return null;
//    }

//    public void updateObject(AssembledObject object) {
//
//    }

//    public void deleteObject(AssembledObject object) {
//
//    }

    public void addObject(String object) throws SQLException {
        String query = "INSERT INTO created_object_list(id_object, object_name) VALUES ('4', '"+object+"')";
        Statement statement = connectionService.getConnection().createStatement();
        statement.execute(query);
        statement.close();
    }

}
