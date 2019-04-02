package com.gs.service.impl;

import com.gs.bean.ChargeBill;
import com.gs.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.ChargeBillDAO;
import com.gs.service.ChargeBillService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class ChargeBillServiceImpl implements ChargeBillService {

    @Resource
    private ChargeBillDAO chargeBillDAO;

    @Override
    public int insert(ChargeBill chargeBill) {
        return chargeBillDAO.insert(chargeBill);
    }

    @Override
    public int batchInsert(List<ChargeBill> list) {
        return chargeBillDAO.batchInsert(list);
    }

    @Override
    public int delete(ChargeBill chargeBill) {
        return chargeBillDAO.delete(chargeBill);
    }

    @Override
    public int deleteById(String id) {
        return chargeBillDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<ChargeBill> list) {
        return chargeBillDAO.batchDelete(list);
    }

    @Override
    public int update(ChargeBill chargeBill) {
        return chargeBillDAO.update(chargeBill);
    }

    @Override
    public int batchUpdate(List<ChargeBill> list) {
        return chargeBillDAO.batchUpdate(list);
    }

    @Override
    public List<ChargeBill> queryAll(User user) {
        return chargeBillDAO.queryAll(user);
    }

    @Override
    public List<ChargeBill> queryByStatus(String status, User user) {
        return chargeBillDAO.queryByStatus(status, user);
    }

    @Override
    public ChargeBill query(ChargeBill chargeBill, User user) {
        return chargeBillDAO.query(chargeBill, user);
    }

    @Override
    public ChargeBill queryById(String id) {
        return chargeBillDAO.queryById(id);
    }

    @Override
    public List<ChargeBill> queryByPager(Pager pager, User user) {
        return chargeBillDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return chargeBillDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return chargeBillDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return chargeBillDAO.active(id);
    }

    @Override
    public int countByStatus(String status, User user) {
        return chargeBillDAO.countByStatus(status, user);
    }

    @Override
    public List<ChargeBill> queryPagerByStatus(Pager pager, String status, User user) {
        return chargeBillDAO.queryPagerByStatus(pager, status, user);
    }

    @Override
    public int countByCondition(ChargeBill chargeBill, User user) {
        return chargeBillDAO.countByCondition(chargeBill, user);
    }

    @Override
    public List<ChargeBill> queryPagerByCondition(Pager pager, ChargeBill chargeBill, User user) {
        return chargeBillDAO.queryPagerByCondition(pager, chargeBill, user);
    }

    @Override
    public List<ChargeBill> queryByDefault(String maintainOrFix, String userId) {
        return chargeBillDAO.queryByDefault(maintainOrFix, userId);
    }

    @Override
    public List<ChargeBill> queryByCondition(String startTime, String endTime, String maintainOrFix, String type, String userId) {
        return chargeBillDAO.queryByCondition(startTime, endTime, maintainOrFix, type, userId);
    }

    @Override
    public List<ChargeBill> queryMyName(User user){
        return chargeBillDAO.queryMyName(user);
    }
}