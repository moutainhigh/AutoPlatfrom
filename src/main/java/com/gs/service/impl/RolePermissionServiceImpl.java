package com.gs.service.impl;

import com.gs.bean.RolePermission;
import com.gs.dao.RolePermissionDAO;
import com.gs.service.RolePermissionService;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {
	
	@Resource
	private RolePermissionDAO rolePermissionDAO;
	
	@Override
	public Collection<Permission> queryAllPermissionByRoleName(String roleName) {
		List<String> p = rolePermissionDAO.queryAllPermissionByRoleName(roleName);
		List<Permission> permissions = new ArrayList<Permission>();
		for (String s : p) {
			Permission per = new WildcardPermission(s);
			permissions.add(per);
		}
		return permissions;
	}
	@Override
	public List<String> queryByRoleIdOrMeduleId(String roleId, String moduleId) {
		return rolePermissionDAO.queryByRoleIdOrMeduleId(roleId, moduleId);
	}
	@Override
	public void delByRoleIdAndPermissionId(String[] permissionIds, String roleId) {
		rolePermissionDAO.delByRoleIdAndPermissionId(permissionIds, roleId);
	}
	@Override
	public void addByRoleIdAndPermissionId(List<RolePermission> rps) {
		rolePermissionDAO.addByRoleIdAndPermissionId(rps);
	}

}
