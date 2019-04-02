package com.gs.dao;

import com.gs.bean.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
/**
*
*
*@author qm
*@since 2017-04-14 16:35:15
*/
@Repository
public interface UserRoleDAO extends BaseDAO<String, UserRole>{

    /**更新一个用户所对应的角色*/
     void updateByRole(UserRole userRole);
}