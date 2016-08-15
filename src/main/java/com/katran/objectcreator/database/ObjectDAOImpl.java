package com.katran.objectcreator.database;

import com.katran.objectcreator.model.SimpleObject;
import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository(value = "objectDao")
public class ObjectDAOImpl implements ObjectDAO {
    private static final Logger LOGGER = Logger.getLogger(ObjectDAOImpl.class);

    @Autowired
    private JdbcTemplate template;

    public SimpleObject getCreatedObjectByID(int id) {
        try {
            return template.queryForObject(
                    "SELECT als.id, o.name, pq.name, m.name " +
                            "FROM object_list als, objects o, production_quality pq, materials m " +
                            "WHERE als.id = ? " +
                            "AND als.object = o.id " +
                            "AND als.quality=pq.id " +
                            "AND als.material=m.id",
                    new Object[]{id},
                    new SimpleObjectMapper()
            );
        } catch (DataAccessException e) {
            return null;
        }
    }

    public List<SimpleObject> getCreatedObjects() {
        return template.query(
                "SELECT als.id, o.name, pq.name, m.name " +
                        "FROM object_list als, objects o, production_quality pq, materials m " +
                        "WHERE als.object = o.id " +
                        "AND als.quality=pq.id " +
                        "AND als.material=m.id " +
                        "ORDER BY als.id",
                new SimpleObjectMapper()
        );
    }

    public List<String> getSources() {
        return template.queryForList("SELECT name FROM sources", String.class);
    }

    public List<String> getMaterials() {
        return template.queryForList("SELECT name FROM materials", String.class);
    }

    public Integer getTableSize(String table) {
        return template.queryForObject("SELECT COUNT(*) FROM " + table, Integer.class);
    }

    public String getMaterialNameByID(int id) {
        return template.queryForObject("SELECT name FROM materials WHERE id = ?", new Object[]{id}, String.class);
    }

    public String getSubjectNameByID(int id) {
        return template.queryForObject("SELECT name FROM objects WHERE id = ?", new Object[]{id}, String.class);
    }

    public String getSourceNameByID(int id) {
        return template.queryForObject("SELECT name FROM sources WHERE id = ?", new Object[]{id}, String.class);
    }

    public Double getSourceQualityByName(String name) {
        return template.queryForObject("SELECT quality FROM sources WHERE name = ?", new Object[]{name}, Double.class);
    }

    public String getProductionQuality(double value) {
        return template.queryForObject("SELECT name FROM production_quality WHERE production_quality.start_value <= ? AND production_quality.end_value >= ?", new Object[]{value, value}, String.class);
    }

    public Integer saveObject(SimpleObject newObject) {
        NDC.push("saving the object to database");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String select = "SELECT o.id AS object, pq.id AS quality, m.id AS material " +
                "FROM objects AS o, production_quality AS pq, materials AS m " +
                "WHERE o.name = (?) " +
                "AND pq.name = (?) " +
                "AND m.name = (?)";
        try {
            Map<String, Object> result = template.queryForMap(select, newObject.getSubject(), newObject.getQuality(), newObject.getMaterial());
            template.update(con -> {
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO object_list (object, quality, material) VALUES (?, ?, ?)", new String[]{"id"});
                pstmt.setInt(1, (Integer) result.get("object"));
                pstmt.setInt(2, (Integer) result.get("quality"));
                pstmt.setInt(3, (Integer) result.get("material"));
                return pstmt;
            }, keyHolder);
            return keyHolder.getKey().intValue();
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            NDC.pop();
            return -1;
        }
    }

    public Integer updateObject(Integer id, SimpleObject twObject) {
        final String select = "SELECT o.id AS object, s.id AS quality, m.id AS material " +
                "FROM objects AS o, sources AS s, materials AS m " +
                "WHERE o.name = (?) " +
                "AND s.name = (?) " +
                "AND m.name = (?)";
        try {
            Map<String, Object> result = template.queryForMap(select, twObject.getSubject(), twObject.getQuality(), twObject.getMaterial());
            template.update("UPDATE object_list SET object = (?), quality = (?), material = (?) WHERE id = (?)", result.get("object"), result.get("quality"), result.get("material"), id);
            return id;
        } catch (DataAccessException e) {
            return -1;
        }
    }

    public void deleteObject(Integer id) {
        template.update("DELETE FROM object_list WHERE id = (?)", id);
    }

    private class SimpleObjectMapper implements RowMapper<SimpleObject> {
        public SimpleObject mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new SimpleObject(
                    rs.getInt("als.id"),
                    rs.getString("o.name"),
                    rs.getString("pq.name"),
                    rs.getString("m.name"));
        }
    }
}