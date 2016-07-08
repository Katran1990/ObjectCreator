package com.katran.app.servlet;
import com.katran.app.database.JDBCSimpleObjectDAO;
import com.katran.app.object.ObjectAssemblyService;
import com.katran.app.object.SimpleObject;
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

public class SimpleCreatorServlet extends HttpServlet {

    @Autowired
    private ObjectAssemblyService assemblyService;

    @Autowired
    private JDBCSimpleObjectDAO dao;

//    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring/context.xml");
//        dao = (JDBCSimpleObjectDAO) context.getBean("jdbcTestWebDAO");
//        System.out.println(dao.getProductionQuality(0.37));
//        dao.saveObject(new SimpleObject("cat", "awful", "stone"));
//        for (String o : dao.getListOfSources()){
//            System.out.println(o);
//        }
//
//    }

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> components = dao.getListOfMaterials();
        List<String> sources = dao.getListOfSources();
        req.setAttribute("components", components);
        req.setAttribute("sources", sources);
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

        if (components.size()>0) {
            try {
                createdSimpleObject = assemblyService.assemblyOfObject(components, sources);
                dao.saveObject(createdSimpleObject);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("createdObject", createdSimpleObject.toString());
        } else {
            req.setAttribute("error", "An input error! Please, select at least one component.");
        }
        doGet(req, resp);
    }

}