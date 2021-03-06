package com.huang.bo.service.user;

import com.huang.bo.pojo.User;

import java.util.List;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/23 8:10
 * @Version 1.0
 */
public interface UserService {
    public User Login(String userCode, String password);

    public boolean updatePwd(int id, String pwd);

    public int getUserCount(String userName, int userRole);

    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);
}
