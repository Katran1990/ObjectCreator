package com.katran.objectcreator.service;

import com.katran.objectcreator.model.SimpleObject;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
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

    private static final Logger LOGGER = Logger.getLogger(AssemblyService.class);
    private static final String RANDOM_VALUE = "rnd";
    private static final Integer MAX_NUMBER_OF_COMPONENTS = 3;
    private static final Double FINE_MULTIPLIER = 0.25;

    public SimpleObject assemblyOfObject(List<String> materialList, List<String> qualityList) {
        LOGGER.info("assembling of object");
        String resultMaterial = defineTheMaterial(materialList);
        double quality = defineTheQuality(qualityList);
        String resultQuality = manager.getProductionQuality(quality);
        String resultObject = manager.getSubjectNameByID(getRandomValue(1, manager.getTableSize("objects")));
        return new SimpleObject(resultObject, resultQuality, resultMaterial);
    }

    private String defineTheMaterial(List<String> materialList) {
        LOGGER.info("defining type of material");
        String component;
        if (materialList.size() > 1) {
            component = getMaterialFromList(materialList);
        } else {
            component = materialList.get(0);
            if (component.equals(RANDOM_VALUE)) {
                component = manager.getMaterialNameByID(getRandomValue(1, manager.getTableSize("materials")));
            }
        }
        return component;
    }

    private double defineTheQuality(List<String> qualityList) {
        LOGGER.info("defining type of quality");
        double fine = (MAX_NUMBER_OF_COMPONENTS - qualityList.size()) * FINE_MULTIPLIER;
        double qualityOfMaterials = 0;
        for (int i = 0; i < qualityList.size(); i++) {
            if (qualityList.get(i).equals(RANDOM_VALUE)) {
                qualityList.set(i, manager.getSourceNameByID(getRandomValue(1, manager.getTableSize("sources"))));
            }
            qualityOfMaterials += manager.getSourceQualityByName(qualityList.get(i));
        }
        qualityOfMaterials = qualityOfMaterials / qualityList.size();
        double preparedQuality = (1 - fine) * qualityOfMaterials;
        return new BigDecimal(preparedQuality).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private int getRandomValue(int startRange, int endRange) {
        return (startRange + (int) (Math.random() * ((endRange - startRange) + 1)));
    }

    private String getMaterialFromList(List<String> materialList) {
        LOGGER.info("getting type of material from list");
        List<String> preparedMaterials = new ArrayList<>();

        for (int i = 0; i < materialList.size(); i++) {
            String element = materialList.get(i);
            if (element.equals(RANDOM_VALUE)) {
                element = manager.getMaterialNameByID(getRandomValue(1, manager.getTableSize("materials")));
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