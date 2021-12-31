package com.huang.bo.service.role;

import com.huang.bo.dao.BaseDao;
import com.huang.bo.dao.role.RoleDao;
import com.huang.bo.dao.role.RoleDaoImpl;
import com.huang.bo.pojo.Role;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Description TODO
 * @Author huangbo1221
 * @Date 2021/12/30 23:10
 * @Version 1.0
 */
public class RoleServiceImpl implements RoleService{
    private RoleDao roleDao;

    public RoleServiceImpl() {
        roleDao = new RoleDaoImpl();
    }
    @Override
    public List<Role> getRoleList() {
        Connection connection = null;
        List<Role> roleList = null;
        try {
            connection = BaseDao.getConnection();
            roleList = roleDao.getRoleList(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.CloseResources(connection, null, null);
        }
        return roleList;
    }

    @Test
    public void test() {
        RoleService roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoleList();
        System.out.println(roleList.size());
    }
}
