package com.gs.service.impl;

import com.gs.bean.Role;
import com.gs.bean.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.RoleDAO;
import com.gs.service.RoleService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDAO roleDAO;

	public int insert(Role role) { return roleDAO.insert(role); }
	public int batchInsert(List<Role> list) { return roleDAO.batchInsert(list); }
	public int delete(Role role) { return roleDAO.delete(role); }
	public int deleteById(String id) {
        return roleDAO.deleteById(id);
    }
	public int batchDelete(List<Role> list) { return roleDAO.batchDelete(list); }
	public int update(Role role) { return roleDAO.update(role); }
	public int batchUpdate(List<Role> list) { return roleDAO.batchUpdate(list); }
	public List<Role> queryAll(User user) { return roleDAO.queryAll(user); }
	public List<Role> queryByStatus(String status,User user) { return roleDAO.queryByStatus(status, user); }
	public Role query(Role role,User user) { return roleDAO.query(role, user); }
	public Role queryById(String id) { return roleDAO.queryById(id); }
	public List<Role> queryByPager(Pager pager,User user) { return roleDAO.queryByPager(pager, user); }
	public int count(User user) { return roleDAO.count(user); }
	public int inactive(String id) { return roleDAO.inactive(id); }
	public int active(String id) { return roleDAO.active(id); }
	public List<Role> queryCAdminAndSOAdmin() {
		return roleDAO.queryCAdminAndSOAdmin();
	}
	public Role queryByName(String roleName) {
		return roleDAO.queryByName(roleName);
	}
	public Role queryByUserId(String userId) {
		return roleDAO.queryByUserId(userId);
	}
	public List<Role> queryByCompanyRole() {
		return roleDAO.queryByCompanyRole();
	}

	public List<Role> queryByCompanyRoleAll() {
		return roleDAO.queryByCompanyRoleAll();
	}

	public List<Role> queryRole() {
		return roleDAO.queryRole();
	}

	public int queryRoleNameIsExist(String roleName) {
		return roleDAO.queryRoleNameIsExist(roleName);
	}

	public int queryRoleDesIsExist(String roleDes) {
		return roleDAO.queryRoleDesIsExist(roleDes);
	}
}