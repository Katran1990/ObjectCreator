package com.katran.objectcreator.service;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service(value = "weatherService")
public class WeatherService {
    private static final String APIID = "c8dfeb9c92d335d8b9dde435c86d5dd3";

    public CurrentWeather getWeather() throws IOException, JSONException {
        try {
            OpenWeatherMap owm = new OpenWeatherMap(APIID);
            owm.setUnits(OpenWeatherMap.Units.METRIC);
            return owm.currentWeatherByCityName("Odessa", "UA");
        } catch (IOException | JSONException e) {
            return null;
        }
    }
}
