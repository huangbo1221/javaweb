<%--
  Created by IntelliJ IDEA.
  User: 15603
  Date: 2021/12/14
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/servlet/login" method="post">
    <h1>登录界面</h1>
    <br>
    <input type="text" name="username">
    <br>
    <input type="submit" name="提交">
</form>


</body>
</html>
