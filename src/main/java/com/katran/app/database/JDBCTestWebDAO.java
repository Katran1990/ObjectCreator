package com.katran.app.database;

import com.katran.app.object.TestWebObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Boris on 05.07.2016.
 */
public class JDBCTestWebDAO {

    @Autowired
    public JdbcTemplate template;

    public TestWebObject getCompletedSubjectByIndex(int index){
        return template.queryForObject(
                "SELECT als.id, s.name, pq.name, m.name " +
                "FROM all_subjects als, subjects s, production_quality pq, materials m " +
                "WHERE als.id = ? " +
                "AND als.object = s.id " +
                "AND als.quality=pq.id " +
                "AND als.material=m.id",
                new Object[]{index},
                new WebObjectRowMapper());
    }

    public List<TestWebObject> getListOfCompletedSubjects(){
        return template.query(
        "SELECT als.id, s.name, pq.name, m.name " +
        "FROM all_subjects als, subjects s, production_quality pq, materials m " +
        "WHERE als.object = s.id " +
        "AND als.quality=pq.id " +
        "AND als.material=m.id",
        new WebObjectRowMapper());
    }

    public List<String> getListOfSources(){
        return template.queryForList("SELECT name FROM subsources", String.class);
    }

    public List<String> getListOfMaterials(){
        return template.queryForList("SELECT name FROM materials", String.class);
    }

    public int getNumberOfRowsInTable(String table){
        return template.queryForObject("SELECT COUNT(*) FROM "+ table, Integer.class);
    }

    public String getMaterialNameByIndex(int index){
        return template.queryForObject("SELECT name FROM materials WHERE id = ?", new Object[]{index} ,String.class);
    }

    public Integer getMaterialIDByName(String name){
        return template.queryForObject("SELECT id FROM materials WHERE name = ?", new Object[]{name} ,Integer.class);
    }

    public String getSubjectNameByIndex(int index){
        return template.queryForObject("SELECT name FROM subjects WHERE id = ?", new Object[]{index} ,String.class);
    }

    public Integer getSubjectIDByName(String name){
        return template.queryForObject("SELECT id FROM subjects WHERE name = ?", new Object[]{name} ,Integer.class);
    }

    public String getQualityNameByIndex(int index){
        return template.queryForObject("SELECT name FROM production_quality WHERE id = ?", new Object[]{index} ,String.class);
    }

    public Integer getQualityIDByName(String name){
        return template.queryForObject("SELECT id FROM production_quality WHERE name = ?", new Object[]{name} ,Integer.class);
    }

    public String getSourceNameByIndex(int index){
        return template.queryForObject("SELECT name FROM subsources WHERE id = ?", new Object[]{index} ,String.class);
    }

    public Integer getSourceIDByName(String name){
        return template.queryForObject("SELECT id FROM subsources WHERE name = ?", new Object[]{name} ,Integer.class);
    }

    public Double getSourceQualityByName(String name){
        return template.queryForObject("SELECT quality FROM subsources WHERE name = ?", new Object[]{name} ,Double.class);
    }

    public String getProductionQuality(double value) {
        return template.queryForObject("SELECT name FROM production_quality WHERE production_quality.start_value <= ? AND production_quality.end_value >= ?", new Object[]{value, value},String.class);
    }


    public void saveObject(TestWebObject twObject){
        template.update(
                "INSERT INTO all_subjects (object, quality, material) VALUES (?, ?, ?)",
                this.getSubjectIDByName(twObject.getSubject()),
                this.getQualityIDByName(twObject.getQuality()),
                this.getMaterialIDByName(twObject.getMaterial()));
    }




}
