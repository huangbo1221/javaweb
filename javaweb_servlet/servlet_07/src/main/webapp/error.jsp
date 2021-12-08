<%--
  Created by IntelliJ IDEA.
  User: 15603
  Date: 2021/12/7
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="errorjsp/500.jsp" %>
<html>
<head>
    <title>测试500界面</title>
</head>
<body>
测试错误界面
<%
    int a = 1/0;
%>
</body>
</html>
