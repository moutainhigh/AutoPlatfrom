package com.gs.service.impl;

import com.gs.bean.Accessories;
import com.gs.bean.AccessoriesType;
import com.gs.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.AccessoriesDAO;
import com.gs.service.AccessoriesService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:53
 */
@Service
public class AccessoriesServiceImpl implements AccessoriesService {

    @Resource
    private AccessoriesDAO accessoriesDAO;

    @Override
    public int insert(Accessories accessories) {
        return accessoriesDAO.insert(accessories);
    }

    @Override
    public int batchInsert(List<Accessories> list) {
        return accessoriesDAO.batchInsert(list);
    }

    @Override
    public int delete(Accessories accessories) {
        return accessoriesDAO.delete(accessories);
    }

    @Override
    public int deleteById(String id) {
        return accessoriesDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<Accessories> list) {
        return accessoriesDAO.batchDelete(list);
    }

    @Override
    public int update(Accessories accessories) {
        return accessoriesDAO.update(accessories);
    }

    @Override
    public int batchUpdate(List<Accessories> list) {
        return accessoriesDAO.batchUpdate(list);
    }

    @Override
    public List<Accessories> queryAll(User user) {
        return accessoriesDAO.queryAll(user);
    }

    @Override
    public List<Accessories> queryByStatus(String status, User user) {
        return accessoriesDAO.queryByStatus(status, user);
    }

    @Override
    public Accessories query(Accessories accessories, User user) {
        return accessoriesDAO.query(accessories, user);
    }

    @Override
    public Accessories queryById(String id) {
        return accessoriesDAO.queryById(id);
    }

    @Override
    public List<Accessories> queryByPager(Pager pager, User user) {
        return accessoriesDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return accessoriesDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return accessoriesDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return accessoriesDAO.active(id);
    }

    @Override
    public List<Accessories> queryByStatusPager(String accStatus, Pager pager, User user) {
        return accessoriesDAO.queryByStatusPager(accStatus, pager, user);
    }

    @Override
    public int countByStatus(String status, User user) {
        return accessoriesDAO.countByStatus(status, user);
    }

    @Override
    public List<Accessories> queryByIdPager(String id, Pager pager, User user) {
        return accessoriesDAO.queryByIdPager(id, pager, user);
    }

    @Override
    public int countByTypeId(String accTypeId, User user) {
        return accessoriesDAO.countByTypeId(accTypeId, user);
    }

    @Override
    public int countByCondition(Accessories accessories, User user) {
        return accessoriesDAO.countByCondition(accessories, user);
    }

    @Override
    public List<Accessories> queryByCondition(Pager pager, Accessories accessories, User user) {
        return accessoriesDAO.queryByCondition(pager, accessories, user);
    }

    @Override
    public void updateIdle(String id, int lastCount, User user) {
        accessoriesDAO.updateIdle(id, lastCount, user);
    }

    @Override
    public void updateAccUseTime(String id) {
        accessoriesDAO.updateAccUseTime(id);
    }

    @Override
    public void updateTotalAndIdle(List<Accessories> accessories) {
        accessoriesDAO.updateTotalAndIdle(accessories);
    }

    @Override
    public Accessories queryByIdTotalAndIdle(String accId) {
        return accessoriesDAO.queryByIdTotalAndIdle(accId);
    }

    @Override
    public List<Accessories> queryByConditionTotal(String startTime, String endTime, String type, String companyId, String accTypeId) {
        return accessoriesDAO.queryByConditionTotal(startTime, endTime, type, companyId, accTypeId);
    }

    @Override
    public List<Accessories> queryByConditionIdle(String startTime, String endTime, String type, String companyId, String accTypeId) {
        return accessoriesDAO.queryByConditionIdle(startTime, endTime, type, companyId, accTypeId);
    }

}