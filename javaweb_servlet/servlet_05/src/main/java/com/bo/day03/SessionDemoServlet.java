package com.bo.day03;

import pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName SessionDemoServlet
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/5 22:47
 * @Version 1.0
 */
public class SessionDemoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置编码格式
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        HttpSession session = req.getSession();

        // 设置一个session属性
        session.setAttribute("person1", new Person("huangbo1221", 55));

        // 获取sessionId
        String sessionId = session.getId();

        if (session.isNew()) {
            resp.getWriter().write("sessionId创建成功， sessionId：" + sessionId );
        } else {
            resp.getWriter().write("sessionId已经存在， sessionId：" + sessionId );
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
