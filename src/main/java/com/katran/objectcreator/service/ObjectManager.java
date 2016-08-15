package com.katran.objectcreator.service;

import com.katran.objectcreator.model.Material;
import com.katran.objectcreator.model.SimpleObject;

import java.util.List;

public interface ObjectManager {
    SimpleObject getCreatedObjectByID(int id);

    List<SimpleObject> getCreatedObjects();

    List<String> getSources();

    List<String> getMaterials();

    Integer getTableSize(String table);

    String getMaterialNameByID(int id);

    String getSubjectNameByID(int id);

    String getSourceNameByID(int id);

    Double getSourceQualityByName(String name);

    String getProductionQuality(double value);

    Integer saveObject(SimpleObject newObject);

    Integer updateObject(Integer id, SimpleObject twObject);

    void deleteObject(Integer id);
}
