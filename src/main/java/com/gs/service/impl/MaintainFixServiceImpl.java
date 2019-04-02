package com.gs.service.impl;

import com.gs.bean.MaintainFix;
import com.gs.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.MaintainFixDAO;
import com.gs.service.MaintainFixService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class MaintainFixServiceImpl implements MaintainFixService {

    @Resource
    private MaintainFixDAO maintainFixDAO;

    @Override
    public int insert(MaintainFix maintainFix) {
        return maintainFixDAO.insert(maintainFix);
    }

    @Override
    public int batchInsert(List<MaintainFix> list) {
        return maintainFixDAO.batchInsert(list);
    }

    @Override
    public int delete(MaintainFix maintainFix) {
        return maintainFixDAO.delete(maintainFix);
    }

    @Override
    public int deleteById(String id) {
        return maintainFixDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<MaintainFix> list) {
        return maintainFixDAO.batchDelete(list);
    }

    @Override
    public int update(MaintainFix maintainFix) {
        return maintainFixDAO.update(maintainFix);
    }

    @Override
    public int batchUpdate(List<MaintainFix> list) {
        return maintainFixDAO.batchUpdate(list);
    }

    @Override
    public List<MaintainFix> queryAll(User user) {
        return maintainFixDAO.queryAll(user);
    }

    @Override
    public List<MaintainFix> queryByStatus(String status, User user) {
        return maintainFixDAO.queryByStatus(status, user);
    }

    @Override
    public MaintainFix query(MaintainFix maintainFix, User user) {
        return maintainFixDAO.query(maintainFix, user);
    }

    @Override
    public MaintainFix queryById(String id) {
        return maintainFixDAO.queryById(id);
    }

    @Override
    public List<MaintainFix> queryByPager(Pager pager, User user) {
        return maintainFixDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return maintainFixDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return maintainFixDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return maintainFixDAO.active(id);
    }

    @Override
    public List<MaintainFix> queryBymaintainPager(Pager pager, User user) {
        return maintainFixDAO.queryBymaintainPager(pager, user);
    }

    @Override
    public int MaintainCont(User user) {
        return maintainFixDAO.MaintainCont(user);
    }

    @Override
    public List<MaintainFix> byStatusPager(String status, Pager pager, User user) {
        return maintainFixDAO.byStatusPager(status, pager, user);
    }


    @Override
    public int countStatus(String status, User user) {
        return maintainFixDAO.countStatus(status, user);
    }

    @Override
    public List<MaintainFix> searchByPager(String name, Pager pager, User user) {
        return maintainFixDAO.searchByPager(name, pager, user);
    }

    @Override
    public int searCount(String name, User user) {
        return maintainFixDAO.searCount(name, user);
    }


    @Override
    public List<MaintainFix> repairByStatusPager(String status, Pager pager, User user) {
        return maintainFixDAO.repairByStatusPager(status, pager, user);
    }


    @Override
    public int repairCountStatus(String status, User user) {
        return maintainFixDAO.repairCountStatus(status, user);
    }


    @Override
    public List<MaintainFix> searchByRepairPager(String name, Pager pager, User user) {
        return maintainFixDAO.searchByRepairPager(name, pager, user);
    }


    @Override
    public int searRepairCount(String name, User user) {
        return maintainFixDAO.searRepairCount(name, user);
    }

    @Override
    public List<MaintainFix> queryByType(String companyId, String maintainOrFix) {
        return maintainFixDAO.queryByType(companyId, maintainOrFix);
    }
}