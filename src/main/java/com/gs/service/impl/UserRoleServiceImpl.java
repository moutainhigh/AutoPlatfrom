package com.gs.service.impl;

import com.gs.bean.User;
import com.gs.bean.UserRole;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.UserRoleDAO;
import com.gs.service.UserRoleService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Resource
	private UserRoleDAO userRoleDAO;

	public int insert(UserRole userRole) { return userRoleDAO.insert(userRole); }
	public int batchInsert(List<UserRole> list) { return userRoleDAO.batchInsert(list); }
	public int delete(UserRole userRole) { return userRoleDAO.delete(userRole); }
	public int deleteById(String id) {
        return userRoleDAO.deleteById(id);
    }
	public int batchDelete(List<UserRole> list) { return userRoleDAO.batchDelete(list); }
	public int update(UserRole userRole) { return userRoleDAO.update(userRole); }
	public int batchUpdate(List<UserRole> list) { return userRoleDAO.batchUpdate(list); }
	public List<UserRole> queryAll(User user) { return userRoleDAO.queryAll(user); }
	public List<UserRole> queryByStatus(String status, User user) { return userRoleDAO.queryByStatus(status, user); }
	public UserRole query(UserRole userRole, User user) { return userRoleDAO.query(userRole, user); }
	public UserRole queryById(String id) { return userRoleDAO.queryById(id); }
	public List<UserRole> queryByPager(Pager pager, User user) { return userRoleDAO.queryByPager(pager, user); }
	public int count(User user) { return userRoleDAO.count(user); }
	public int inactive(String id) { return userRoleDAO.inactive(id); }
	public int active(String id) { return userRoleDAO.active(id); }

	public void updateByRole(UserRole userRole) {
		 userRoleDAO.updateByRole(userRole);
	}
}