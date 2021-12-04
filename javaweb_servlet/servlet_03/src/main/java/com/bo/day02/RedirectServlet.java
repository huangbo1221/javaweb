package com.bo.day02;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName RedirectServlet
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/4 22:30
 * @Version 1.0
 */
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 实现方式1
//        resp.sendRedirect("/s3/img");

        // 实现方式2
        resp.setHeader("location", "/s3/img");
        resp.setStatus(HttpServletResponse.SC_FOUND);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
