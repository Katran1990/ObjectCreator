package com.katran.app.servlet;

import com.katran.app.object.ObjectAssemblyService;
import com.katran.app.object.SimpleObject;
import com.katran.app.object.SimpleObjectManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boris on 24.05.2016.
 */

public class SimpleCreatorServlet extends HttpServlet {

    @Autowired
    private ObjectAssemblyService assemblyService;

    @Autowired
    private SimpleObjectManager manager;

//    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring/context.xml");
//        manager = (SimpleObjectManager) context.getBean("simpleObjectManager");
//        System.out.println(manager.getProductionQuality(0.37));
//        manager.saveObject(new SimpleObject("cat", "awful", "stone"));
//        for (String o : manager.getListOfSources()){
//            System.out.println(o);
//        }
//        System.out.println(manager.getSourceQualityByName("awful"));
//
//    }

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> components = manager.getListOfMaterials();
        List<String> sources = manager.getListOfSources();
        req.setAttribute("components", components);
        req.setAttribute("sources", sources);

        HttpSession session = req.getSession();
        Object error = session.getAttribute("error");
        if (error != null) {
            req.setAttribute("error", error);
            session.removeAttribute("error");
        }

        Object createdObject = session.getAttribute("createdObject");
        if (createdObject != null) {
            req.setAttribute("createdObject", createdObject);
            session.removeAttribute("createdObject");
        }
        req.getRequestDispatcher("/object-creator.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> components = new ArrayList<String>();
        List<String> sources = new ArrayList<String>();
        SimpleObject createdSimpleObject = null;
        String component;
        component = req.getParameter("component1");
        if (component!=null) {
            components.add(component);
            sources.add(req.getParameter("source1"));
        }
        component = req.getParameter("component2");
        if (component!=null) {
            components.add(component);
            sources.add(req.getParameter("source2"));
        }
        component = req.getParameter("component3");
        if (component!=null) {
            components.add(component);
            sources.add(req.getParameter("source3"));
        }
        HttpSession session = req.getSession();
        if (components.size()>0) {
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
        resp.sendRedirect("simple-creator");
    }

}