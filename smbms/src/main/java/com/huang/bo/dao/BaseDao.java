package com.huang.bo.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @ClassName BaseDao
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/20 22:13
 * @Version 1.0
 */
public class BaseDao {

    private static String url;

    private static String driver;

    private static String username;

    private static String password;

    // 静态代码块，工程启动时加载
    static {

        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");

        Properties properties = new Properties();

        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = properties.getProperty("driver");

        url = properties.getProperty("url");

        username = properties.getProperty("username");

        password = properties.getProperty("password");
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // 加载数据库驱动
            Class.forName(driver);

            // 获取连接
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    // 编写查询公共方法
    public static ResultSet executeQuery(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet, String sql, Object[] params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            // setObject的index从1开始
            preparedStatement.setObject(i+1, params[i]);
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    // 编写增删改公共方法
    public static int executeUpdate(Connection connection, PreparedStatement preparedStatement,  String sql, Object[] params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            // setObject的index从1开始
            preparedStatement.setObject(i+1, params[i]);
        }
        int updateRes = preparedStatement.executeUpdate();
        return updateRes;
    }

    // 编写公共资源释放类
    public static boolean CloseResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        boolean flag = true;
        if (resultSet != null) {
            try {
                resultSet.close();
                // 关闭后置为null，便于GC回收
                resultSet = null;
            } catch (SQLException e) {
                e.printStackTrace();
                // 关闭失败
                flag = false;
            }
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
                // 关闭后置为null，便于GC回收
                preparedStatement = null;
            } catch (SQLException e) {
                e.printStackTrace();
                // 关闭失败
                flag = false;
            }
        }

        if (connection != null) {
            try {
                connection.close();
                // 关闭后置为null，便于GC回收
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
                // 关闭失败
                flag = false;
            }
        }
        return flag;
    }
}
