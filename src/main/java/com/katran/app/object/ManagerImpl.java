package com.katran.app.object;

import com.katran.app.database.DAO;
import com.katran.app.database.clazzes.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ManagerImpl implements Manager {

    @Autowired
    private DAO dao;

    public SimpleObject getCompletedSubjectByIndex(int index) {
        return dao.getCompletedSubjectByIndex(index);
    }

    public List<SimpleObject> getListOfCompletedSubjects() {
        return dao.getListOfCompletedSubjects();
    }

    public List<String> getListOfSources() {
        return dao.getListOfSources();
    }

    public List<String> getListOfMaterials() {
        return dao.getListOfMaterials();
    }

    public Integer getNumberOfRowsInTable(String table) {
        return dao.getNumberOfRowsInTable(table);
    }

    public String getMaterialNameByIndex(int index) {
        return dao.getMaterialNameByIndex(index);
    }

    public Integer getMaterialIDByName(String name) {
        return dao.getMaterialIDByName(name);
    }

    public String getSubjectNameByIndex(int index) {
        return dao.getSubjectNameByIndex(index);
    }

    public Integer getSubjectIDByName(String name) {
        return dao.getSubjectIDByName(name);
    }

    public String getQualityNameByIndex(int index) {
        return dao.getQualityNameByIndex(index);
    }

    public Integer getQualityIDByName(String name) {
        return dao.getQualityIDByName(name);
    }

    public String getSourceNameByIndex(int index) {
        return dao.getSourceNameByIndex(index);
    }

    public Integer getSourceIDByName(String name) {
        return dao.getSubjectIDByName(name);
    }

    public Double getSourceQualityByName(String name) {
        return dao.getSourceQualityByName(name);
    }

    public String getProductionQuality(double value) {
        return dao.getProductionQuality(value);
    }

    @Transactional
    public void saveObject(SimpleObject twObject) {
        dao.saveObject(twObject);
    }

    public List<Material> getAllMaterials(){
        return dao.getAllMaterials();
    }

    public Material getMaterial(Integer id){
        return dao.getMaterial(id);
    }

    public Material getMaterial(String name){
        return dao.getMaterial(name);
    }

    public void addMaterial(String material){
        dao.addMaterial(material);
    }

    public Material deleteMaterial(Integer id){
        return dao.deleteMaterial(id);
    }

    public Material deleteMaterial(String name){
        return dao.deleteMaterial(name);
    }

    public void updateMaterial(Integer id, String name) {
        dao.updateMaterial(id, name);
    }
}
