package com.gs.service.impl;

import com.gs.bean.Supply;
import com.gs.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.SupplyDAO;
import com.gs.service.SupplyService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class SupplyServiceImpl implements SupplyService {

	@Resource
	private SupplyDAO supplyDAO;

	@Override
	public int insert(Supply supply) {
		return supplyDAO.insert(supply);
	}

	@Override
	public int countByStatus(String status, User user) {
		return supplyDAO.countByStatus(status, user);
	}

	@Override
	public int batchInsert(List<Supply> list) {
		return supplyDAO.batchInsert(list);
	}

	@Override
	public int delete(Supply supply) {
		return supplyDAO.delete(supply);
	}

	@Override
	public List<Supply> queryPagerByStatus(Pager pager, String status, User user) {
		return supplyDAO.queryPagerByStatus(pager, status, user);
	}

	@Override
	public int deleteById(String id) {
		return supplyDAO.deleteById(id);
	}

	@Override
	public int countByCondition(Supply supply, User user) {
		return supplyDAO.countByCondition(supply, user);
	}

	@Override
	public int batchDelete(List<Supply> list) {
		return supplyDAO.batchDelete(list);
	}

	@Override
	public int update(Supply supply) {
		return supplyDAO.update(supply);
	}

	@Override
	public List<Supply> queryPagerByCondition(Pager pager, Supply supply, User user) {
		return supplyDAO.queryPagerByCondition(pager, supply, user);
	}

	@Override
	public int batchUpdate(List<Supply> list) {
		return supplyDAO.batchUpdate(list);
	}

	@Override
	public List<Supply> queryAll(User user) {
		return supplyDAO.queryAll(user);
	}

	@Override
	public List<Supply> queryByStatus(String status, User user) {
		return supplyDAO.queryByStatus(status, user);
	}

	@Override
	public Supply query(Supply supply, User user) {
		return supplyDAO.query(supply, user);
	}

	@Override
	public Supply queryById(String id) {
		return supplyDAO.queryById(id);
	}

	@Override
	public List<Supply> queryByPager(Pager pager, User user) {
		return supplyDAO.queryByPager(pager, user);
	}

	@Override
	public int count(User user) {
		return supplyDAO.count(user);
	}

	@Override
	public int inactive(String id) {
		return supplyDAO.inactive(id);
	}

	@Override
	public int active(String id) {
		return supplyDAO.active(id);
	}
}