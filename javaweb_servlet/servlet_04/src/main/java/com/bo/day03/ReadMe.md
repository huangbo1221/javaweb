# HttpServletRequest
## getParameter与getParameterValues方法
getParameter获取单个参数，getParameterValues获取到的值是一个String数组。

![img.png](img.png)

![img_1.png](img_1.png)

但是打印出来的value乱码了，可以设置HttpServletRequest的编码格式解决，如下：

![img_2.png](img_2.png)


