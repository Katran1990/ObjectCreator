package com.katran.objectcreator.service;

import com.katran.objectcreator.database.ObjectDAO;
import com.katran.objectcreator.model.SimpleObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "objectManager")
public class ObjectManagerImpl implements ObjectManager {

    @Autowired
    private ObjectDAO dao;

    public SimpleObject getCreatedObjectByID(int index) {
        return dao.getCreatedObjectByID(index);
    }

    public List<SimpleObject> getCreatedObjects() {
        return dao.getCreatedObjects();
    }

    public List<String> getSources() {
        return dao.getSources();
    }

    public List<String> getMaterials() {
        return dao.getMaterials();
    }

    public Integer getTableSize(String table) {
        return dao.getTableSize(table);
    }

    public String getMaterialNameByID(int index) {
        return dao.getMaterialNameByID(index);
    }

    public String getSubjectNameByID(int index) {
        return dao.getSubjectNameByID(index);
    }

    public String getSourceNameByID(int index) {
        return dao.getSourceNameByID(index);
    }

    public Double getSourceQualityByName(String name) {
        return dao.getSourceQualityByName(name);
    }

    public String getProductionQuality(double value) {
        return dao.getProductionQuality(value);
    }

    //TODO
    @Transactional
    public Integer saveObject(SimpleObject twObject) {
        return dao.saveObject(twObject);
    }

    @Transactional
    public Integer updateObject(Integer id, SimpleObject twObject) {
        return dao.updateObject(id, twObject);
    }

    @Transactional
    public void deleteObject(Integer id) {
        dao.deleteObject(id);
    }

}
