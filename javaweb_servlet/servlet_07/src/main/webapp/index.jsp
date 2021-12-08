<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<html>
<body>
<h2>Hello World!</h2>
<%--JSP表达式
作用：用来将程序输出到客户端
--%>
<%= new Date() %>

<%
    int x = 1;
    out.println(x);
%>


<%
    for (int i = 0; i < 5; i++) {
%>
    <h1>for 循环 <%= i %></h1>
    <br>
<%
    }
%>

<%!
    static {
        String name = "这是一个全局静态代码块！";
    }

    private int global = 1;

    private void jspInitMethod() {
        System.out.println("手写的初始化方法");
    }
%>

pageContext = ${pageContext.request.contextPath}

</body>
</html>
