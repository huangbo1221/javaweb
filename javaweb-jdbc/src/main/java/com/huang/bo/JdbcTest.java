package com.huang.bo;

import java.sql.*;

/**
 * @ClassName JdbcTest
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/15 22:35
 * @Version 1.0
 */
public class JdbcTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
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

        // 3、向数据库发送SQL的对象statement或者prepareStatement，专门进行CRUD
        Statement statement = connection.createStatement();
        // prepareStatement是安全的，可以进行预编译
        // connection.prepareStatement("select * from users");


        // 4、编写sql
        String sql = "select * from users";

//        String delSql = "delete from users where id = 3";
//        // 返回的i表示受影响的行数，不同于查询语句返回的是结果集
//        int i = statement.executeUpdate(delSql);

        // 执行查询sql，返回一个ResultSet：结果集
        ResultSet rs = statement.executeQuery(sql);

        // 打印结果集
        while (rs.next()) {
            System.out.print("id:" + rs.getObject("id") + " ");
            System.out.print("name:" + rs.getObject("name") + " ");
            System.out.print("password:" + rs.getObject("password") + " ");
            System.out.print("email:" + rs.getObject("email") + " ");
            System.out.println("birthday:" + rs.getObject("birthday") + " ");
        }

        // 关闭连接
        rs.close();
        statement.close();
        connection.close();

    }


}
