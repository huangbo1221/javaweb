<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
  Created by IntelliJ IDEA.
  User: 15603
  Date: 2021/12/5
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>请登录</h2>
<form action="${pageContext.request.contextPath}/login" method="post">
    用户名：<input type="text" name="username"><br>
    密码： <input type="password" name="password"><br>
    爱好：
    <input type="checkbox" name="hobbies" value="女孩">女孩
    <input type="checkbox" name="hobbies" value="篮球">篮球
    <input type="checkbox" name="hobbies" value="睡觉">睡觉
    <input type="checkbox" name="hobbies" value="刷剧">刷剧<br>
    <input type="submit">
</form>

</body>
</html>
