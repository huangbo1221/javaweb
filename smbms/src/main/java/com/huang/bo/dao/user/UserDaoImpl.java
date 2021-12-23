package com.huang.bo.dao.user;

import com.huang.bo.dao.BaseDao;
import com.huang.bo.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
