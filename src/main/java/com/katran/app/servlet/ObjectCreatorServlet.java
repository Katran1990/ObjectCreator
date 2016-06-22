package com.katran.app.servlet;

import com.katran.app.database.WebObjectDAO;
import com.katran.app.object.ObjectAssemblyService;
import com.katran.app.object.WebObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boris on 24.05.2016.
 */

public class ObjectCreatorServlet extends HttpServlet {

    @Autowired
    public ObjectAssemblyService assemblyService;
    @Autowired
    public WebObjectDAO dao;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> components = null;
        List<String> sources = null;
        try {
            components = dao.getComponents();
            sources = dao.getSources();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("components", components);
        req.setAttribute("sources", sources);
        req.getRequestDispatcher("/object-creator.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> components = new ArrayList<String>();
        List<String> sources = new ArrayList<String>();
        WebObject createdSimpleObject = null;
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

        try {
            createdSimpleObject = assemblyService.assemblyOfObject(components, sources);
            dao.addObject(createdSimpleObject);
        } catch (SQLException e) {e.printStackTrace();}

        req.setAttribute("createdObject", createdSimpleObject.toString());
        req.getRequestDispatcher("/object-creator.jsp").include(req, resp);
        doGet(req, resp);
    }

}
