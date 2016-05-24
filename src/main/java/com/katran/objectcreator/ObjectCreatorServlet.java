package com.katran.objectcreator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Boris on 24.05.2016.
 */

public class ObjectCreatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String createdObject = req.getParameter("quality-of-material")+" "+req.getParameter("type-of-material")+" "+req.getParameter("type-of-object");

        req.setAttribute("createdObject", createdObject);
        req.getRequestDispatcher("/start-point.jsp").include(req, resp);
    }
}
