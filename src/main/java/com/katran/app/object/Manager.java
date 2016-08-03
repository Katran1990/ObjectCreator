package com.katran.app.object;

import java.util.List;

public interface Manager {
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
}
