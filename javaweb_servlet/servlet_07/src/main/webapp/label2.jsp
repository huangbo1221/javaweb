<%--
  Created by IntelliJ IDEA.
  User: 15603
  Date: 2021/12/8
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试九大内置对象</title>
</head>
<body>

<%--内置对象--%>
    <%
        pageContext.setAttribute("name1", "huangbo1"); // 在当前页才能取到放进去的值
        request.setAttribute("name2", "huangbo2"); // 在一次请求中能取到放进去的值，值也会被携带到转发中的请求里去
        session.setAttribute("name3", "huangbo3");// 在一次会话中可以取到该值，也就是从打开浏览器，到关闭浏览器
        application.setAttribute("name4", "huangbo4");// 服务器只要开着，就能取到放进去的值

        // 也可通过执行后org.apache.jasper.runtime.PageContextImpl.setAttribute(java.lang.String, java.lang.Object, int)
        // 方法来指定其作用域
    %>

    <%
        // 从pageContext中取出来上面代码放进去的值
        // find值时，是从低到高寻找的。就像双亲委派机制。
        // page --> request --> session --> application
        String name1 = (String) pageContext.findAttribute("name1");
        String name2 = (String) pageContext.findAttribute("name2");
        String name3 = (String) pageContext.findAttribute("name3");
        String name4 = (String) pageContext.findAttribute("name4");
        String name5 = (String) pageContext.findAttribute("name5"); // 理论上应该找不到
    %>

<%--使用EL表达式来输出--%>
<h1>值分别为：</h1>
<h3>${name1}</h3>
<h3>${name2}</h3>
<h3>${name3}</h3>
<h3>${name4}</h3>
<h3>${name5}</h3>

</body>
</html>
