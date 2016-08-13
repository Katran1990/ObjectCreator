package com.katran.objectcreator.database;

import com.katran.objectcreator.model.Material;
import com.katran.objectcreator.model.SimpleObject;

import java.util.List;

public interface ObjectDAO{
    SimpleObject getCompletedSubjectByIndex(int index);

    List<SimpleObject> getListOfCompletedSubjects();

    List<String> getListOfSources();

    List<String> getListOfMaterials();

    Integer getNumberOfRowsInTable(String table);

    String getMaterialNameByIndex(int index);

    Integer getMaterialIDByName(String name);

    String getSubjectNameByIndex(int index);

    Integer getSubjectIDByName(String name);

    String getQualityNameByIndex(int index);

    Integer getQualityIDByName(String name);

    String getSourceNameByIndex(int index);

    Integer getSourceIDByName(String name);

    Double getSourceQualityByName(String name);

    String getProductionQuality(double value);

    void saveObject(SimpleObject twObject);

    void updateObject(Integer id, SimpleObject twObject);

    void deleteObject(Integer id);

    void deleteAllObjects();

    List<Material> getAllMaterials();

    Material getMaterial(Integer id);

    Material getMaterial(String name);

    void addMaterial(String material);

    Material deleteMaterial(Integer id);

    Material deleteMaterial(String name);

    void updateMaterial(Integer id, String name);
}
