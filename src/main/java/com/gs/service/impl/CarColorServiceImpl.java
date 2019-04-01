package com.gs.service.impl;

import com.gs.bean.CarColor;
import com.gs.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.CarColorDAO;
import com.gs.service.CarColorService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class CarColorServiceImpl implements CarColorService {

	@Resource
	private CarColorDAO carColorDAO;

	public int insert(CarColor carColor) { return carColorDAO.insert(carColor); }
	public int batchInsert(List<CarColor> list) { return carColorDAO.batchInsert(list); }
	public int delete(CarColor carColor) { return carColorDAO.delete(carColor); }
	public int deleteById(String id) {
        return carColorDAO.deleteById(id);
    }
	public int batchDelete(List<CarColor> list) { return carColorDAO.batchDelete(list); }
	public int update(CarColor carColor) { return carColorDAO.update(carColor); }
	public int batchUpdate(List<CarColor> list) { return carColorDAO.batchUpdate(list); }
	public List<CarColor> queryAll(User user) {
		return carColorDAO.queryAll(user);
	}
	public List<CarColor> queryByStatus(String status, User user) {
		return carColorDAO.queryByStatus(status, user);
	}
	public CarColor query(CarColor carColor, User user) {
		return carColorDAO.query(carColor, user);
	}
	public CarColor queryById(String id) { return carColorDAO.queryById(id); }
	public List<CarColor> queryByPager(Pager pager, User user) {
		return carColorDAO.queryByPager(pager, user);
	}
	public int count(User user) {
		return carColorDAO.count(user);
	}
	public int inactive(String id) { return carColorDAO.inactive(id); }
	public int active(String id) { return carColorDAO.active(id); }
	public List<CarColor> queryByColorPager(String status,Pager pager) {
		return carColorDAO.queryByColorPager(status, pager);
	}
	public int statusCount(String status) {
		return carColorDAO.statusCount(status);
	}
	public List<CarColor> searchByPager(String colorName,Pager pager) {
		return carColorDAO.searchByPager(colorName, pager);
	}
	public int searchCount(String colorName) {
		return carColorDAO.searchCount(colorName);
	}
}