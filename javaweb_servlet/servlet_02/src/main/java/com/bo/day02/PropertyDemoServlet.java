package com.bo.day02;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName PropertyDemoServlet
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/3 21:22
 * @Version 1.0
 */
public class PropertyDemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        InputStream resourceAsStream = servletContext.getResourceAsStream("/WEB-INF/classes/db.properties");
        Properties prop = new Properties();
        prop.load(resourceAsStream);
        String username = (String) prop.get("username");
        String password = (String) prop.get("password");
        resp.getWriter().println(username + ": " + password);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
