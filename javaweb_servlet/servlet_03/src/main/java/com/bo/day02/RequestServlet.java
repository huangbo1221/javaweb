package com.bo.day02;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName RequestServlet
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/4 22:47
 * @Version 1.0
 */
public class RequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("enter RequestServlet doGet()!");
        System.out.println(req.getServletContext().getContextPath());
        System.out.println("=======================================");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username + ":" + password);

        resp.sendRedirect("/s3/success.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
