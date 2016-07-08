package com.katran.app.database;

import com.katran.app.object.SimpleObject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Boris on 06.07.2016.
 */
public class SimpleObjectRowMapper implements RowMapper<SimpleObject> {
    public SimpleObject mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SimpleObject(
                rs.getInt("als.id"),
                rs.getString("s.name"),
                rs.getString("pq.name"),
                rs.getString("m.name"));
    }
}
