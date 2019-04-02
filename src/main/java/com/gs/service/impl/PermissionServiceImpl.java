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
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionDAO permissionDAO;

    @Override
    public int insert(Permission permission) {
        return permissionDAO.insert(permission);
    }

    @Override
    public int batchInsert(List<Permission> list) {
        return permissionDAO.batchInsert(list);
    }

    @Override
    public int delete(Permission permission) {
        return permissionDAO.delete(permission);
    }

    @Override
    public int deleteById(String id) {
        return permissionDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<Permission> list) {
        return permissionDAO.batchDelete(list);
    }

    @Override
    public int update(Permission permission) {
        return permissionDAO.update(permission);
    }

    @Override
    public int batchUpdate(List<Permission> list) {
        return permissionDAO.batchUpdate(list);
    }

    @Override
    public List<Permission> queryAll(User user) {
        return permissionDAO.queryAll(user);
    }

    @Override
    public List<Permission> queryByStatus(String status, User user) {
        return permissionDAO.queryByStatus(status, user);
    }

    @Override
    public Permission query(Permission permission, User user) {
        return permissionDAO.query(permission, user);
    }

    @Override
    public Permission queryById(String id) {
        return permissionDAO.queryById(id);
    }

    @Override
    public List<Permission> queryByPager(Pager pager, User user) {
        return permissionDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return permissionDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return permissionDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return permissionDAO.active(id);
    }

    @Override
    public List<Permission> queryByModuleId(String moduleId) {
        return permissionDAO.queryByModuleId(moduleId);
    }

    @Override
    public List<Permission> queryByModulePager(String moduleId, Pager pager) {
        return permissionDAO.queryByModulePager(moduleId, pager);
    }

    @Override
    public List<Permission> queryByStatusPager(String status, Pager pager) {
        return permissionDAO.queryByStatusPager(status, pager);
    }

    @Override
    public int countModule(String moduleId) {
        return permissionDAO.countModule(moduleId);
    }

    @Override
    public int countStatus(String status) {
        return permissionDAO.countStatus(status);
    }

    @Override
    public int queryPNIsExist(String pName) {
        return permissionDAO.queryPNIsExist(pName);
    }

    @Override
    public int queryPZHNExist(String zhName) {
        return permissionDAO.queryPZHNExist(zhName);
    }
}