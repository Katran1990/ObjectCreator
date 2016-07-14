package com.katran.app.servlet;

import com.katran.app.object.SimpleObject;
import com.katran.app.object.SimpleObjectManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Boris on 22.06.2016.
 */
public class SimpleViewerServlet extends HttpServlet {

    @Autowired
    private SimpleObjectManager manager;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext (this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SimpleObject> objects;
        objects = manager.getListOfCompletedSubjects();
        req.setAttribute("objects", objects);
        req.getRequestDispatcher("/created-objects.jsp").forward(req, resp);
    }
}