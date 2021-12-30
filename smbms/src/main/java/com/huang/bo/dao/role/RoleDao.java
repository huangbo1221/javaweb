package com.huang.bo.dao.role;

import com.huang.bo.pojo.Role;

import java.sql.Connection;
import java.util.List;

/**
 * @ClassName RoleDao
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/30 23:04
 * @Version 1.0
 */
public interface RoleDao {
    List<Role> getRoleList(Connection connection);
}
