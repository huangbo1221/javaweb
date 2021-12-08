<%--
  Created by IntelliJ IDEA.
  User: 15603
  Date: 2021/12/8
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试标签</title>
</head>
<body>
    <%@ include file="common/header.jsp"%>
    <h2>我是body体</h2>
    <%@include file="common/footer.jsp"%>

    <hr>

    <jsp:include page="common/header.jsp"></jsp:include>
    <h2>我是body体</h2>
    <jsp:include page="common/footer.jsp"></jsp:include>
</body>
</html>
