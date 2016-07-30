package com.katran.app.controllers;

import com.katran.app.object.ObjectAssemblyService;
import com.katran.app.object.SimpleObject;
import com.katran.app.object.SimpleObjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Boris on 16.07.2016.
 */
@Controller
public class CreatorController {

    @Autowired
    private ObjectAssemblyService assemblyService;

    @Autowired
    private SimpleObjectManager manager;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(){
        return "index";
    }

    @RequestMapping(value = "/creator", method = RequestMethod.GET)
    public ModelAndView getCreatorForm(HttpServletRequest request) {
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
    @RequestMapping(value = "/creator", method = RequestMethod.POST)
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

    @RequestMapping(value="/viewer", method = RequestMethod.GET)
    public ModelAndView viewCreatorObjects(){
        ModelAndView model = new ModelAndView("created-objects");
        List<SimpleObject> objects;
        objects = manager.getListOfCompletedSubjects();
        model.addObject("objects", objects);
        return model;
    }

}
