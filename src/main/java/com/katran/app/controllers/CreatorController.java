package com.katran.app.controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.katran.app.object.AssemblyService;
import com.katran.app.object.Manager;
import com.katran.app.object.SimpleObject;
import com.katran.app.object.WeatherService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BufferedHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class CreatorController {

    @Autowired
    private AssemblyService assemblyService;

    @Autowired
    private Manager manager;

    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/")
    public ModelAndView welcome() throws Exception {
        ModelAndView model = new ModelAndView("index");
        Map<String, Object> weatherData = weatherService.getWeather();
        model.addObject("city", weatherData.get("city"));
        model.addObject("temp", weatherData.get("temp"));
        model.addObject("wind", weatherData.get("wind"));
        return model;
    }


    @GetMapping(value = "/creator")
    public ModelAndView getCreatorForm(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("creator");
        List<String> components = manager.getListOfMaterials();
        List<String> sources = manager.getListOfSources();
        model.addObject("components", components);
        model.addObject("sources", sources);


        HttpSession session = request.getSession();
        Object error = session.getAttribute("error");
        if (error != null) {
            model.addObject("error", error);
            session.removeAttribute("error");
        }

        Object createdObject = session.getAttribute("createdObject");
        if (createdObject != null) {
            model.addObject("createdObject", createdObject);
            session.removeAttribute("createdObject");
        }
        return model;
    }

    //
    @PostMapping(value = "/creator")
    public String submitCreatorForm(@RequestParam Map<String, String> req, HttpServletRequest request) throws ServletException, IOException {
        List<String> components = new ArrayList<String>();
        List<String> sources = new ArrayList<String>();
        SimpleObject createdSimpleObject = null;
        String component;
        component = req.get("component1");
        if (component != null) {
            components.add(component);
            sources.add(req.get("source1"));
        }
        component = req.get("component2");
        if (component != null) {
            components.add(component);
            sources.add(req.get("source2"));
        }
        component = req.get("component3");
        if (component != null) {
            components.add(component);
            sources.add(req.get("source3"));
        }

        HttpSession session = request.getSession();
        if (components.size() > 0) {
            try {
                createdSimpleObject = assemblyService.assemblyOfObject(components, sources);
                manager.saveObject(createdSimpleObject);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            session.setAttribute("createdObject", createdSimpleObject.toString());
        } else {
            session.setAttribute("error", "An input error! Please, select at least one component.");
        }
        return "redirect:/creator";
    }

    @GetMapping(value = "/viewer")
    public ModelAndView viewCreatorObjects() throws IOException {
        ModelAndView model = new ModelAndView("viewer");
        List<SimpleObject> objects;
        objects = manager.getListOfCompletedSubjects();
        model.addObject("objects", objects);
        return model;
    }

}
