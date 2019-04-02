package com.gs.service.impl;

import com.gs.bean.MaintainRemind;
import com.gs.bean.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.MaintainRemindDAO;
import com.gs.service.MaintainRemindService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class MaintainRemindServiceImpl implements MaintainRemindService {

    @Resource
    private MaintainRemindDAO maintainRemindDAO;

    @Override
    public int insert(MaintainRemind maintainRemind) {
        return maintainRemindDAO.insert(maintainRemind);
    }

    @Override
    public int batchInsert(List<MaintainRemind> list) {
        return maintainRemindDAO.batchInsert(list);
    }

    @Override
    public int delete(MaintainRemind maintainRemind) {
        return maintainRemindDAO.delete(maintainRemind);
    }

    @Override
    public int deleteById(String id) {
        return maintainRemindDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<MaintainRemind> list) {
        return maintainRemindDAO.batchDelete(list);
    }

    @Override
    public int update(MaintainRemind maintainRemind) {
        return maintainRemindDAO.update(maintainRemind);
    }

    @Override
    public int batchUpdate(List<MaintainRemind> list) {
        return maintainRemindDAO.batchUpdate(list);
    }


    @Override
    public List<MaintainRemind> queryAll(User user) {
        return maintainRemindDAO.queryAll(user);
    }

    @Override
    public List<MaintainRemind> queryByStatus(String status, User user) {
        return maintainRemindDAO.queryByStatus(status, user);
    }

    @Override
    public MaintainRemind query(MaintainRemind maintainRemind, User user) {
        return maintainRemindDAO.query(maintainRemind, user);
    }

    @Override
    public MaintainRemind queryById(String id) {
        return maintainRemindDAO.queryById(id);
    }

    @Override
    public List<MaintainRemind> queryByPager(Pager pager, User user) {
        return maintainRemindDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return maintainRemindDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return maintainRemindDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return maintainRemindDAO.active(id);
    }

    @Override
    public int countByCondition(MaintainRemind remind, User user) {
        return maintainRemindDAO.countByCondition(remind, user);
    }

    @Override
    public List<MaintainRemind> queryPagerByCondition(Pager pager, MaintainRemind remind, User user) {
        return maintainRemindDAO.queryPagerByCondition(pager, remind, user);
    }

    @Override
    public List<MaintainRemind> queryByTop(int top, User user) {
        return maintainRemindDAO.queryByTop(top, user);
    }
}