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
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:58:54
 */
@Service
public class CheckinServiceImpl implements CheckinService {

    @Resource
    private CheckinDAO checkinDAO;

    public int insert(Checkin checkin) {
        return checkinDAO.insert(checkin);
    }

    public int batchInsert(List<Checkin> list) {
        return checkinDAO.batchInsert(list);
    }

    public int delete(Checkin checkin) {
        return checkinDAO.delete(checkin);
    }

    public int deleteById(String id) {
        return checkinDAO.deleteById(id);
    }

    public int batchDelete(List<Checkin> list) {
        return checkinDAO.batchDelete(list);
    }

    public int update(Checkin checkin) {
        return checkinDAO.update(checkin);
    }

    public int batchUpdate(List<Checkin> list) {
        return checkinDAO.batchUpdate(list);
    }

    public List<Checkin> queryAll(User user) {
        return checkinDAO.queryAll(user);
    }

    public List<Checkin> queryByStatus(String status, User user) {
        return checkinDAO.queryByStatus(status, user);
    }

    public Checkin query(Checkin checkin, User user) {
        return checkinDAO.query(checkin, user);
    }

    public Checkin queryById(String id) {
        return checkinDAO.queryById(id);
    }

    public List<Checkin> queryByPager(Pager pager, User user) {
        return checkinDAO.queryByPager(pager, user);
    }

    public int count(User user) {
        return checkinDAO.count(user);
    }

    public int inactive(String id) {
        return checkinDAO.inactive(id);
    }

    public int active(String id) {
        return checkinDAO.active(id);
    }

    public int countByStatus(String status, User user) {
        return checkinDAO.countByStatus(status, user);
    }

    public List<Checkin> queryPagerByStatus(Pager pager, String status, User user) {
        return checkinDAO.queryPagerByStatus(pager, status, user);
    }

    public int countByCondition(Checkin checkin, User user) {
        return checkinDAO.countByCondition(checkin, user);
    }

    public List<Checkin> queryPagerByCondition(Pager pager, Checkin checkin, User user) {
        return checkinDAO.queryPagerByCondition(pager, checkin, user);
    }

    public Checkin queryByTrackStatus(String userId, User user) {
        return checkinDAO.queryByTrackStatus(userId, user);
    }

    @Override
    public List<Checkin> queryByTop(int top, User user) {
        return checkinDAO.queryByTop(top, user);
    }

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