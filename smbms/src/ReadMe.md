# 项目搭建准备工作
1、搭建一个maven web项目
2、配置Tomcat
3、测试项目是否能够跑起来
4、导入项目中会遇到的jar包
   jsp、servlet、mysql驱动、jstl、standard
5、创建项目包结构

![img.png](img.png)

6、编写实体类
ORM映射：表-类映射

7、编写基础公共类
* 数据库配置文件
  db.properties
* 编写数据库的公共类
  BaseDao.java
* 编写字符编码过滤器
  CharacterEncodingFilter

8、导入静态资源

# 登录实现
具体流程如下：

![img_1.png](img_1.png)

1、编写前端页面
2、设置首页
```xml
<welcome-file-list>
    <welcome-file>login.jsp</welcome-file>
  </welcome-file-list>
```