package com.katran.objectcreator.service;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service(value = "weatherService")
public class WeatherService {
    public static final Logger LOGGER = Logger.getLogger(WeatherService.class);

    @Autowired
    private OpenWeatherMap owm;

    public CurrentWeather getWeather(){
        LOGGER.info("getting weather data from openweathermap.org");
        try {
            return owm.currentWeatherByCityName("Odessa", "UA");
        } catch (IOException | JSONException e) {
            LOGGER.error(e.getMessage(), e.getCause());
            return null;
        }
    }
}
