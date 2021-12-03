package com.bo.day02;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName RequestDispatcherDemo
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/3 20:33
 * @Version 1.0
 */
public class RequestDispatcherDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("enter RequestDispatcherDemo method!");
        ServletContext servletContext = req.getServletContext();
        req.setAttribute("className", "RequestDispatcherDemo");
        servletContext.getRequestDispatcher("/gp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
