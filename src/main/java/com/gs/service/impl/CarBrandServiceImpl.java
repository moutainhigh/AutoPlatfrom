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
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class CarBrandServiceImpl implements CarBrandService {

    @Resource
    private CarBrandDAO carBrandDAO;

    @Override
    public int insert(CarBrand carBrand) {
        return carBrandDAO.insert(carBrand);
    }

    @Override
    public int batchInsert(List<CarBrand> list) {
        return carBrandDAO.batchInsert(list);
    }

    @Override
    public int delete(CarBrand carBrand) {
        return carBrandDAO.delete(carBrand);
    }

    @Override
    public int deleteById(String id) {
        return carBrandDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<CarBrand> list) {
        return carBrandDAO.batchDelete(list);
    }

    @Override
    public int update(CarBrand carBrand) {
        return carBrandDAO.update(carBrand);
    }

    @Override
    public int batchUpdate(List<CarBrand> list) {
        return carBrandDAO.batchUpdate(list);
    }

    @Override
    public List<CarBrand> queryAll(User user) {
        return carBrandDAO.queryAll(user);
    }

    @Override
    public List<CarBrand> queryByStatus(String status, User user) {
        return carBrandDAO.queryByStatus(status, user);
    }

    @Override
    public CarBrand query(CarBrand carBrand, User user) {
        return carBrandDAO.query(carBrand, user);
    }

    @Override
    public CarBrand queryById(String id) {
        return carBrandDAO.queryById(id);
    }

    @Override
    public List<CarBrand> queryByPager(Pager pager, User user) {
        return carBrandDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return carBrandDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return carBrandDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return carBrandDAO.active(id);
    }

    @Override
    public String queryNameById(String brandId) {
        return carBrandDAO.queryNameById(brandId);
    }

    @Override
    public List<CarBrand> queryByBrandPager(String status, Pager pager) {
        return carBrandDAO.queryByBrandPager(status, pager);
    }

    @Override
    public List<CarBrand> searchByPager(String brandName, Pager pager) {
        return carBrandDAO.searchByPager(brandName, pager);
    }

    @Override
    public int statusCount(String status) {
        return carBrandDAO.statusCount(status);
    }

    @Override
    public int searchCount(String brandName) {
        return carBrandDAO.searchCount(brandName);
    }
}