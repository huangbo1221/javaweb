# JSP学习
## jsp简单语法
代码如下：

![img.png](img.png)

执行后的输出如下：

![img_1.png](img_1.png)

转化成的java代码如下：

![img_2.png](img_2.png)

注意：上述的java代码全部在_jspService方法里！！！
也就是说在<% %>或<%= %>里写的java代码，全部嵌入到了转化后的java代码中的jspService方法里！
那么有没有可以嵌入到全局的代码（非jspService）里的呢？

## jsp声明
代码如下：

![img_3.png](img_3.png)

jsp转化的java代码如下：

![img_4.png](img_4.png)

可见：要想使jsp里的java代码在转化后，嵌入到全局代码中，jsp语法为：
```java
<%!

%>
```
