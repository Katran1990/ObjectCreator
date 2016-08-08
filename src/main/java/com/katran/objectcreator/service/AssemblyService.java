package com.katran.objectcreator.service;

import com.katran.objectcreator.model.SimpleObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "assemblyService")
public class AssemblyService {

    @Autowired
    private ObjectManager manager;

    private static final String RANDOM = "rnd";
    private static final Integer MAX_NUMBER_OF_COMPONENTS = 3;
    private static final Double FINE_MULTIPLIER = 0.25;

    public SimpleObject assemblyOfObject(List<String> materialList, List<String> qualityList) throws SQLException {
        String resultMaterial = defineTheMaterial(materialList);
        double quality = defineTheQuality(qualityList);
        String resultQuality = manager.getProductionQuality(quality);
        String resultObject = manager.getSubjectNameByIndex(getRandomValue(1, manager.getNumberOfRowsInTable("objects")));
        return new SimpleObject(resultObject, resultQuality, resultMaterial);
    }

    private String defineTheMaterial(List<String> components) throws SQLException {
        String component;
        if (components.size() > 1) {
            component = getMaterialFromList(components);
        } else {
            component = components.get(0);
            if (component.equals(RANDOM)) {
                component = manager.getMaterialNameByIndex(getRandomValue(1, manager.getNumberOfRowsInTable("materials")));
            }
        }
        return component;
    }

    private double defineTheQuality(List<String> sources) throws SQLException {
        double fine = (MAX_NUMBER_OF_COMPONENTS - sources.size()) * FINE_MULTIPLIER;
        double qualityOfMaterials = 0;
        for (int i = 0; i < sources.size(); i++) {
            if (sources.get(i).equals(RANDOM)) {
                sources.set(i, manager.getSourceNameByIndex(getRandomValue(1, manager.getNumberOfRowsInTable("sources"))));
            }
            qualityOfMaterials += manager.getSourceQualityByName(sources.get(i));
        }
        qualityOfMaterials = qualityOfMaterials / sources.size();
        double preparedQuality = (1 - fine) * qualityOfMaterials;
        return new BigDecimal(preparedQuality).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private int getRandomValue(int startRange, int endRange) {
        return (startRange + (int) (Math.random() * ((endRange - startRange) + 1)));
    }

    private String getMaterialFromList(List<String> materialList) throws SQLException {
        List<String> preparedMaterials = new ArrayList<String>();

        for (int i = 0; i < materialList.size(); i++) {
            String element = materialList.get(i);
            if (element.equals(RANDOM)) {
                element = manager.getMaterialNameByIndex(getRandomValue(1, manager.getNumberOfRowsInTable("materials")));
            }
            preparedMaterials.add(element);
        }

        int minCount = 0;
        String resultMaterial = null;

        for (int i = 0; i < preparedMaterials.size(); i++) {
            int maxCount = 0;
            for (int j = i + 1; j < preparedMaterials.size(); j++) {
                if (preparedMaterials.get(i).equals(preparedMaterials.get(j))) {
                    maxCount++;
                }
            }
            if (maxCount > minCount) {
                minCount = maxCount;
                resultMaterial = preparedMaterials.get(i);
            }
            if (minCount == 0) {
                resultMaterial = preparedMaterials.get(getRandomValue(0, preparedMaterials.size() - 1));
            }
        }
        return resultMaterial;
    }
}