package com.gs.service.impl;

import com.gs.bean.Permission;
import com.gs.bean.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.PermissionDAO;
import com.gs.service.PermissionService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class PermissionServiceImpl implements PermissionService {

	@Resource
	private PermissionDAO permissionDAO;

	public int insert(Permission permission) { return permissionDAO.insert(permission); }
	public int batchInsert(List<Permission> list) { return permissionDAO.batchInsert(list); }
	public int delete(Permission permission) { return permissionDAO.delete(permission); }
	public int deleteById(String id) {
        return permissionDAO.deleteById(id);
    }
	public int batchDelete(List<Permission> list) { return permissionDAO.batchDelete(list); }
	public int update(Permission permission) { return permissionDAO.update(permission); }
	public int batchUpdate(List<Permission> list) { return permissionDAO.batchUpdate(list); }
	public List<Permission> queryAll(User user) { return permissionDAO.queryAll(user); }
	public List<Permission> queryByStatus(String status, User user) { return permissionDAO.queryByStatus(status, user); }
	public Permission query(Permission permission, User user) { return permissionDAO.query(permission, user); }
	public Permission queryById(String id) { return permissionDAO.queryById(id); }
	public List<Permission> queryByPager(Pager pager, User user) { return permissionDAO.queryByPager(pager, user); }
	public int count(User user) { return permissionDAO.count(user); }
	public int inactive(String id) { return permissionDAO.inactive(id); }
	public int active(String id) { return permissionDAO.active(id); }
	public List<Permission> queryByModuleId(String moduleId) {
		return permissionDAO.queryByModuleId(moduleId);
	}
	public List<Permission> queryByModulePager(String moduleId, Pager pager) {
		return permissionDAO.queryByModulePager(moduleId, pager);
	}
	public List<Permission> queryByStatusPager(String status, Pager pager) {
		return permissionDAO.queryByStatusPager(status, pager);
	}
	public int countModule(String moduleId) {
        return permissionDAO.countModule(moduleId);
    }
	public int countStatus(String status) {
		return permissionDAO.countStatus(status);
	}

	public int queryPNIsExist(String pName) {
		return permissionDAO.queryPNIsExist(pName);
	}

	public int queryPZHNExist(String zhName) {
		return permissionDAO.queryPZHNExist(zhName);
	}
}