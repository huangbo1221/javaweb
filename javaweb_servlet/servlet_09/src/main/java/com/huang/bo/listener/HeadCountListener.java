package com.huang.bo.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Objects;

/**
 * @ClassName HeadCountListener
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/13 23:16
 * @Version 1.0
 */
public class HeadCountListener implements HttpSessionListener {
    // 创建一个session，就会触发这个函数
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("sessionCreated: " + se.getSession().getId());
        ServletContext servletContext = se.getSession().getServletContext();
        Integer onlineCount = (Integer) servletContext.getAttribute("onlineCount");
        if (onlineCount == null) {
            onlineCount = new Integer(1);
        } else {
            int i = onlineCount.intValue();
            i++;
            onlineCount = new Integer(i);
        }
        servletContext.setAttribute("onlineCount", onlineCount);
    }

    // 一旦销毁session，就会触发这个函数
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("sessionDestroyed: " + se.getSession().getId());
        ServletContext servletContext = se.getSession().getServletContext();
        Integer onlineCount = (Integer) servletContext.getAttribute("onlineCount");
        if (onlineCount == null) {
            onlineCount = new Integer(0);
        } else {
            int i = onlineCount.intValue();
            i--;
            onlineCount = new Integer(i);
        }
        servletContext.setAttribute("onlineCount", onlineCount);
    }
}
