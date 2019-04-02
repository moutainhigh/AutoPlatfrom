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
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class CarColorServiceImpl implements CarColorService {

    @Resource
    private CarColorDAO carColorDAO;

    @Override
    public int insert(CarColor carColor) {
        return carColorDAO.insert(carColor);
    }

    @Override
    public int batchInsert(List<CarColor> list) {
        return carColorDAO.batchInsert(list);
    }

    @Override
    public int delete(CarColor carColor) {
        return carColorDAO.delete(carColor);
    }

    @Override
    public int deleteById(String id) {
        return carColorDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<CarColor> list) {
        return carColorDAO.batchDelete(list);
    }

    @Override
    public int update(CarColor carColor) {
        return carColorDAO.update(carColor);
    }

    @Override
    public int batchUpdate(List<CarColor> list) {
        return carColorDAO.batchUpdate(list);
    }

    @Override
    public List<CarColor> queryAll(User user) {
        return carColorDAO.queryAll(user);
    }

    @Override
    public List<CarColor> queryByStatus(String status, User user) {
        return carColorDAO.queryByStatus(status, user);
    }

    @Override
    public CarColor query(CarColor carColor, User user) {
        return carColorDAO.query(carColor, user);
    }

    @Override
    public CarColor queryById(String id) {
        return carColorDAO.queryById(id);
    }

    @Override
    public List<CarColor> queryByPager(Pager pager, User user) {
        return carColorDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return carColorDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return carColorDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return carColorDAO.active(id);
    }

    @Override
    public List<CarColor> queryByColorPager(String status, Pager pager) {
        return carColorDAO.queryByColorPager(status, pager);
    }

    @Override
    public int statusCount(String status) {
        return carColorDAO.statusCount(status);
    }

    @Override
    public List<CarColor> searchByPager(String colorName, Pager pager) {
        return carColorDAO.searchByPager(colorName, pager);
    }

    @Override
    public int searchCount(String colorName) {
        return carColorDAO.searchCount(colorName);
    }
}