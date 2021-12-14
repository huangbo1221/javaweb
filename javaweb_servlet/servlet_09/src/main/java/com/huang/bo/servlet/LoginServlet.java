package com.huang.bo.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName LoginServlet
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/14 21:39
 * @Version 1.0
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        if ("admin".equals(username)) {
            req.getSession().setAttribute("USER_SESSION", req.getSession().getId());
            resp.sendRedirect(req.getContextPath() + "/sys/success.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }
}
