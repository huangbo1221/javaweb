<html>
<body>
<h2>Hello World!</h2>

<jsp:useBean id="person" class="com.bo.pojo.Person"></jsp:useBean>

<jsp:setProperty name="person" property="id" value="1"></jsp:setProperty>
<jsp:setProperty name="person" property="name" value="huangbo1221"></jsp:setProperty>
<jsp:setProperty name="person" property="address" value="hebei"></jsp:setProperty>
<jsp:setProperty name="person" property="age" value="16"></jsp:setProperty>

name = <jsp:getProperty name="person" property="name"/>
</body>
</html>
