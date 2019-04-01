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
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:58:54
 */
@Service
public class SalaryServiceImpl implements SalaryService {

    @Resource
    private SalaryDAO salaryDAO;

    public int insert(Salary salary) {
        return salaryDAO.insert(salary);
    }

    public int batchInsert(List<Salary> list) {
        return salaryDAO.batchInsert(list);
    }

    public int delete(Salary salary) {
        return salaryDAO.delete(salary);
    }

    public int deleteById(String id) {
        return salaryDAO.deleteById(id);
    }

    public int batchDelete(List<Salary> list) {
        return salaryDAO.batchDelete(list);
    }

    public int update(Salary salary) {
        return salaryDAO.update(salary);
    }

    public int batchUpdate(List<Salary> list) {
        return salaryDAO.batchUpdate(list);
    }

    public List<Salary> queryAll(User user) {
        return salaryDAO.queryAll(user);
    }


    public List<Salary> queryByStatus(String status,User user) {
        return salaryDAO.queryByStatus(status,user);
    }

    public Salary query(Salary salary,User user) {
        return salaryDAO.query(salary,user);
    }

    public Salary queryById(String id) {
        return salaryDAO.queryById(id);
    }

    public List<Salary> queryByPager(Pager pager,User user) {
        return salaryDAO.queryByPager(pager,user);
    }

    public int count(User user) {
        return salaryDAO.count(user);
    }

    public int inactive(String id) {
        return salaryDAO.inactive(id);
    }

    public int active(String id) {
        return salaryDAO.active(id);
    }

    public List<Salary> queryByUserId(Pager pager, String userId) {
        return salaryDAO.queryByUserId(pager, userId);
    }

    @Override
    public int countByUserId(String userId) {
        return salaryDAO.countByUserId(userId);
    }


    @Transactional
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