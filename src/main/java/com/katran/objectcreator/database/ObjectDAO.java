package com.katran.objectcreator.database;

import com.katran.objectcreator.model.Material;
import com.katran.objectcreator.model.SimpleObject;

import java.util.List;

public interface ObjectDAO {
    SimpleObject getCreatedObjectByID(int id);

    //TODO delete list
    List<SimpleObject> getCreatedObjects();

    List<String> getSources();

    List<String> getMaterials();

    Integer getTableSize(String name);

    String getMaterialNameByID(int id);

    Integer getMaterialIDByName(String name);

    String getSubjectNameByID(int id);

    Integer getSubjectIDByName(String name);

    Integer getQualityIDByName(String name);

    String getSourceNameByID(int id);

    Double getSourceQualityByName(String name);

    String getProductionQuality(double value);

    Integer saveObject(SimpleObject twObject);

    Integer updateObject(Integer id, SimpleObject twObject);

    void deleteObject(Integer id);
}
