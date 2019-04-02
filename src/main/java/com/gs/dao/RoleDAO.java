package com.gs.dao;

import com.gs.bean.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:35:15
*/
@Repository
public interface RoleDAO extends BaseDAO<String, Role>{

    /**查询除了系统超级管理员之外的管理员*/
     List<Role> queryCAdminAndSOAdmin();

    /**根据roleName去查询*/
     Role queryByName(String roleName);

    /**根据userId去查询*/
     Role queryByUserId(String userId);

    /**查询属于公司的角色*/
     List<Role> queryByCompanyRole();

    /**查询属于公司的角色包括人事部管理员*/
     List<Role> queryByCompanyRoleAll();

    /**查询属于公司的全部角色*/
     List<Role> queryRole();

    /**根据一段字符串查询角色表是否有这个英文角色名*/
     int queryRoleNameIsExist(String roleName);

    /**根据一段字符串查询角色表是否有这个中文角色名*/
     int queryRoleDesIsExist(String roleDes);
}