package com.huang.bo.dao.role;

import com.huang.bo.dao.BaseDao;
import com.huang.bo.pojo.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName RoleDaoImpl
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/30 23:05
 * @Version 1.0
 */
public class RoleDaoImpl implements RoleDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleDaoImpl.class);
    @Override
    public List<Role> getRoleList(Connection connection) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Role> roleList = new ArrayList();
        if (Objects.isNull(connection)) {
            LOGGER.info("getRoleList method, connection is null!");
            return roleList;
        }
        String sql = "select * from smbms_role";
        Object[] params = {};
        try {
            rs = BaseDao.executeQuery(connection, pstm, rs, sql, params);
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setRoleCode(rs.getString("roleCode"));
                role.setRoleName(rs.getString("roleName"));
                roleList.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.CloseResources(null, pstm, rs);
        }
        return roleList;
    }
}
