package com.gs.service.impl;

import com.gs.bean.Checkin;
import com.gs.bean.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.CheckinDAO;
import com.gs.service.CheckinService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class CheckinServiceImpl implements CheckinService {

    @Resource
    private CheckinDAO checkinDAO;

    @Override
    public int insert(Checkin checkin) {
        return checkinDAO.insert(checkin);
    }

    @Override
    public int batchInsert(List<Checkin> list) {
        return checkinDAO.batchInsert(list);
    }

    @Override
    public int delete(Checkin checkin) {
        return checkinDAO.delete(checkin);
    }

    @Override
    public int deleteById(String id) {
        return checkinDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<Checkin> list) {
        return checkinDAO.batchDelete(list);
    }

    @Override
    public int update(Checkin checkin) {
        return checkinDAO.update(checkin);
    }

    @Override
    public int batchUpdate(List<Checkin> list) {
        return checkinDAO.batchUpdate(list);
    }

    @Override
    public List<Checkin> queryAll(User user) {
        return checkinDAO.queryAll(user);
    }

    @Override
    public List<Checkin> queryByStatus(String status, User user) {
        return checkinDAO.queryByStatus(status, user);
    }

    @Override
    public Checkin query(Checkin checkin, User user) {
        return checkinDAO.query(checkin, user);
    }

    @Override
    public Checkin queryById(String id) {
        return checkinDAO.queryById(id);
    }

    @Override
    public List<Checkin> queryByPager(Pager pager, User user) {
        return checkinDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return checkinDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return checkinDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return checkinDAO.active(id);
    }

    @Override
    public int countByStatus(String status, User user) {
        return checkinDAO.countByStatus(status, user);
    }

    @Override
    public List<Checkin> queryPagerByStatus(Pager pager, String status, User user) {
        return checkinDAO.queryPagerByStatus(pager, status, user);
    }

    @Override
    public int countByCondition(Checkin checkin, User user) {
        return checkinDAO.countByCondition(checkin, user);
    }

    @Override
    public List<Checkin> queryPagerByCondition(Pager pager, Checkin checkin, User user) {
        return checkinDAO.queryPagerByCondition(pager, checkin, user);
    }

    @Override
    public Checkin queryByTrackStatus(String userId, User user) {
        return checkinDAO.queryByTrackStatus(userId, user);
    }

    @Override
    public List<Checkin> queryByTop(int top, User user) {
        return checkinDAO.queryByTop(top, user);
    }

    @Override
    public List<Checkin> queryMyName(User user) { return checkinDAO.queryMyName(user); }

    @Override
    public int queryByPhone(String phone, User user) {
        return checkinDAO.queryByPhone(phone, user);
    }

    @Override
    public int updateCheckinByPhone(User user) {
        return checkinDAO.updateCheckinByPhone(user);
    }
}