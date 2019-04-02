package com.gs.service.impl;

import com.gs.bean.User;
import com.gs.bean.WorkInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.WorkInfoDAO;
import com.gs.service.WorkInfoService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class WorkInfoServiceImpl implements WorkInfoService {

    @Resource
    private WorkInfoDAO workInfoDAO;

    @Override
    public int insert(WorkInfo workInfo) {
        return workInfoDAO.insert(workInfo);
    }

    @Override
    public int batchInsert(List<WorkInfo> list) {
        return workInfoDAO.batchInsert(list);
    }

    @Override
    public int delete(WorkInfo workInfo) {
        return workInfoDAO.delete(workInfo);
    }

    @Override
    public int deleteById(String id) {
        return workInfoDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<WorkInfo> list) {
        return workInfoDAO.batchDelete(list);
    }

    @Override
    public int update(WorkInfo workInfo) {
        return workInfoDAO.update(workInfo);
    }

    @Override
    public int batchUpdate(List<WorkInfo> list) {
        return workInfoDAO.batchUpdate(list);
    }

    @Override
    public List<WorkInfo> queryAll(User user) {
        return workInfoDAO.queryAll(user);
    }

    @Override
    public List<WorkInfo> queryByStatus(String status, User user) {
        return workInfoDAO.queryByStatus(status, user);
    }

    @Override
    public WorkInfo query(WorkInfo workInfo, User user) {
        return workInfoDAO.query(workInfo, user);
    }

    @Override
    public WorkInfo queryById(String id) {
        return workInfoDAO.queryById(id);
    }

    @Override
    public List<WorkInfo> queryByPager(Pager pager, User user) {
        return workInfoDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return workInfoDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return workInfoDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return workInfoDAO.active(id);
    }

    @Override
    public List<WorkInfo> queryWorkUserId(Pager pager, String userId) {
        return workInfoDAO.queryWorkUserId(pager, userId);
    }

    @Override
    public int countWorkUserId(String userId) {
        return workInfoDAO.countWorkUserId(userId);
    }

    @Override
    public List<WorkInfo> queryByPager_N(Pager pager, User user) {
        return workInfoDAO.queryByPager_N(pager, user);
    }

    @Override
    public int count_N(User user) {
        return workInfoDAO.count_N(user);
    }

    @Override
    public List<WorkInfo> queryWorkUserId_N(Pager pager, String userId) {
        return workInfoDAO.queryWorkUserId_N(pager, userId);
    }

    @Override
    public int countWorkUserId_N(String userId) {
        return workInfoDAO.countWorkUserId_N(userId);
    }

    @Override
    public List<WorkInfo> queryByPager_Y(Pager pager, User user) {
        return workInfoDAO.queryByPager_Y(pager, user);
    }

    @Override
    public int count_Y(User user) {
        return workInfoDAO.count_Y(user);
    }

    @Override
    public List<WorkInfo> queryWorkUserId_Y(Pager pager, String userId) {
        return workInfoDAO.queryWorkUserId_Y(pager, userId);
    }

    @Override
    public int countWorkUserId_Y(String userId) {
        return workInfoDAO.countWorkUserId_Y(userId);
    }


    @Override
    public WorkInfo queryByRocordIdUserId(String recordId) {
        return workInfoDAO.queryByRocordIdUserId(recordId);
    }

    @Override
    public List<WorkInfo> queryByDefault(String maintainOrFix, String companyId) {
        return workInfoDAO.queryByDefault(maintainOrFix, companyId);
    }

    @Override
    public List<WorkInfo> queryByCondition(String startTime, String endTime, String maintainOrFix, String type, String companyId) {
        return workInfoDAO.queryByCondition(startTime, endTime, maintainOrFix, type, companyId);
    }
}