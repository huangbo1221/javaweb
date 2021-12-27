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

    // 查询接口
    public User getLoginUser(Connection connection, String userCode);

    // 更细密码接口
    public int updatePwd(Connection connection, int id, String password);

}
