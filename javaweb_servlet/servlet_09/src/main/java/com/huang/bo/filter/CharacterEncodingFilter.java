package com.huang.bo.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName CharacterEncodingFilter
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/13 22:32
 * @Version 1.0
 */
public class CharacterEncodingFilter implements Filter {

    // web服务器启动的时候开始初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CharacterEncodingFilter init！");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("CharacterEncodingFilter doFilter start to excute！");
        // 为了让请求继续往下执行其他的filter，必须得调用chain.doFilter(request, response)
        chain.doFilter(request, response);
        System.out.println("CharacterEncodingFilter doFilter finished！");
    }

    // web服务器关闭的时候开始销毁
    @Override
    public void destroy() {
        System.out.println("CharacterEncodingFilter destroyed!");
    }
}
