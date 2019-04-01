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
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class TrackListServiceImpl implements TrackListService {

	@Resource
	private TrackListDAO trackListDAO;

	public int insert(TrackList trackList) { return trackListDAO.insert(trackList); }
	public int batchInsert(List<TrackList> list) { return trackListDAO.batchInsert(list); }
	public int delete(TrackList trackList) { return trackListDAO.delete(trackList); }
	public int deleteById(String id) {
        return trackListDAO.deleteById(id);
    }
	public int batchDelete(List<TrackList> list) { return trackListDAO.batchDelete(list); }
	public int update(TrackList trackList) { return trackListDAO.update(trackList); }
	public int batchUpdate(List<TrackList> list) { return trackListDAO.batchUpdate(list); }
	public List<TrackList> queryAll(User user) { return trackListDAO.queryAll(user); }
	public List<TrackList> queryByStatus(String status,User user) { return trackListDAO.queryByStatus(status,user); }
	public TrackList query(TrackList trackList,User user) { return trackListDAO.query(trackList,user); }
	public TrackList queryById(String id) { return trackListDAO.queryById(id); }
	public List<TrackList> queryByPager(Pager pager,User user) { return trackListDAO.queryByPager(pager,user); }
	public int count(User user) { return trackListDAO.count(user); }
	public int inactive(String id) { return trackListDAO.inactive(id); }
	public int active(String id) { return trackListDAO.active(id); }

}