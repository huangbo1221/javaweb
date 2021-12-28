package com.huang.bo.servlet.user;

import com.huang.bo.pojo.User;
import com.huang.bo.service.user.UserService;
import com.huang.bo.service.user.UserServiceImpl;
import com.huang.bo.utils.Constants;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName UserServlet
 * @Description 用户的servlet操作
 * @Author huangbo1221
 * @Date 2021/12/28 7:55
 * @Version 1.0
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");
        if (Objects.nonNull(o) && !StringUtils.isNullOrEmpty(newpassword)) {
            // 用户处于登录场景中，且新设置的密码不为空
            UserService userService = new UserServiceImpl();
            boolean res = userService.updatePwd(((User) o).getId(), newpassword);
            if (res) {
                // 修改成功
                req.setAttribute("message", "修改密码成功，请退出，使用新密码重新登录");
                // 移除session，利用filter重新登录
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }  else {
                // 更新密码失败
                // todo
            }
        } else {
            // 用户不处于登录场景中，或者新设置的密码为空
            // todo
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
