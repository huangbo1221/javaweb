package com.huang.bo.dao.user;

import com.huang.bo.dao.BaseDao;
import com.huang.bo.pojo.User;
import com.mysql.cj.util.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/23 7:33
 * @Version 1.0
 */
public class UserDaoImpl implements UserDao {

    public static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public User getLoginUser(Connection connection, String userCode) {
        if (connection == null) {
            logger.error("connection is null!");
            return null;
        }

        // 资源的获取
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        String sql = "select * from smbms_user where userCode = ?";
        Object[] params = {userCode};

        User user = null;

        try {
            rs = BaseDao.executeQuery(connection, preparedStatement, rs, sql, params);
            // 查出来可能有多条，但是这里只取了一条
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getTimestamp("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getTimestamp("modifyDate"));

                // 其实这里的代码较为冗余，后续可以考虑数据库模型到javabean的直接转换。DBUtils。
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.CloseResources(connection, preparedStatement, rs);
        }
        return user;
    }

    @Override
    public int updatePwd(Connection connection, int id, String password) {
        if (Objects.isNull(connection)) {
            logger.info("connection is null!");
            return -1;
        }

        String sql = "update smbms_user set userPassword = ? where id = ?";
        PreparedStatement preparedStatement = null;
        Object[] params = {password, id};
        int result = 0;
        try {
            result = BaseDao.executeUpdate(connection, preparedStatement, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.CloseResources(connection, preparedStatement, null);
        }
        return result;
    }

    @Override
    public int getUserCount(Connection connection, String userName, int userRole) {
        if (Objects.isNull(connection)) {
            logger.info("queryAllUsers method, connection is null!");
            return 0;
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        ArrayList<Object> objects = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append("select count(1) as count from smbms_user u, smbms_role r where u.userRole = r.id");
        if (!StringUtils.isNullOrEmpty(userName)) {
            sql.append(" and u.userName like ?");
            objects.add("%" + userName + "%");
        }
        if (userRole > 0) {
            sql.append(" and r.id = ?");
            objects.add(userRole);
        }
        Object[] params = objects.toArray();

        int count = 0;
        try {
            resultSet = BaseDao.executeQuery(connection, preparedStatement, resultSet, sql.toString(), params);
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 这里没有关闭connection，是因为connection要在业务层关闭，处理事务？
            BaseDao.CloseResources(null, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<User>();
        if (Objects.isNull(connection)) {
            logger.info("getUserList method, connection is null!");
            return userList;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole = r.id");
        List<Object> list = new ArrayList<Object>();
        if (!StringUtils.isNullOrEmpty(userName)) {
            sql.append(" and u.userName like ?");
            list.add("%" + userName + "%");
        }
        if (userRole > 0) {
            sql.append(" and u.userRole = ?");
            list.add(userRole);
        }
        //在mysql数据库中，分页使用 limit startIndex，pageSize ; 总数
        sql.append(" order by creationDate DESC limit ?,?");
        currentPageNo = (currentPageNo - 1) * pageSize;
        list.add(currentPageNo);
        list.add(pageSize);

        Object[] params = list.toArray();
        System.out.println("sql ----> " + sql.toString());
        try {
            rs = BaseDao.executeQuery(connection, pstm, rs, sql.toString(), params);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setUserRole(rs.getInt("userRole"));
                user.setUserRoleName(rs.getString("userRoleName"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.CloseResources(null, pstm, rs);
        }

        return userList;
    }

    @Test
    public void test() {
        Connection connection = BaseDao.getConnection();
        UserDaoImpl userDao = new UserDaoImpl();
        int i = userDao.updatePwd(connection, 1, "123456");
        System.out.println(i);
    }

    @Test
    public void testQueryAllUsers() {
        Connection connection = BaseDao.getConnection();
        UserDaoImpl userDao = new UserDaoImpl();
        int i = userDao.getUserCount(connection, "李明", 0);
        System.out.println(i);
    }
}
