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


    private final String GET_ALL_COMPONENTS = "SELECT * FROM components";
    private final String GET_ALL_SOURCES = "SELECT * FROM sources";
    private final String GET_OBJECT_BY_INDEX = "SELECT * FROM objects WHERE id_object = ?";
    private final String GET_COMPONENT_BY_INDEX = "SELECT * FROM components WHERE id_component = ?";
    private final String GET_QUALITY_BY_NAME = "SELECT * FROM sources WHERE name_source = ?";
    private final String GET_SOURCE_BY_INDEX = "SELECT * FROM sources WHERE id_source = ?";
    private final String GET_QUALITY_NAME = "SELECT * FROM production_quality WHERE production_quality.start_value <= ? AND production_quality.end_value >= ?";
    private final String GET_ALL_OBJECTS = "SELECT * FROM object_list";
    private final String INSERT_NEW_OBJECT = "INSERT INTO object_list (name_object) VALUES (?)";
    private final String GET_NUMBER_OF_ROWS = "SELECT COUNT(*) AS total FROM ";









    @Autowired
    private DatabaseConnectionService connectionService;

    public List getObjects() throws SQLException {
        PreparedStatement pstmt = connectionService.getConnection().prepareStatement(GET_ALL_OBJECTS);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<WebObject> objectList = new ArrayList<WebObject>();

        while (rs.next()){
            objectList.add(new WebObject(rs.getString("name_object")));
        }
        return objectList;
    }

    //ready
    public List getComponents() throws SQLException {
        PreparedStatement pstmt = connectionService.getConnection().prepareStatement(GET_ALL_COMPONENTS);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<String> components = new ArrayList<String>();
        while (rs.next()){
            components.add(rs.getString("name_component"));
        }
        return components;
    }

    //ready
    public List getSources() throws SQLException {
        PreparedStatement pstmt = connectionService.getConnection().prepareStatement(GET_ALL_SOURCES);
        ResultSet rs = pstmt.executeQuery();
        ArrayList<String> sources = new ArrayList<String>();
        while (rs.next()){
            sources.add(rs.getString("name_source"));
        }
        return sources;
    }

    //ready
    public String getObject(int index) throws SQLException {
        PreparedStatement pstmt = connectionService.getConnection().prepareStatement(GET_OBJECT_BY_INDEX);
        String object = null;
        pstmt.setInt(1, index);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            object = rs.getString("name_object");
        }
        return object;
    }

    //ready
    public String getComponent(int index) throws SQLException {
        PreparedStatement pstmt = connectionService.getConnection().prepareStatement(GET_COMPONENT_BY_INDEX);
        String component = null;
        pstmt.setInt(1, index);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            component = rs.getString("name_component");
        }
        return component;
    }

    //ready
    public double getSourceQuality(String source) throws SQLException {
        PreparedStatement pstmt = connectionService.getConnection().prepareStatement(GET_QUALITY_BY_NAME);
        double quality = 0;
        pstmt.setString(1, source);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            quality = rs.getDouble("quality");
        }
        return quality;
    }

    public String getSource(int index) throws SQLException {
        PreparedStatement pstmt = connectionService.getConnection().prepareStatement(GET_SOURCE_BY_INDEX);
        String source = null;
        pstmt.setInt(1, index);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            source = rs.getString("name_source");
        }
        return source;
    }

    //ready
    public String getProductionQuality(double value) throws SQLException {
        PreparedStatement pstmt = connectionService.getConnection().prepareStatement(GET_QUALITY_NAME);
        pstmt.setDouble(1, value);
        pstmt.setDouble(2, value);
        ResultSet rs = pstmt.executeQuery();
        String quality = null;
        while (rs.next()){
            quality = rs.getString("name");
        }
        return quality;
    }

    public int getNumberOfRowsInTable(String value) throws SQLException {
        PreparedStatement pstmt = connectionService.getConnection().prepareStatement(GET_NUMBER_OF_ROWS+value);
        pstmt.setString(1, value);
        ResultSet rs = pstmt.executeQuery();
        int result = 0;
        while (rs.next()){
            result = rs.getInt("total");
        }
        return result;
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
        PreparedStatement pstmt = connectionService.getConnection().prepareStatement(INSERT_NEW_OBJECT);
        pstmt.setString(1, object.toString());
        pstmt.execute();
    }

}
