package com.gs.service.impl;

import com.gs.bean.CarModel;
import com.gs.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.CarModelDAO;
import com.gs.service.CarModelService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class CarModelServiceImpl implements CarModelService {

	@Resource
	private CarModelDAO carModelDAO;

	public int insert(CarModel carModel) { return carModelDAO.insert(carModel); }
	public int batchInsert(List<CarModel> list) { return carModelDAO.batchInsert(list); }
	public int delete(CarModel carModel) { return carModelDAO.delete(carModel); }
	public int deleteById(String id) {
        return carModelDAO.deleteById(id);
    }
	public int batchDelete(List<CarModel> list) { return carModelDAO.batchDelete(list); }
	public int update(CarModel carModel) { return carModelDAO.update(carModel); }
	public int batchUpdate(List<CarModel> list) { return carModelDAO.batchUpdate(list); }
	public List<CarModel> queryAll(User user) {
		return carModelDAO.queryAll(user);
	}
	public List<CarModel> queryByStatus(String status, User user) {
		return carModelDAO.queryByStatus(status, user);
	}
	public CarModel query(CarModel carModel, User user) {
		return carModelDAO.query(carModel, user);
	}
	public CarModel queryById(String id) {
		return carModelDAO.queryById(id);
	}
	public List<CarModel> queryByPager(Pager pager, User user) {
		return carModelDAO.queryByPager(pager, user);
	}
	public int count(User user) {
		return carModelDAO.count(user);
	}
	public int inactive(String id) { return carModelDAO.inactive(id); }
	public int active(String id) { return carModelDAO.active(id); }
	public List<CarModel> queryByBrandId(String brandId) {
		return carModelDAO.queryByBrandId(brandId);
	}
	public List<CarModel> queryByModelStatusPager(String status,Pager pager) {
		return carModelDAO.queryByModelStatusPager(status, pager);
	}
	public int statusCount(String status) {
		return carModelDAO.statusCount(status);
	}
	public List<CarModel> searchByPager(String brandId,Pager pager) {
		return carModelDAO.searchByPager(brandId, pager);
	}
	public int searchCount(String brandId) {
		return carModelDAO.searchCount(brandId);
	}

}