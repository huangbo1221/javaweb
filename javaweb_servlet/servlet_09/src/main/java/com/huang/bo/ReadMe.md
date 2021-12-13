# Filter
## filter优先于servlet执行

![img.png](img.png)

编写一个Filter实例，如下：

![img_1.png](img_1.png)

web.xml配置如下：

![img_2.png](img_2.png)

演示如下：

请求为http://localhost:8080/s9/show，出现了乱码，因为没经过过滤器

![img_3.png](img_3.png)

请求为http://localhost:8080/s9/servlet/show，没出现乱码，因为经过了过滤器

![img_4.png](img_4.png)

## 服务器启动时，Filter开始初始化

![img_5.png](img_5.png)

## 服务器停止时，Filter开始销毁

![img_6.png](img_6.png)

# Listener监听器

## 实例演示（统计在线人数）
代码如下：

![img_7.png](img_7.png)

利用会话上下文统计在线人数，实现HttpSessionListener接口即可

web.xml配置如下：

![img_8.png](img_8.png)

当启动服务时，显示效果如下：

![img_9.png](img_9.png)

会显示有两个会话，原因时服务器启动时创建了多个会话（其中存在失败的会话被统计在内）

![img_10.png](img_10.png)

## 浏览器演示如下：

在新的浏览器启动时，显示效果如下：

![img_11.png](img_11.png)

可见增加了一个会话！

