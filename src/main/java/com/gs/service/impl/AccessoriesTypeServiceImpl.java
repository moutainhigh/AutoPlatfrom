package com.gs.service.impl;

import com.gs.bean.AccessoriesType;
import com.gs.bean.Module;
import com.gs.bean.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.AccessoriesTypeDAO;
import com.gs.service.AccessoriesTypeService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:53
 */
@Service
public class AccessoriesTypeServiceImpl implements AccessoriesTypeService {

    @Resource
    private AccessoriesTypeDAO accessoriesTypeDAO;

    @Override
    public int insert(AccessoriesType accessoriesType) {
        return accessoriesTypeDAO.insert(accessoriesType);
    }

    @Override
    public int batchInsert(List<AccessoriesType> list) {
        return accessoriesTypeDAO.batchInsert(list);
    }

    @Override
    public int delete(AccessoriesType accessoriesType) {
        return accessoriesTypeDAO.delete(accessoriesType);
    }

    @Override
    public int deleteById(String id) {
        return accessoriesTypeDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<AccessoriesType> list) {
        return accessoriesTypeDAO.batchDelete(list);
    }

    @Override
    public int update(AccessoriesType accessoriesType) {
        return accessoriesTypeDAO.update(accessoriesType);
    }

    @Override
    public int batchUpdate(List<AccessoriesType> list) {
        return accessoriesTypeDAO.batchUpdate(list);
    }

    @Override
    public List<AccessoriesType> queryAll(User user) {
        return accessoriesTypeDAO.queryAll(user);
    }

    @Override
    public List<AccessoriesType> queryByStatus(String status, User user) {
        return accessoriesTypeDAO.queryByStatus(status, user);
    }

    @Override
    public AccessoriesType query(AccessoriesType accessoriesType, User user) {
        return accessoriesTypeDAO.query(accessoriesType, user);
    }

    @Override
    public AccessoriesType queryById(String id) {
        return accessoriesTypeDAO.queryById(id);
    }

    @Override
    public List<AccessoriesType> queryByPager(Pager pager, User user) {
        return accessoriesTypeDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return accessoriesTypeDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return accessoriesTypeDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return accessoriesTypeDAO.active(id);
    }

    @Override
    public List<AccessoriesType> queryByStatusPager(String accTypeStatus, Pager pager, User user) {
        return accessoriesTypeDAO.queryByStatusPager(accTypeStatus, pager, user);
    }

    @Override
    public int countByStatus(String status, User user) {
        return accessoriesTypeDAO.countByStatus(status, user);
    }

    @Override
    public int countByCondition(AccessoriesType accessoriesType, User user) {
        return accessoriesTypeDAO.countByCondition(accessoriesType, user);
    }

    @Override
    public List<AccessoriesType> queryByCondition(Pager pager, AccessoriesType accessoriesType, User user) {
        return accessoriesTypeDAO.queryByCondition(pager, accessoriesType, user);
    }

    @Override
    public List<AccessoriesType> queryByCompany(String companyId) {
        return accessoriesTypeDAO.queryByCompany(companyId);
    }

}