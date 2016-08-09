package com.katran.objectcreator.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service(value = "weatherService")
public class WeatherService {
    public Map<String, Object> getWeather() throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://api.openweathermap.org/data/2.5/weather?id=698740&units=metric&APPID=c8dfeb9c92d335d8b9dde435c86d5dd3");
        httpGet.addHeader("User-Agent", "Chrome/52.0.2743.116");
        CloseableHttpResponse response = client.execute(httpGet);
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        String inputLine;
        StringBuffer responseJson = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            responseJson.append(inputLine);
        }
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(responseJson.toString()).getAsJsonObject();
        reader.close();
        client.close();
        result.put("city", jsonObject.get("name").getAsString());
        result.put("temp", jsonObject.get("main").getAsJsonObject().get("temp").getAsString());
        result.put("wind", jsonObject.get("wind").getAsJsonObject().get("speed").getAsDouble());
        return result;
    }
}