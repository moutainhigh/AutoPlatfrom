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
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleDAO userRoleDAO;

    @Override
    public int insert(UserRole userRole) {
        return userRoleDAO.insert(userRole);
    }

    @Override
    public int batchInsert(List<UserRole> list) {
        return userRoleDAO.batchInsert(list);
    }

    @Override
    public int delete(UserRole userRole) {
        return userRoleDAO.delete(userRole);
    }

    @Override
    public int deleteById(String id) {
        return userRoleDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<UserRole> list) {
        return userRoleDAO.batchDelete(list);
    }

    @Override
    public int update(UserRole userRole) {
        return userRoleDAO.update(userRole);
    }

    @Override
    public int batchUpdate(List<UserRole> list) {
        return userRoleDAO.batchUpdate(list);
    }

    @Override
    public List<UserRole> queryAll(User user) {
        return userRoleDAO.queryAll(user);
    }

    @Override
    public List<UserRole> queryByStatus(String status, User user) {
        return userRoleDAO.queryByStatus(status, user);
    }

    @Override
    public UserRole query(UserRole userRole, User user) {
        return userRoleDAO.query(userRole, user);
    }

    @Override
    public UserRole queryById(String id) {
        return userRoleDAO.queryById(id);
    }

    @Override
    public List<UserRole> queryByPager(Pager pager, User user) {
        return userRoleDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return userRoleDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return userRoleDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return userRoleDAO.active(id);
    }

    @Override
    public void updateByRole(UserRole userRole) {
        userRoleDAO.updateByRole(userRole);
    }
}