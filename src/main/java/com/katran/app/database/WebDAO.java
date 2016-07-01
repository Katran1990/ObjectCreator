package com.katran.app.database;

import com.katran.app.object.WebObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Boris on 30.06.2016.
 */
public interface WebDAO {
    String GET_ALL_OBJECTS = "SELECT name_object FROM object_list";
    String GET_ALL_COMPONENTS = "SELECT name_component FROM components";
    String GET_ALL_SOURCES = "SELECT name_source FROM sources";
    String GET_OBJECT_BY_INDEX = "SELECT name_object FROM objects WHERE id_object = ?";
    String GET_COMPONENT_BY_INDEX = "SELECT name_component FROM components WHERE id_component = ?";
    String GET_QUALITY_BY_NAME = "SELECT quality FROM sources WHERE name_source = ?";
    String GET_SOURCE_BY_INDEX = "SELECT name_source FROM sources WHERE id_source = ?";
    String GET_QUALITY_NAME = "SELECT name FROM production_quality WHERE production_quality.start_value <= ? AND production_quality.end_value >= ?";
    String GET_NUMBER_OF_ROWS = "SELECT COUNT(*) AS total FROM ";
    String INSERT_NEW_OBJECT = "INSERT INTO object_list (name_object) VALUES (?)";


    public List<String> getObjects() throws SQLException;

    public List<String> getComponents() throws SQLException;

    public List<String> getSources() throws SQLException;

    public String getObject(int index) throws SQLException;

    public String getComponent(int index) throws SQLException;

    public double getSourceQuality(String source) throws SQLException;

    public String getSource(int index) throws SQLException;

    public String getProductionQuality(double value) throws SQLException;

    public int getNumberOfRowsInTable(String value) throws SQLException;

    public void addObject(WebObject object) throws SQLException;
}
