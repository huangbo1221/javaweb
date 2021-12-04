<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
<h2>Hello World!</h2>
<%--${pageContext.request.contextPath}---》 表示此子项目的路径 --%>
<form action="${pageContext.request.contextPath}/login" method="get">
    用户名：<input type="text" name="username"> <br/>
    密码： <input type="text" name="password"> <br/>
    <input type="submit">
</form>

</body>
</html>
