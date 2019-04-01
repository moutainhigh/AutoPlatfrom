package com.gs.service.impl;

import com.gs.bean.CarPlate;
import com.gs.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.CarPlateDAO;
import com.gs.service.CarPlateService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class CarPlateServiceImpl implements CarPlateService {

	@Resource
	private CarPlateDAO carPlateDAO;

	public int insert(CarPlate carPlate) { return carPlateDAO.insert(carPlate); }
	public int batchInsert(List<CarPlate> list) { return carPlateDAO.batchInsert(list); }
	public int delete(CarPlate carPlate) { return carPlateDAO.delete(carPlate); }
	public int deleteById(String id) {
        return carPlateDAO.deleteById(id);
    }
	public int batchDelete(List<CarPlate> list) { return carPlateDAO.batchDelete(list); }
	public int update(CarPlate carPlate) { return carPlateDAO.update(carPlate); }
	public int batchUpdate(List<CarPlate> list) { return carPlateDAO.batchUpdate(list); }
	public List<CarPlate> queryAll(User user) {
		return carPlateDAO.queryAll(user);
	}
	public List<CarPlate> queryByStatus(String status, User user) {
		return carPlateDAO.queryByStatus(status, user);
	}
	public CarPlate query(CarPlate carPlate, User user) {
		return carPlateDAO.query(carPlate, user);
	}
	public CarPlate queryById(String id) { return carPlateDAO.queryById(id); }
	public List<CarPlate> queryByPager(Pager pager, User user) {
		return carPlateDAO.queryByPager(pager, user);
	}
	public int count(User user) {
		return carPlateDAO.count(user);
	}
	public int inactive(String id) { return carPlateDAO.inactive(id); }
	public int active(String id) { return carPlateDAO.active(id); }
	public List<CarPlate> byStatusPager(String status, Pager pager) {
		return carPlateDAO.byStatusPager(status, pager);
	}
	public int countStatus(String status) {
		return carPlateDAO.countStatus(status);
	}
	public List<CarPlate> searchByPager(String plateName,Pager pager) {
		return carPlateDAO.searchByPager(plateName, pager);
	}
	public int searchCount(String plateName) {
		return carPlateDAO.searchCount(plateName);
	}
}