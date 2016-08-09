package com.katran.objectcreator.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boris on 09.08.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/object-creator-common-context.xml")
public class AssemblyServiceTest {

    @Autowired
    private AssemblyService assemblyService;

    @Autowired
    private ObjectManager manager;

    @Test
    public void testGetRandomValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = AssemblyService.class.getDeclaredMethod("getRandomValue", int.class, int.class);
        method.setAccessible(true);
        int i = (Integer) method.invoke(assemblyService, 1, 5);
        assert (i>=1&&i<=5);
    }

    @Test
    public void testDefineTheMaterial() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> materialList = new ArrayList<String>();
        materialList.add("rnd");
        materialList.add("rnd");
        Method method = AssemblyService.class.getDeclaredMethod("defineTheMaterial", List.class);
        method.setAccessible(true);
        String result = (String) method.invoke(assemblyService, materialList);
        materialList = manager.getListOfMaterials();
        assert (materialList.contains(result));
    }

    @Test
    public void testDefineTheQuality() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> qualityList = new ArrayList<String>();
        qualityList.add("excellent");
        qualityList.add("excellent");
        qualityList.add("excellent");
        Method method = AssemblyService.class.getDeclaredMethod("defineTheQuality", List.class);
        method.setAccessible(true);
        Double result = (Double) method.invoke(assemblyService, qualityList);
        assert (1.0==result);
    }

    @Test
    public void testGetMaterialFromList() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> materialList = new ArrayList<String>();
        materialList.add("wood");
        materialList.add("wood");
        Method method = AssemblyService.class.getDeclaredMethod("getMaterialFromList", List.class);
        method.setAccessible(true);
        String result = (String) method.invoke(assemblyService, materialList);
        assert ("wood".equals(result));
    }
}
