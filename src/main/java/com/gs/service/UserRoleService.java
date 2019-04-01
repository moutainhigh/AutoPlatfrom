package com.gs.service;

import com.gs.bean.UserRole;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface UserRoleService extends BaseService<String, UserRole>{

    /**更新一个用户所对应的角色*/
    public void updateByRole(UserRole userRole);

}