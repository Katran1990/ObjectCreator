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
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        WebObject createdSimpleObject = assemblyService.assemblyOfObject(req.getParameter("quality-of-material"), req.getParameter("type-of-material"), req.getParameter("type-of-object"));
        try {dao.addObject(createdSimpleObject);
        } catch (SQLException e) {e.printStackTrace();}

        req.setAttribute("createdObject", createdSimpleObject.toString());
        req.getRequestDispatcher("/start-point.jsp").include(req, resp);
    }
}
