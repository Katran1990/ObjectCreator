package com.katran.app.servlet;
import com.katran.app.database.JDBCTestWebDAO;
import com.katran.app.database.JDBCWebDAO;
import com.katran.app.object.ObjectAssemblyService;
import com.katran.app.object.TestWebObject;
import com.katran.app.object.WebObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

    //@Autowired
    //public JDBCWebDAO dao;

    @Autowired
    public JDBCTestWebDAO dao1;

//    public static void main(String[] args) {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring/context.xml");
//        dao1 = (JDBCTestWebDAO) context.getBean("jdbcTestWebDAO");
//        System.out.println(dao1.getProductionQuality(0.37));
//        dao1.saveObject(new TestWebObject("cat", "awful", "stone"));
//        for (String o : dao1.getListOfSources()){
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
        List<String> components = null;
        List<String> sources = null;
        components = dao1.getListOfMaterials();
        //components = dao.getComponents(); //dao1.getListOfSources();
        sources = dao1.getListOfSources();
        //sources = dao.getSources();       //dao1.getListOfSources();
        req.setAttribute("components", components);
        req.setAttribute("sources", sources);
        req.getRequestDispatcher("/object-creator.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> components = new ArrayList<String>();
        List<String> sources = new ArrayList<String>();
        TestWebObject createdSimpleObject = null;
        //WebObject createdSimpleObject = null;
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
                dao1.saveObject(createdSimpleObject);
                //dao.addObject(createdSimpleObject);
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