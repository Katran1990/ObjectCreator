package com.katran.app.database;

import com.katran.app.database.clazzes.Material;
import com.katran.app.object.SimpleObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DAOImpl implements DAO {

    @Autowired
    private JdbcTemplate template;

    public SimpleObject getCompletedSubjectByIndex(int index){
        return template.queryForObject(
                "SELECT als.id, o.name, pq.name, m.name " +
                "FROM object_list als, objects o, production_quality pq, materials m " +
                "WHERE als.id = ? " +
                "AND als.object = o.id " +
                "AND als.quality=pq.id " +
                "AND als.material=m.id",
                new Object[]{index},
                new SimpleObjectMapper());
    }

    public List<SimpleObject> getListOfCompletedSubjects(){
        return template.query(
        "SELECT als.id, o.name, pq.name, m.name " +
        "FROM object_list als, objects o, production_quality pq, materials m " +
        "WHERE als.object = o.id " +
        "AND als.quality=pq.id " +
        "AND als.material=m.id " +
        "ORDER BY als.id",
        new SimpleObjectMapper());
    }

    public List<String> getListOfSources(){
        return template.queryForList("SELECT name FROM sources", String.class);
    }

    public List<String> getListOfMaterials(){
        return template.queryForList("SELECT name FROM materials", String.class);
    }

    public Integer getNumberOfRowsInTable(String table){
        return template.queryForObject("SELECT COUNT(*) FROM "+ table, Integer.class);
    }

    public String getMaterialNameByIndex(int index){
        return template.queryForObject("SELECT name FROM materials WHERE id = ?", new Object[]{index} ,String.class);
    }

    public Integer getMaterialIDByName(String name){
        return template.queryForObject("SELECT id FROM materials WHERE name = ?", new Object[]{name} ,Integer.class);
    }

    public String getSubjectNameByIndex(int index){
        return template.queryForObject("SELECT name FROM objects WHERE id = ?", new Object[]{index} ,String.class);
    }

    public Integer getSubjectIDByName(String name){
        return template.queryForObject("SELECT id FROM objects WHERE name = ?", new Object[]{name} ,Integer.class);
    }

    public String getQualityNameByIndex(int index){
        return template.queryForObject("SELECT name FROM production_quality WHERE id = ?", new Object[]{index} ,String.class);
    }

    public Integer getQualityIDByName(String name){
        return template.queryForObject("SELECT id FROM production_quality WHERE name = ?", new Object[]{name} ,Integer.class);
    }

    public String getSourceNameByIndex(int index){
        return template.queryForObject("SELECT name FROM sources WHERE id = ?", new Object[]{index} ,String.class);
    }

    public Integer getSourceIDByName(String name){
        return template.queryForObject("SELECT id FROM sources WHERE name = ?", new Object[]{name} ,Integer.class);
    }

    public Double getSourceQualityByName(String name){
        return template.queryForObject("SELECT quality FROM sources WHERE name = ?", new Object[]{name} ,Double.class);
    }

    public String getProductionQuality(double value) {
        return template.queryForObject("SELECT name FROM production_quality WHERE production_quality.start_value <= ? AND production_quality.end_value >= ?", new Object[]{value, value},String.class);
    }

    public void saveObject(SimpleObject twObject){
        template.update(
            "INSERT INTO object_list (object, quality, material) VALUES (?, ?, ?)",
            this.getSubjectIDByName(twObject.getSubject()),
            this.getQualityIDByName(twObject.getQuality()),
            this.getMaterialIDByName(twObject.getMaterial()));
    }

    private class SimpleObjectMapper implements RowMapper<SimpleObject>{
        public SimpleObject mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new SimpleObject(
                    rs.getInt("als.id"),
                    rs.getString("o.name"),
                    rs.getString("pq.name"),
                    rs.getString("m.name"));
        }
    }

    private class MaterialMapper implements RowMapper<Material>{
        public Material mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Material(
                    rs.getInt("materials.id"),
                    rs.getString("materials.name"));
        }
    }

    public List<Material> getAllMaterials(){
        return template.query("SELECT id, name FROM materials ORDER BY id", new MaterialMapper());
    }

    public Material getMaterial(Integer id){
        return template.queryForObject("SELECT id, name FROM materials WHERE id = (?)", new MaterialMapper(), id);
    }

    public Material getMaterial(String name){
        return template.queryForObject("SELECT id, name FROM materials WHERE name = (?)", new MaterialMapper(), name);
    }

    public void addMaterial(String material){
        template.update("INSERT INTO materials (name) VALUES (?)", material);
    }

    public Material deleteMaterial(Integer id) {
        Material material = getMaterial(id);
        template.update("DELETE FROM materials WHERE id = (?)", id);
        return material;
    }

    public Material deleteMaterial(String name) {
        Material material = getMaterial(name);
        template.update("DELETE FROM materials WHERE name = (?)", name);
        return material;
    }
}
