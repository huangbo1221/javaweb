package com.huang.bo;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @ClassName TransactionTest
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/16 22:47
 * @Version 1.0
 */
public class TransactionTest {

    @Test
    public void test1() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        try {
            // 注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 连接数据库的信息
            String url= "jdbc:mysql://localhost:3306/jdbc?useUnicode=utf-8&characterEncoding=utf-8";
            String userName = "root";
            String password = "123456";

            // 建立连接
            connection = DriverManager.getConnection(url, userName, password);

            // 创建事务，将自动提交置为false，则是开启了事务
            connection.setAutoCommit(false);

            // 撰写sql
            String mysql1 = "update account set money = 600 where name = 'B'";

            // 事务
            connection.prepareStatement(mysql1).executeUpdate();

            // 异常
//            int index = 1 / 0;

            String mysql2 = "update account set money = 300 where name = 'C'";

            connection.prepareStatement(mysql2).executeUpdate();

            // 事务的提交
            connection.commit();
            System.out.println("success!");
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
                System.out.println("sql执行失败！");
            }
        } finally {
            connection.close();
        }


    }

}
