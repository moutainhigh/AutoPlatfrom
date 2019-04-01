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
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class MaintainFixAccServiceImpl implements MaintainFixAccService {

	@Resource
	private MaintainFixAccDAO maintainFixAccDAO;

	public int insert(MaintainFixAcc maintainFixAcc) { return maintainFixAccDAO.insert(maintainFixAcc); }
	public int batchInsert(List<MaintainFixAcc> list) { return maintainFixAccDAO.batchInsert(list); }
	public int delete(MaintainFixAcc maintainFixAcc) { return maintainFixAccDAO.delete(maintainFixAcc); }
	public int deleteById(String id) {
        return maintainFixAccDAO.deleteById(id);
    }
	public int batchDelete(List<MaintainFixAcc> list) { return maintainFixAccDAO.batchDelete(list); }
	public int update(MaintainFixAcc maintainFixAcc) { return maintainFixAccDAO.update(maintainFixAcc); }
	public int batchUpdate(List<MaintainFixAcc> list) { return maintainFixAccDAO.batchUpdate(list); }
	public List<MaintainFixAcc> queryAll(User user) {
		return maintainFixAccDAO.queryAll(user);
	}
	public List<MaintainFixAcc> queryByStatus(String status, User user) {
		return maintainFixAccDAO.queryByStatus(status, user);
	}
	public MaintainFixAcc query(MaintainFixAcc maintainFixAcc, User user) {
		return maintainFixAccDAO.query(maintainFixAcc, user);
	}
	public MaintainFixAcc queryById(String id) {
		return maintainFixAccDAO.queryById(id);
	}
	public List<MaintainFixAcc> queryByPager(Pager pager, User user) {
		return maintainFixAccDAO.queryByPager(pager, user);
	}
	public int count(User user) {
		return maintainFixAccDAO.count(user);
	}
	public int inactive(String id) { return maintainFixAccDAO.inactive(id); }
	public int active(String id) { return maintainFixAccDAO.active(id); }
	public List<MaintainFixAcc> queryAllByMaintainId(String[] ids) {
		return maintainFixAccDAO.queryAllByMaintainId(ids);
	}


	public List<MaintainFixAcc> queryAllByPager(String id,User user,Pager pager) {
		return maintainFixAccDAO.queryAllByPager(id, user, pager);
	}

	public int queryAllByCount(String id,User user) {
		return maintainFixAccDAO.queryAllByCount(id,user);
	}

}