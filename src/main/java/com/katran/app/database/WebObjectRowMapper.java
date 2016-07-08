package com.katran.app.database;

import com.katran.app.object.TestWebObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Boris on 06.07.2016.
 */
public class WebObjectRowMapper implements RowMapper<TestWebObject> {
    public TestWebObject mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TestWebObject(
                rs.getInt("als.id"),
                rs.getString("s.name"),
                rs.getString("pq.name"),
                rs.getString("m.name"));
    }
}
