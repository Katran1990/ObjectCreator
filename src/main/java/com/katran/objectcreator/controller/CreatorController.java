package com.katran.objectcreator.controller;

import com.katran.objectcreator.model.ObjectComponent;
import com.katran.objectcreator.model.ObjectComponentWrapper;
import com.katran.objectcreator.service.AssemblyService;
import com.katran.objectcreator.service.ObjectManager;
import com.katran.objectcreator.model.SimpleObject;
import com.katran.objectcreator.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class CreatorController {

    private static final String ERROR = "error";
    private static final String CREATED_OBJECT = "createdObject";
    private static final int NUMBER_OF_OBJECTS = 3;
    private static final String CITY = "city";
    private static final String TEMPERATURE = "temp";
    private static final String WIND = "wind";

    @Autowired
    private AssemblyService assemblyService;

    @Autowired
    private ObjectManager manager;

    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/")
    public ModelAndView welcome() throws Exception {
        ModelAndView model = new ModelAndView("index");
        Map<String, Object> weatherData = weatherService.getWeather();
        model.addObject(CITY, weatherData.get(CITY));
        model.addObject(TEMPERATURE, weatherData.get(TEMPERATURE));
        model.addObject(WIND, weatherData.get(WIND));
        return model;
    }

    @GetMapping(value = "/creator")
    public ModelAndView getCreatorForm(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("creator");
        List<String> materialList = manager.getMaterials();
        List<String> qualityList = manager.getSources();
        model.addObject("materials", materialList);
        model.addObject("qualities", qualityList);
        ArrayList<ObjectComponent> componentList = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_OBJECTS; i++) {
            componentList.add(i, new ObjectComponent());
        }
        ObjectComponentWrapper componentWrapper = new ObjectComponentWrapper(componentList);
        model.addObject("componentWrapper", componentWrapper);

        HttpSession session = request.getSession();
        Object error = session.getAttribute(ERROR);
        if (error != null) {
            model.addObject(ERROR, error);
            session.removeAttribute(ERROR);
        }
        Object createdObject = session.getAttribute(CREATED_OBJECT);
        if (createdObject != null) {
            model.addObject(CREATED_OBJECT, createdObject);
            session.removeAttribute(CREATED_OBJECT);
        }
        return model;
    }


    @PostMapping(value = "/creator")
    public String submitCreatorForm(@ModelAttribute("componentWrapper") ObjectComponentWrapper componentWrapper, HttpServletRequest request) throws ServletException, IOException {
        List<String> materialList = new ArrayList<>();
        List<String> qualityList = new ArrayList<>();
        componentWrapper.getComponentList().stream().filter(component -> component.getMaterial() != null).forEach(component -> {
            materialList.add(component.getMaterial());
            qualityList.add(component.getQuality());
        });

        HttpSession session = request.getSession();
        SimpleObject createdObject;
        if (materialList.isEmpty()) {
            session.setAttribute(ERROR, "An input error! Please, select at least one component.");
        } else {
            createdObject = assemblyService.assemblyOfObject(materialList, qualityList);
            manager.saveObject(createdObject);
            session.setAttribute(CREATED_OBJECT, createdObject);
        }
        return "redirect:/creator";
    }

    @GetMapping(value = "/viewer")
    public ModelAndView viewCreatorObjects() throws IOException {
        ModelAndView model = new ModelAndView("viewer");
        List<SimpleObject> objects = manager.getCreatedObjects();
        model.addObject("objects", objects);
        return model;
    }

}
