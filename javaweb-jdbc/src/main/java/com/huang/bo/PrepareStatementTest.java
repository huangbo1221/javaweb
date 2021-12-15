package com.huang.bo;

import java.sql.*;

/**
 * @ClassName PrepareStatementTest
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/15 23:13
 * @Version 1.0
 */
public class PrepareStatementTest {
    public static void main(String[] args) throws Exception{
        // 配置信息
        // useUnicode=true&characterEncoding=utf-8 解决中文的乱码问题
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String userName = "root";
        String password = "123456";

        // 1、加载驱动
        // 利用反射的方法加载驱动，com.mysql.jdbc.Driver类继承了com.mysql.cj.jdbc.Driver类
        // 反射执行的时候，会先调用父类的静态方法DriverManager.registerDriver(new Driver());
        // 区注册驱动
        Class.forName("com.mysql.jdbc.Driver");

        // 2、连接数据库
        // 第一步里调用DriverManager.registerDriver注册了驱动，然后这一步利用DriverManager连接数据库
        Connection connection = DriverManager.getConnection(url, userName, password);

        // 3、编写sql，预编译的sql，后面的问号表示占位符
        String sql = "insert into users (id, NAME, password, email, birthday) VALUES (?, ?, ?, ?, ?)";

        // 4、向数据库发送SQL的对象statement或者prepareStatement，专门进行CRUD
        // prepareStatement是安全的，可以进行预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, 4); // 给第一个占位符赋值
        preparedStatement.setString(2, "liuliu"); // 给第二个占位符赋值
        preparedStatement.setString(3, "4321"); // 给第三个占位符赋值
        preparedStatement.setString(4, "163@11"); // 给第四个占位符赋值
        preparedStatement.setDate(5, new Date(new java.util.Date().getTime())); // 给第五个占位符赋值

        // 5、执行sql
        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            System.out.println("sql has been excuted successfully! i: " + i);
        }

        // 6、关闭连接
        preparedStatement.close();
        connection.close();

    }
}
