package com.bo.day02;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName GetContextParamDemo01
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/3 20:18
 * @Version 1.0
 */
public class GetContextParamDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String className = (String) req.getAttribute("className");
        System.out.println("className: " + className);
        ServletContext servletContext = req.getServletContext();
        String url = servletContext.getInitParameter("url");
        resp.getWriter().println(url);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
