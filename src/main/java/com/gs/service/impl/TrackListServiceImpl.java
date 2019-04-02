package com.gs.service.impl;

import com.gs.bean.TrackList;
import com.gs.bean.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.TrackListDAO;
import com.gs.service.TrackListService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class TrackListServiceImpl implements TrackListService {

    @Resource
    private TrackListDAO trackListDAO;

    @Override
    public int insert(TrackList trackList) {
        return trackListDAO.insert(trackList);
    }

    @Override
    public int batchInsert(List<TrackList> list) {
        return trackListDAO.batchInsert(list);
    }

    @Override
    public int delete(TrackList trackList) {
        return trackListDAO.delete(trackList);
    }

    @Override
    public int deleteById(String id) {
        return trackListDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<TrackList> list) {
        return trackListDAO.batchDelete(list);
    }

    @Override
    public int update(TrackList trackList) {
        return trackListDAO.update(trackList);
    }

    @Override
    public int batchUpdate(List<TrackList> list) {
        return trackListDAO.batchUpdate(list);
    }

    @Override
    public List<TrackList> queryAll(User user) {
        return trackListDAO.queryAll(user);
    }

    @Override
    public List<TrackList> queryByStatus(String status, User user) {
        return trackListDAO.queryByStatus(status, user);
    }

    @Override
    public TrackList query(TrackList trackList, User user) {
        return trackListDAO.query(trackList, user);
    }

    @Override
    public TrackList queryById(String id) {
        return trackListDAO.queryById(id);
    }

    @Override
    public List<TrackList> queryByPager(Pager pager, User user) {
        return trackListDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return trackListDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return trackListDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return trackListDAO.active(id);
    }

}