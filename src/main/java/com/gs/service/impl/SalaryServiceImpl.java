package com.gs.service.impl;

import com.gs.bean.Salary;
import com.gs.bean.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.SalaryDAO;
import com.gs.service.SalaryService;
import com.gs.common.bean.Pager;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class SalaryServiceImpl implements SalaryService {

    @Resource
    private SalaryDAO salaryDAO;

    @Override
    public int insert(Salary salary) {
        return salaryDAO.insert(salary);
    }

    @Override
    public int batchInsert(List<Salary> list) {
        return salaryDAO.batchInsert(list);
    }

    @Override
    public int delete(Salary salary) {
        return salaryDAO.delete(salary);
    }

    @Override
    public int deleteById(String id) {
        return salaryDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<Salary> list) {
        return salaryDAO.batchDelete(list);
    }

    @Override
    public int update(Salary salary) {
        return salaryDAO.update(salary);
    }

    @Override
    public int batchUpdate(List<Salary> list) {
        return salaryDAO.batchUpdate(list);
    }

    @Override
    public List<Salary> queryAll(User user) {
        return salaryDAO.queryAll(user);
    }


    @Override
    public List<Salary> queryByStatus(String status, User user) {
        return salaryDAO.queryByStatus(status,user);
    }

    @Override
    public Salary query(Salary salary, User user) {
        return salaryDAO.query(salary,user);
    }

    @Override
    public Salary queryById(String id) {
        return salaryDAO.queryById(id);
    }

    @Override
    public List<Salary> queryByPager(Pager pager, User user) {
        return salaryDAO.queryByPager(pager,user);
    }

    @Override
    public int count(User user) {
        return salaryDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return salaryDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return salaryDAO.active(id);
    }

    @Override
    public List<Salary> queryByUserId(Pager pager, String userId) {
        return salaryDAO.queryByUserId(pager, userId);
    }

    @Override
    public int countByUserId(String userId) {
        return salaryDAO.countByUserId(userId);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBatchInsert(List<Salary> salaries) {
        boolean flag = false;
        if (salaries != null) {
            salaryDAO.addInsert(salaries);
            flag = true;
        }
        return flag;
    }

    @Override
    public List<Salary> queryByPagerSearch(Pager pager, Salary salary,User user) {
        return salaryDAO.queryByPagerSearch(pager, salary,user);
    }

    @Override
    public int countSearch(Salary salary,User user) {
        return salaryDAO.countSearch(salary,user);
    }
}