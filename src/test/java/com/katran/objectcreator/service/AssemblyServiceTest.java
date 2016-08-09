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

/**
 * Created by Boris on 09.08.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/object-creator-common-context.xml")
public class AssemblyServiceTest {

    @Autowired
    private AssemblyService assemblyService;

    @Test
    public void testGetRandomValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = AssemblyService.class.getDeclaredMethod("getRandomValue", int.class, int.class);
        method.setAccessible(true);
        int i = (Integer) method.invoke(assemblyService, 1, 5);
        assert (i>=1&&i<=5);
    }
}
