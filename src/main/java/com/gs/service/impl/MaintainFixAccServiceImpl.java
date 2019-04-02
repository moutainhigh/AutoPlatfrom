package com.gs.service.impl;

import com.gs.bean.MaintainFixAcc;
import com.gs.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.MaintainFixAccDAO;
import com.gs.service.MaintainFixAccService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class MaintainFixAccServiceImpl implements MaintainFixAccService {

    @Resource
    private MaintainFixAccDAO maintainFixAccDAO;

    @Override
    public int insert(MaintainFixAcc maintainFixAcc) {
        return maintainFixAccDAO.insert(maintainFixAcc);
    }

    @Override
    public int batchInsert(List<MaintainFixAcc> list) {
        return maintainFixAccDAO.batchInsert(list);
    }

    @Override
    public int delete(MaintainFixAcc maintainFixAcc) {
        return maintainFixAccDAO.delete(maintainFixAcc);
    }

    @Override
    public int deleteById(String id) {
        return maintainFixAccDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<MaintainFixAcc> list) {
        return maintainFixAccDAO.batchDelete(list);
    }

    @Override
    public int update(MaintainFixAcc maintainFixAcc) {
        return maintainFixAccDAO.update(maintainFixAcc);
    }

    @Override
    public int batchUpdate(List<MaintainFixAcc> list) {
        return maintainFixAccDAO.batchUpdate(list);
    }

    @Override
    public List<MaintainFixAcc> queryAll(User user) {
        return maintainFixAccDAO.queryAll(user);
    }

    @Override
    public List<MaintainFixAcc> queryByStatus(String status, User user) {
        return maintainFixAccDAO.queryByStatus(status, user);
    }

    @Override
    public MaintainFixAcc query(MaintainFixAcc maintainFixAcc, User user) {
        return maintainFixAccDAO.query(maintainFixAcc, user);
    }

    @Override
    public MaintainFixAcc queryById(String id) {
        return maintainFixAccDAO.queryById(id);
    }

    @Override
    public List<MaintainFixAcc> queryByPager(Pager pager, User user) {
        return maintainFixAccDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return maintainFixAccDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return maintainFixAccDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return maintainFixAccDAO.active(id);
    }

    @Override
    public List<MaintainFixAcc> queryAllByMaintainId(String[] ids) {
        return maintainFixAccDAO.queryAllByMaintainId(ids);
    }


    @Override
    public List<MaintainFixAcc> queryAllByPager(String id, User user, Pager pager) {
        return maintainFixAccDAO.queryAllByPager(id, user, pager);
    }

    @Override
    public int queryAllByCount(String id, User user) {
        return maintainFixAccDAO.queryAllByCount(id, user);
    }

}