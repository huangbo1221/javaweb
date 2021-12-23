package com.huang.bo.servlet.user;

import com.huang.bo.pojo.User;
import com.huang.bo.service.user.UserService;
import com.huang.bo.service.user.UserServiceImpl;
import com.huang.bo.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName LoginServlet
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/23 21:06
 * @Version 1.0
 */
public class LoginServlet extends HttpServlet {

    // servlet:控制层调用业务层代码

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("enter logginservlet");
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

        UserService userService = new UserServiceImpl();
        User user = userService.Login(userCode, userPassword);

        // 没验证密码的正确性
        if (Objects.nonNull(user)) {
            req.getSession().setAttribute(Constants.USER_SESSION, user);
            resp.sendRedirect("jsp/frame.jsp");
        } else {
            // 这里的error对应login.jsp里的EL表达式{error}
            req.setAttribute("error", "用户名或者密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
