package com.katran.app.database;

import com.katran.app.object.SimpleObject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Boris on 13.07.2016.
 */
public interface SimpleObjectDAO {
    public SimpleObject getCompletedSubjectByIndex(int index);

    public List<SimpleObject> getListOfCompletedSubjects();

    public List<String> getListOfSources();

    public List<String> getListOfMaterials();

    public int getNumberOfRowsInTable(String table);

    public String getMaterialNameByIndex(int index);

    public Integer getMaterialIDByName(String name);

    public String getSubjectNameByIndex(int index);

    public Integer getSubjectIDByName(String name);

    public String getQualityNameByIndex(int index);

    public Integer getQualityIDByName(String name);

    public String getSourceNameByIndex(int index);

    public Integer getSourceIDByName(String name);

    public Double getSourceQualityByName(String name);

    public String getProductionQuality(double value);

    public void saveObject(SimpleObject twObject);
}
