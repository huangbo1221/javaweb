package com.huang.bo.filter;

import com.huang.bo.pojo.User;
import com.huang.bo.utils.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName SysFilter
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/23 22:27
 * @Version 1.0
 */
public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        User user = (User) req.getSession().getAttribute(Constants.USER_SESSION);
        if (Objects.isNull(user)) {
            res.sendRedirect(req.getContextPath() + "/error.jsp");
        } else {
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
