package com.gs.service.impl;

import com.gs.bean.CarBrand;
import com.gs.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.CarBrandDAO;
import com.gs.service.CarBrandService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class CarBrandServiceImpl implements CarBrandService {

	@Resource
	private CarBrandDAO carBrandDAO;

	public int insert(CarBrand carBrand) { return carBrandDAO.insert(carBrand); }
	public int batchInsert(List<CarBrand> list) { return carBrandDAO.batchInsert(list); }
	public int delete(CarBrand carBrand) { return carBrandDAO.delete(carBrand); }
	public int deleteById(String id) {
        return carBrandDAO.deleteById(id);
    }
	public int batchDelete(List<CarBrand> list) { return carBrandDAO.batchDelete(list); }
	public int update(CarBrand carBrand) { return carBrandDAO.update(carBrand); }
	public int batchUpdate(List<CarBrand> list) { return carBrandDAO.batchUpdate(list); }
	public List<CarBrand> queryAll(User user) {
		return carBrandDAO.queryAll(user);
	}
	public List<CarBrand> queryByStatus(String status, User user) {
		return carBrandDAO.queryByStatus(status, user);
	}
	public CarBrand query(CarBrand carBrand, User user) {
		return carBrandDAO.query(carBrand, user);
	}
	public CarBrand queryById(String id) { return carBrandDAO.queryById(id); }
	public List<CarBrand> queryByPager(Pager pager, User user) {
		return carBrandDAO.queryByPager(pager, user);
	}
	public int count(User user) {
		return carBrandDAO.count(user);
	}
	public int inactive(String id) { return carBrandDAO.inactive(id); }
	public int active(String id) { return carBrandDAO.active(id); }
	public String queryNameById(String brandId) {
		return carBrandDAO.queryNameById(brandId);
	}
	public List<CarBrand> queryByBrandPager(String status, Pager pager) {
		return carBrandDAO.queryByBrandPager(status, pager);
	}
	public List<CarBrand> searchByPager(String brandName, Pager pager) {
		return carBrandDAO.searchByPager(brandName, pager);
	}
	public int statusCount(String status) {
		return carBrandDAO.statusCount(status);
	}
	public int searchCount(String brandName) {
		return carBrandDAO.searchCount(brandName);
	}
}