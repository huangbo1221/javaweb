package com.huang.bo.service.user;

import com.huang.bo.dao.BaseDao;
import com.huang.bo.dao.user.UserDao;
import com.huang.bo.dao.user.UserDaoImpl;
import com.huang.bo.pojo.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Objects;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/23 8:12
 * @Version 1.0
 */
public class UserServiceImpl implements UserService{

    public static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    // 业务层到会调用dao层，所以我们要引用dao层

    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    public User Login(String userCode, String password) {
        Connection connection = null;
        User user = null;
        try {
            connection = BaseDao.getConnection();
            // 通过业务层调用对应的具体的数据库操作
            user = userDao.getLoginUser(connection, userCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 其实在dao层已经关闭资源了
            BaseDao.CloseResources(connection, null, null);
        }

        return user;
    }

    @Override
    public boolean updatePwd(int id, String pwd) {
        logger.info("enter UserServiceImpl updatePwd method!");
        Connection connection = null;
        connection = BaseDao.getConnection();

        boolean flag = false;
        try {
            int i = userDao.updatePwd(connection, id, pwd);
            if (i > 0) {
                flag = true;
            }
        } catch (Exception e) {
            logger.error("UserServiceImpl updatePwd method error!", e);
            e.printStackTrace();
        } finally {
            BaseDao.CloseResources(connection, null, null);
        }

        return flag;
    }

    @Test
    public void test() {
        UserServiceImpl userService = new UserServiceImpl();
        User admin = userService.Login("admin", "123456");
        if (Objects.nonNull(admin)) {
            System.out.println(admin.getUserPassword());
        } else {
            System.out.println("admin is null!");
        }
    }
}
