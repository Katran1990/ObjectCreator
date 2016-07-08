package com.katran.app.object;

import com.katran.app.database.JDBCSimpleObjectDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boris on 25.05.2016.
 */

public class ObjectAssemblyService {
    @Autowired
    private JDBCSimpleObjectDAO dao;

    //ready
    public SimpleObject assemblyOfObject(List<String> components, List<String> sources) throws SQLException {
        String component = defineTheComponent(components);
        double quality = defineTheQuality(sources);
        String generalQuality = dao.getProductionQuality(quality);
        String object = dao.getSubjectNameByIndex(getRandomValue(1, dao.getNumberOfRowsInTable("subjects")));
        return new SimpleObject(object, generalQuality, component);

    }

    //ready
    private String defineTheComponent(List<String> components) throws SQLException {
        String component;

        if (components.size() > 1) {
            component = getComponentFromList(components);
        } else {
            component = components.get(0);
            if (component.equals("rnd")) {
                component = dao.getMaterialNameByIndex(getRandomValue(1, dao.getNumberOfRowsInTable("materials")));
            }
        }
        return component;
    }

    //ready
    private double defineTheQuality(List<String> sources) throws SQLException {
        double fine = (3 - sources.size()) * 0.25;
        double qualityOfComponents = 0;
        for (int i = 0; i < sources.size(); i++) {
            if (sources.get(i).equals("rnd")) {
                sources.set(i, dao.getSourceNameByIndex(getRandomValue(1, dao.getNumberOfRowsInTable("subsources"))));
            }
            qualityOfComponents+= dao.getSourceQualityByName(sources.get(i));
        }
        qualityOfComponents = qualityOfComponents / sources.size();
        return (1 - fine) * qualityOfComponents;
    }

    //ready
    private int getRandomValue(int beginRange, int endRange) {
        return (beginRange + (int) (Math.random() * ((endRange - beginRange) + 1)));
    }

    //ready
    private String getComponentFromList(List<String> components) throws SQLException {

        List<String> preparedComponents = new ArrayList<String>();

        for (int i = 0; i < components.size(); i++) {
            String element = components.get(i);
            if (element.equals("rnd")) {
                element = dao.getMaterialNameByIndex(getRandomValue(1, dao.getNumberOfRowsInTable("materials")));
            }
            preparedComponents.add(element);
        }

        int minCount = 0;
        String component = null;

        for (int i = 0; i < preparedComponents.size(); i++) {
            int maxCount = 0;
            for (int j = i + 1; j < preparedComponents.size(); j++) {
                if (preparedComponents.get(i).equals(preparedComponents.get(j))) {
                    maxCount++;
                }
            }
            if (maxCount > minCount) {
                minCount = maxCount;
                component = preparedComponents.get(i);
            }
            if (minCount==0){
                component = preparedComponents.get(getRandomValue(0,preparedComponents.size()-1));
            }
        }
        return component;
    }


}