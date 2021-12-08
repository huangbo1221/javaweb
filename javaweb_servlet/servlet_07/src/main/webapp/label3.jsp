<%--
  Created by IntelliJ IDEA.
  User: 15603
  Date: 2021/12/8
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

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
