# 转发和重定向的区别

![img.png](img.png)

## 转发
见servlet_02子模块的com.bo.day02.RequestDispatcherDemo.doGet方法，是一个实现转发的方法，
客户端转包如下：

![img_1.png](img_1.png)

## 重定向
代码见servlet_03的com.bo.day02.RedirectServlet.doGet，效果如下：

![img_2.png](img_2.png)

可见重定向时，url发生了改变，且客户端请求了两次。

## 实现重定向的两种方式
### 方式1
```java
resp.sendRedirect("/s3/img");
```
    
![img_3.png](img_3.png)

### 方式2
```java
resp.setHeader("location", "/s3/img");
resp.setStatus(HttpServletResponse.SC_FOUND);
```

![img_4.png](img_4.png)

## 提交表单-->重定向-->显示成功
1、先执行http://localhost:8080/s3/
显示如下：

![img_5.png](img_5.png)

2、输入表单并点击提交

![img_6.png](img_6.png)

![img_7.png](img_7.png)

![img_8.png](img_8.png)