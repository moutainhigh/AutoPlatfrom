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
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class CarModelServiceImpl implements CarModelService {

    @Resource
    private CarModelDAO carModelDAO;

    @Override
    public int insert(CarModel carModel) {
        return carModelDAO.insert(carModel);
    }

    @Override
    public int batchInsert(List<CarModel> list) {
        return carModelDAO.batchInsert(list);
    }

    @Override
    public int delete(CarModel carModel) {
        return carModelDAO.delete(carModel);
    }

    @Override
    public int deleteById(String id) {
        return carModelDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<CarModel> list) {
        return carModelDAO.batchDelete(list);
    }

    @Override
    public int update(CarModel carModel) {
        return carModelDAO.update(carModel);
    }

    @Override
    public int batchUpdate(List<CarModel> list) {
        return carModelDAO.batchUpdate(list);
    }

    @Override
    public List<CarModel> queryAll(User user) {
        return carModelDAO.queryAll(user);
    }

    @Override
    public List<CarModel> queryByStatus(String status, User user) {
        return carModelDAO.queryByStatus(status, user);
    }

    @Override
    public CarModel query(CarModel carModel, User user) {
        return carModelDAO.query(carModel, user);
    }

    @Override
    public CarModel queryById(String id) {
        return carModelDAO.queryById(id);
    }

    @Override
    public List<CarModel> queryByPager(Pager pager, User user) {
        return carModelDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return carModelDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return carModelDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return carModelDAO.active(id);
    }

    @Override
    public List<CarModel> queryByBrandId(String brandId) {
        return carModelDAO.queryByBrandId(brandId);
    }

    @Override
    public List<CarModel> queryByModelStatusPager(String status, Pager pager) {
        return carModelDAO.queryByModelStatusPager(status, pager);
    }

    @Override
    public int statusCount(String status) {
        return carModelDAO.statusCount(status);
    }

    @Override
    public List<CarModel> searchByPager(String brandId, Pager pager) {
        return carModelDAO.searchByPager(brandId, pager);
    }

    @Override
    public int searchCount(String brandId) {
        return carModelDAO.searchCount(brandId);
    }

}