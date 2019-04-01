package com.gs.dao;

import java.util.List;

import com.gs.bean.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 根据角色名称获取权限字符串
 *
 */
@Repository
public interface RolePermissionDAO {

	/**根据角色名查询它拥有的权限*/
	public List<String> queryAllPermissionByRoleName(String roleName);

	/**根据角色编号或模块编号来查询权限*/
	public List<String> queryByRoleIdOrMeduleId(@Param("roleId") String roleId, @Param("moduleId") String moduleId);

	/**根据角色编号和模块编号删除一个或多个权限*/
	public void delByRoleIdAndPermissionId(@Param("permissionIds") String[] permissionIds, @Param("roleId") String roleId);

	/**根据角色编号和模块编号添加一个或多个权限*/
	public void addByRoleIdAndPermissionId(List<RolePermission> rps);
}
