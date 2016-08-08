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

    @Autowired
    private AssemblyService assemblyService;

    @Autowired
    private ObjectManager manager;

    @Autowired
    private WeatherService weatherService;

    private static final int NUMBER_OF_OBJECTS = 3;

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
        List<String> materialList = manager.getListOfMaterials();
        List<String> qualityList = manager.getListOfSources();
        model.addObject("materials", materialList);
        model.addObject("qualities", qualityList);
        ArrayList<ObjectComponent> componentList = new ArrayList<ObjectComponent>();
        for (int i = 0; i< NUMBER_OF_OBJECTS; i++){
            componentList.add(i, new ObjectComponent());
        }
        ObjectComponentWrapper componentWrapper = new ObjectComponentWrapper(componentList);
        model.addObject("componentWrapper", componentWrapper);

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


    @PostMapping(value = "/creator")
    public String submitCreatorForm(@ModelAttribute("componentWrapper") ObjectComponentWrapper componentWrapper, HttpServletRequest request) throws ServletException, IOException {
        List<String> materialList = new ArrayList<String>();
        List<String> qualityList = new ArrayList<String>();
        for (int i = 0; i<componentWrapper.getComponentList().size(); i++){
            ObjectComponent objectComponent = componentWrapper.getComponentList().get(i);
            if (objectComponent.getMaterial()!=null){
                materialList.add(objectComponent.getMaterial());
                qualityList.add(objectComponent.getQuality());
            }
        }

        HttpSession session = request.getSession();
        SimpleObject createdObject = null;
        if (materialList.size() > 0) {
            try {
                createdObject = assemblyService.assemblyOfObject(materialList, qualityList);
                manager.saveObject(createdObject);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            session.setAttribute("createdObject", createdObject.toString());
        } else {
            session.setAttribute("error", "An input error! Please, select at least one component.");
        }
        return "redirect:/creator";
    }

    @GetMapping(value = "/viewer")
    public ModelAndView viewCreatorObjects() throws IOException {
        ModelAndView model = new ModelAndView("viewer");
        List<SimpleObject> objects = manager.getListOfCompletedObjects();
        model.addObject("objects", objects);
        return model;
    }

}
