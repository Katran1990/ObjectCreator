package com.katran.app.database;

import com.katran.app.object.WebObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Boris on 30.06.2016.
 */
public class JDBCWebDAO implements WebDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<String> getObjects() throws SQLException {
        return jdbcTemplate.queryForList(GET_ALL_OBJECTS, String.class);
    }

    public List<String> getComponents() throws SQLException {
        return jdbcTemplate.queryForList(GET_ALL_COMPONENTS, String.class);
    }

    public List<String> getSources() throws SQLException {
        return jdbcTemplate.queryForList(GET_ALL_SOURCES, String.class);
    }

    public String getObject(int index) throws SQLException {
        return jdbcTemplate.queryForObject(GET_OBJECT_BY_INDEX, String.class, index);
    }

    public String getComponent(int index) throws SQLException {
        return jdbcTemplate.queryForObject(GET_COMPONENT_BY_INDEX, String.class, index);
    }

    public double getSourceQuality(String source) throws SQLException {
        return jdbcTemplate.queryForObject(GET_QUALITY_BY_NAME, Double.class, source);
    }

    public String getSource(int index) throws SQLException {
        return jdbcTemplate.queryForObject(GET_SOURCE_BY_INDEX, String.class, index);
    }

    public String getProductionQuality(double value) throws SQLException {
        String sql = "SELECT name FROM production_quality WHERE production_quality.start_value <= "+value+" AND production_quality.end_value >= "+value;
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    public int getNumberOfRowsInTable(String value) throws SQLException {
        return jdbcTemplate.queryForObject(GET_NUMBER_OF_ROWS+value, Integer.class);
    }

    public void addObject(WebObject object) throws SQLException {
        jdbcTemplate.update(INSERT_NEW_OBJECT, object.toString());
    }
}
