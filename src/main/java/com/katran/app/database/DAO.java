package com.katran.app.database;

import com.katran.app.database.clazzes.Material;
import com.katran.app.object.SimpleObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DAO {
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

    List<Material> getAllMaterials();

    Material getMaterial(Integer id);

    Material getMaterial(String name);

    void addMaterial(String material);

    Material deleteMaterial(Integer id);

    Material deleteMaterial(String name);
}
