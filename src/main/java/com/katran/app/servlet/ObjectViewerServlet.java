package com.katran.app.servlet;

import com.katran.app.database.JDBCTestWebDAO;
import com.katran.app.database.JDBCWebDAO;
import com.katran.app.object.TestWebObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Boris on 22.06.2016.
 */
public class ObjectViewerServlet extends HttpServlet {

    @Autowired
    JDBCWebDAO dao;

    @Autowired
    JDBCTestWebDAO dao1;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TestWebObject> objects;
        objects = dao1.getListOfCompletedSubjects();
        //objects = dao.getObjects();

        req.setAttribute("objects", objects);
        req.getRequestDispatcher("/created-objects.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}