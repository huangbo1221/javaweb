package com.huang.bo.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.huang.bo.pojo.Role;
import com.huang.bo.pojo.User;
import com.huang.bo.service.role.RoleService;
import com.huang.bo.service.role.RoleServiceImpl;
import com.huang.bo.service.user.UserService;
import com.huang.bo.service.user.UserServiceImpl;
import com.huang.bo.utils.Constants;
import com.huang.bo.utils.PageSupport;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        String method = req.getParameter("method");
        if (!StringUtils.isNullOrEmpty(method) && "savepwd".equals(method)) {
            updatePwd(req, resp);
        } else if (!StringUtils.isNullOrEmpty(method) && "pwdmodify".equals(method)) {
            modifyPwd(req, resp);
        } else if (!StringUtils.isNullOrEmpty(method) && "query".equals(method)) {
            query(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) {
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
                req.setAttribute("message", "密码修改失败");
            }
        } else {
            // 用户不处于登录场景中，或者新设置的密码为空
            req.setAttribute("message", "新设置的密码不合法");
        }
        try {
            req.getRequestDispatcher("/jsp/pwdmodify.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifyPwd(HttpServletRequest req, HttpServletResponse resp) {
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");
        Map<String,String> resultMap = new HashMap<>();

        if (Objects.isNull(o)) {
            resultMap.put("result", "sessionerror");
        } else if (StringUtils.isNullOrEmpty(oldpassword)) {
            resultMap.put("result", "error");
        } else {
            String userPassword = ((User) o).getUserPassword();
            if (oldpassword.equals(userPassword)) {
                resultMap.put("result", "true");
            } else {
                resultMap.put("result", "false");
            }
        }

        // ajax实现的地方需要用json的返回样式，因此需将返回格式设置成json
        resp.setContentType("application/json");
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 查询用户列表
        // 从前端获取数据
        String queryUserName = request.getParameter("queryname");
        String temp = request.getParameter("queryUserRole");
        String pageIndex = request.getParameter("pageIndex");
        int queryUserRole = 0;
        UserService userService = new UserServiceImpl();
        List<User> userList = null;
        //第一次走这个请求，一定是第一页，页面大小是固定的
        //设置页面容量
        int pageSize = Constants.pageSize;
        //默认当前页码
        int currentPageNo = 1;

        if (queryUserName == null) {
            queryUserName = "";
        }
        if (temp != null && !temp.equals("")) {
            queryUserRole = Integer.parseInt(temp);
        }

        if (pageIndex != null) {
            try {
                currentPageNo = Integer.valueOf(pageIndex);
            } catch (NumberFormatException e) {
                response.sendRedirect("error.jsp");
            }
        }
        //总数量（表）
        int totalCount = userService.getUserCount(queryUserName, queryUserRole);
        //总页数
        PageSupport pages = new PageSupport();
        pages.setCurrentPageNo(currentPageNo);  //当前页
        pages.setPageSize(pageSize);            //页面大小
        pages.setTotalCount(totalCount);        //总数量

        int totalPageCount = pages.getTotalPageCount();  //总页面数量

        //控制首页和尾页
        //如果页面小于第一页，就显示第一页
        if (currentPageNo < 1) {
            currentPageNo = 1;
            //如果当前页面大于最后一页，当前页等于最后一页即可
        } else if (currentPageNo > totalPageCount) {
            currentPageNo = totalPageCount;
        }

        //获取用户列表展示
        userList = userService.getUserList(queryUserName, queryUserRole, currentPageNo, pageSize);
        request.setAttribute("userList", userList);
        //获取角色列表
        List<Role> roleList = null;
        RoleService roleService = new RoleServiceImpl();
        roleList = roleService.getRoleList();
        request.setAttribute("roleList", roleList);

        request.setAttribute("queryUserName", queryUserName);
        request.setAttribute("queryUserRole", queryUserRole);
        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNo);

        request.getRequestDispatcher("userlist.jsp").forward(request, response);
    }
}
