package com.huang.bo.dao.user;

import com.huang.bo.pojo.User;

import java.sql.Connection;
import java.util.List;

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

    // 根据用户名模糊查询所有用户或者根据用户角色查询用户
    public int getUserCount(Connection connection, String userName, int userRole);

    // 通过条件来查询所有用户列表
    List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize);
}
