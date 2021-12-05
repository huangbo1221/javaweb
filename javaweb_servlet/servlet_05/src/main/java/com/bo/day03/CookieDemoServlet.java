package com.bo.day03;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @ClassName CookieDemoServlet
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/5 20:42
 * @Version 1.0
 */
public class CookieDemoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求和返回的编码格式
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        // 获取请求的所有cookies
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            out.print("您上一次访问的时间是：");
            for (Cookie cookie : cookies) {
                if ("lastLoginTime".equals(cookie.getName())) {
                    String value = cookie.getValue();
                    long l = Long.parseLong(value);
                    String s = new Date(l).toLocaleString();
                    out.println(s);
                }
            }
        } else {
            out.println("这是您第一次访问");
        }

        String lastLogin = String.valueOf(System.currentTimeMillis());
        System.out.println(lastLogin);
        Cookie lastCookie = new Cookie("lastLoginTime", lastLogin);
        lastCookie.setMaxAge(24 * 60 * 60);
        resp.addCookie(lastCookie);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
