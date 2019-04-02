package com.gs.service.impl;

import com.gs.bean.Module;
import com.gs.bean.User;
import com.gs.common.bean.Pager4EasyUI;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.ModuleDAO;
import com.gs.service.ModuleService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class ModuleServiceImpl implements ModuleService {

    @Resource
    private ModuleDAO moduleDAO;

    @Override
    public int insert(Module module) {
        return moduleDAO.insert(module);
    }

    @Override
    public int batchInsert(List<Module> list) {
        return moduleDAO.batchInsert(list);
    }

    @Override
    public int delete(Module module) {
        return moduleDAO.delete(module);
    }

    @Override
    public int deleteById(String id) {
        return moduleDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<Module> list) {
        return moduleDAO.batchDelete(list);
    }

    @Override
    public int update(Module module) {
        return moduleDAO.update(module);
    }

    @Override
    public int batchUpdate(List<Module> list) {
        return moduleDAO.batchUpdate(list);
    }

    @Override
    public List<Module> queryAll(User user) {
        return moduleDAO.queryAll(user);
    }

    @Override
    public List<Module> queryByStatus(String status, User user) {
        return moduleDAO.queryByStatus(status, user);
    }

    @Override
    public Module query(Module module, User user) {
        return moduleDAO.query(module, user);
    }

    @Override
    public Module queryById(String id) {
        return moduleDAO.queryById(id);
    }

    @Override
    public List<Module> queryByPager(Pager pager, User user) {
        return moduleDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return moduleDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return moduleDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return moduleDAO.active(id);
    }

    @Override
    public List<Module> queryByStatusPager(String moduleStatus, Pager pager) {
        return moduleDAO.queryByStatusPager(moduleStatus, pager);
    }

    @Override
    public int countByStatus(String moduleStatus) {
        return moduleDAO.countByStatus(moduleStatus);
    }

    @Override
    public int queryModuleNameIsExist(String moduleName) {
        return moduleDAO.queryModuleNameIsExist(moduleName);
    }
}