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
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class CarPlateServiceImpl implements CarPlateService {

    @Resource
    private CarPlateDAO carPlateDAO;

    @Override
    public int insert(CarPlate carPlate) {
        return carPlateDAO.insert(carPlate);
    }

    @Override
    public int batchInsert(List<CarPlate> list) {
        return carPlateDAO.batchInsert(list);
    }

    @Override
    public int delete(CarPlate carPlate) {
        return carPlateDAO.delete(carPlate);
    }

    @Override
    public int deleteById(String id) {
        return carPlateDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<CarPlate> list) {
        return carPlateDAO.batchDelete(list);
    }

    @Override
    public int update(CarPlate carPlate) {
        return carPlateDAO.update(carPlate);
    }

    @Override
    public int batchUpdate(List<CarPlate> list) {
        return carPlateDAO.batchUpdate(list);
    }

    @Override
    public List<CarPlate> queryAll(User user) {
        return carPlateDAO.queryAll(user);
    }

    @Override
    public List<CarPlate> queryByStatus(String status, User user) {
        return carPlateDAO.queryByStatus(status, user);
    }

    @Override
    public CarPlate query(CarPlate carPlate, User user) {
        return carPlateDAO.query(carPlate, user);
    }

    @Override
    public CarPlate queryById(String id) {
        return carPlateDAO.queryById(id);
    }

    @Override
    public List<CarPlate> queryByPager(Pager pager, User user) {
        return carPlateDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return carPlateDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return carPlateDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return carPlateDAO.active(id);
    }

    @Override
    public List<CarPlate> byStatusPager(String status, Pager pager) {
        return carPlateDAO.byStatusPager(status, pager);
    }

    @Override
    public int countStatus(String status) {
        return carPlateDAO.countStatus(status);
    }

    @Override
    public List<CarPlate> searchByPager(String plateName, Pager pager) {
        return carPlateDAO.searchByPager(plateName, pager);
    }

    @Override
    public int searchCount(String plateName) {
        return carPlateDAO.searchCount(plateName);
    }
}