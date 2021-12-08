<%--
  Created by IntelliJ IDEA.
  User: 15603
  Date: 2021/12/8
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试jsp标签</title>
</head>
<body>

<jsp:forward page="jsptag2.jsp">
    <jsp:param name="name" value="huangbo1221"/>
    <jsp:param name="age" value="80"/>
</jsp:forward>

</body>
</html>
