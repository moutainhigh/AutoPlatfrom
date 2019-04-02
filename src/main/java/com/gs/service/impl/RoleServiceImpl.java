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
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDAO roleDAO;

    @Override
    public int insert(Role role) {
        return roleDAO.insert(role);
    }

    @Override
    public int batchInsert(List<Role> list) {
        return roleDAO.batchInsert(list);
    }

    @Override
    public int delete(Role role) {
        return roleDAO.delete(role);
    }

    @Override
    public int deleteById(String id) {
        return roleDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<Role> list) {
        return roleDAO.batchDelete(list);
    }

    @Override
    public int update(Role role) {
        return roleDAO.update(role);
    }

    @Override
    public int batchUpdate(List<Role> list) {
        return roleDAO.batchUpdate(list);
    }

    @Override
    public List<Role> queryAll(User user) {
        return roleDAO.queryAll(user);
    }

    @Override
    public List<Role> queryByStatus(String status, User user) {
        return roleDAO.queryByStatus(status, user);
    }

    @Override
    public Role query(Role role, User user) {
        return roleDAO.query(role, user);
    }

    @Override
    public Role queryById(String id) {
        return roleDAO.queryById(id);
    }

    @Override
    public List<Role> queryByPager(Pager pager, User user) {
        return roleDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return roleDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return roleDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return roleDAO.active(id);
    }

    @Override
    public List<Role> queryCAdminAndSOAdmin() {
        return roleDAO.queryCAdminAndSOAdmin();
    }

    @Override
    public Role queryByName(String roleName) {
        return roleDAO.queryByName(roleName);
    }

    @Override
    public Role queryByUserId(String userId) {
        return roleDAO.queryByUserId(userId);
    }

    @Override
    public List<Role> queryByCompanyRole() {
        return roleDAO.queryByCompanyRole();
    }

    @Override
    public List<Role> queryByCompanyRoleAll() {
        return roleDAO.queryByCompanyRoleAll();
    }

    @Override
    public List<Role> queryRole() {
        return roleDAO.queryRole();
    }

    @Override
    public int queryRoleNameIsExist(String roleName) {
        return roleDAO.queryRoleNameIsExist(roleName);
    }

    @Override
    public int queryRoleDesIsExist(String roleDes) {
        return roleDAO.queryRoleDesIsExist(roleDes);
    }
}