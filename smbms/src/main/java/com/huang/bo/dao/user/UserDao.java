package com.huang.bo.dao.user;

import com.huang.bo.pojo.User;

import java.sql.Connection;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/23 7:31
 * @Version 1.0
 */
public interface UserDao {

    public User getLoginUser(Connection connection, String userCode);

}
