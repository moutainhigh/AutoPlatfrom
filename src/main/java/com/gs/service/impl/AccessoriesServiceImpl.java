package com.gs.service.impl;

import com.gs.bean.Accessories;
import com.gs.bean.AccessoriesType;
import com.gs.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.AccessoriesDAO;
import com.gs.service.AccessoriesService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:53
*/
@Service
public class AccessoriesServiceImpl implements AccessoriesService {

	@Resource
	private AccessoriesDAO accessoriesDAO;

	public int insert(Accessories accessories) { return accessoriesDAO.insert(accessories); }
	public int batchInsert(List<Accessories> list) { return accessoriesDAO.batchInsert(list); }
	public int delete(Accessories accessories) { return accessoriesDAO.delete(accessories); }
	public int deleteById(String id) {
        return accessoriesDAO.deleteById(id);
    }
	public int batchDelete(List<Accessories> list) { return accessoriesDAO.batchDelete(list); }
	public int update(Accessories accessories) { return accessoriesDAO.update(accessories); }
	public int batchUpdate(List<Accessories> list) { return accessoriesDAO.batchUpdate(list); }
	public List<Accessories> queryAll(User user) { return accessoriesDAO.queryAll(user); }
	public List<Accessories> queryByStatus(String status, User user) { return accessoriesDAO.queryByStatus(status, user); }
	public Accessories query(Accessories accessories, User user) { return accessoriesDAO.query(accessories, user); }
	public Accessories queryById(String id) { return accessoriesDAO.queryById(id); }
	public List<Accessories> queryByPager(Pager pager, User user) { return accessoriesDAO.queryByPager(pager, user); }
	public int count(User user) { return accessoriesDAO.count(user); }
	public int inactive(String id) { return accessoriesDAO.inactive(id); }
	public int active(String id) { return accessoriesDAO.active(id); }
	public List<Accessories> queryByStatusPager(String accStatus, Pager pager, User user) {
		return accessoriesDAO.queryByStatusPager(accStatus, pager, user);
	}

	public int countByStatus(String status, User user) {
		return accessoriesDAO.countByStatus(status, user);
	}

	public List<Accessories> queryByIdPager(String id, Pager pager, User user) {
		return accessoriesDAO.queryByIdPager(id,pager, user);
	}

	public int countByTypeId(String accTypeId, User user) {
		return accessoriesDAO.countByTypeId(accTypeId, user);
	}

	public int countByCondition(Accessories accessories, User user) { return accessoriesDAO.countByCondition(accessories, user); }

	public List<Accessories> queryByCondition(Pager pager, Accessories accessories, User user){
		return accessoriesDAO.queryByCondition(pager, accessories, user);
	}

	public void updateIdle(String id,  int lastCount, User user) {
		accessoriesDAO.updateIdle(id, lastCount, user);
	}

	public void updateAccUseTime(String id) {
		accessoriesDAO.updateAccUseTime(id);
	}

	public void updateTotalAndIdle(List<Accessories> accessories) {
		accessoriesDAO.updateTotalAndIdle(accessories);
	}

	public Accessories queryByIdTotalAndIdle(String accId) {
		return accessoriesDAO.queryByIdTotalAndIdle(accId);
	}

	@Override
	public List<Accessories> queryByConditionTotal(String startTime, String endTime, String type, String companyId,String accTypeId) {
		return accessoriesDAO.queryByConditionTotal(startTime,endTime,type,companyId,accTypeId);
	}

	@Override
	public List<Accessories> queryByConditionIdle(String startTime, String endTime, String type, String companyId,String accTypeId) {
		return accessoriesDAO.queryByConditionIdle(startTime,endTime,type,companyId,accTypeId);
	}

}