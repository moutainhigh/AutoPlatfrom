package com.gs.service.impl;

import com.gs.bean.SupplyType;
import com.gs.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.SupplyTypeDAO;
import com.gs.service.SupplyTypeService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class SupplyTypeServiceImpl implements SupplyTypeService {

	@Resource
	private SupplyTypeDAO supplyTypeDAO;

	@Override
	public int insert(SupplyType supplyType) {
		return supplyTypeDAO.insert(supplyType);
	}

	@Override
	public int countByStatus(String status, User user) {
		return supplyTypeDAO.countByStatus(status, user);
	}

	@Override
	public int batchInsert(List<SupplyType> list) {
		return supplyTypeDAO.batchInsert(list);
	}

	@Override
	public int delete(SupplyType supplyType) {
		return supplyTypeDAO.delete(supplyType);
	}

	@Override
	public List<SupplyType> queryPagerByStatus(Pager pager, String status, User user) {
		return supplyTypeDAO.queryPagerByStatus(pager, status, user);
	}

	@Override
	public int deleteById(String id) {
		return supplyTypeDAO.deleteById(id);
	}

	@Override
	public int batchDelete(List<SupplyType> list) {
		return supplyTypeDAO.batchDelete(list);
	}

	@Override
	public int countByCondition(SupplyType supplyType, User user) {
		return supplyTypeDAO.countByCondition(supplyType, user);
	}

	@Override
	public int update(SupplyType supplyType) {
		return supplyTypeDAO.update(supplyType);
	}

	@Override
	public List<SupplyType> queryPagerByCondition(Pager pager, SupplyType supplyType, User user) {
		return supplyTypeDAO.queryPagerByCondition(pager, supplyType, user);
	}

	@Override
	public int batchUpdate(List<SupplyType> list) {
		return supplyTypeDAO.batchUpdate(list);
	}

	@Override
	public List<SupplyType> queryAll(User user) {
		return supplyTypeDAO.queryAll(user);
	}

	@Override
	public List<SupplyType> queryByStatus(String status, User user) {
		return supplyTypeDAO.queryByStatus(status, user);
	}

	@Override
	public SupplyType query(SupplyType supplyType, User user) {
		return supplyTypeDAO.query(supplyType, user);
	}

	@Override
	public SupplyType queryById(String id) {
		return supplyTypeDAO.queryById(id);
	}

	@Override
	public List<SupplyType> queryByPager(Pager pager, User user) {
		return supplyTypeDAO.queryByPager(pager, user);
	}

	@Override
	public int count(User user) {
		return supplyTypeDAO.count(user);
	}

	@Override
	public int inactive(String id) {
		return supplyTypeDAO.inactive(id);
	}

	@Override
	public int active(String id) {
		return supplyTypeDAO.active(id);
	}
}