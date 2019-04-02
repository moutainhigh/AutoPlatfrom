package com.gs.dao;

import com.gs.bean.Permission;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:35:15
 */
@Repository
public interface PermissionDAO extends BaseDAO<String, Permission> {

    /**根据模块编号来查询权限*/
     List<Permission> queryByModuleId(String moduleId);

    /**根据模块编号来分页*/
     List<Permission> queryByModulePager(@Param("moduleId")String moduleId, @Param("pager")Pager pager);

    /**根据状态来分页*/
     List<Permission> queryByStatusPager(@Param("status")String status, @Param("pager")Pager pager);

    /**根据模块编号来统计*/
     int countModule(String moduleId);

    /**根据状态来统计*/
     int countStatus(String status);

    /**根据一段字符串查询权限表是否有这个权限名*/
     int queryPNIsExist(String pName);

    /**根据一段字符串查询权限表是否有这个中文权限名*/
     int queryPZHNExist(String zhName);
}