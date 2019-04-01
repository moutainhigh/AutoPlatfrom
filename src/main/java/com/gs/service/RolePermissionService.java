package com.gs.service;

import java.util.Collection;
import java.util.List;

import com.gs.bean.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.Permission;

/**
 * 根据角色名称获取权限字符串
 *
 */
public interface RolePermissionService {

	/**根据角色名查询它拥有的权限*/
	public Collection<Permission> queryAllPermissionByRoleName(String roleName);

	/**根据角色编号或模块编号来查询权限*/
	public List<String> queryByRoleIdOrMeduleId(String roleId, String moduleId);

	/**根据角色编号和模块编号删除一个或多个权限*/
	public void delByRoleIdAndPermissionId(String[] permissionIds, String roleId);

	/**根据角色编号和模块编号添加一个或多个权限*/
	public void addByRoleIdAndPermissionId(List<RolePermission> rps);
}
