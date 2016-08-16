package com.katran.objectcreator.service;

import net.aksingh.owmjapis.OpenWeatherMap;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.Method;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/object-creator-common-context.xml")
public class WeatherServiceTest {

    @Autowired
    WeatherService weatherService;

    @Test
    public void testGetWeather() throws IOException {
        assertEquals("Odessa", weatherService.getWeather().getCityName());
    }

}
