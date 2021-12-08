<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入jstl标签核心包--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>测试jstl核心语法</title>
</head>
<body>

<h4>if测试</h4>

<hr>

<form action="jstltag.jsp" method="get">
    <%--
    EL表达式获取表单中的数据
    ${param.参数名}}
    --%>
    <input type="text" name="username" value="${param.username}">
    <input type="submit" value="登录">
</form>

<%--使用jstl标签的if--%>
<c:if test="${param.username=='admin'}" var="isAdmin">
    <c:out value="管理员，欢迎您！"/>
</c:if>

<c:out value="${isAdmin}"></c:out>

</body>
</html>
