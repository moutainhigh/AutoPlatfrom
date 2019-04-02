package com.gs.service.impl;

import com.gs.bean.Company;
import com.gs.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.CompanyDAO;
import com.gs.service.CompanyService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyDAO companyDAO;

    @Override
    public int insert(Company company) {
        return companyDAO.insert(company);
    }

    @Override
    public int batchInsert(List<Company> list) {
        return companyDAO.batchInsert(list);
    }

    @Override
    public int delete(Company company) {
        return companyDAO.delete(company);
    }

    @Override
    public int deleteById(String id) {
        return companyDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<Company> list) {
        return companyDAO.batchDelete(list);
    }

    @Override
    public int update(Company company) {
        return companyDAO.update(company);
    }

    @Override
    public int batchUpdate(List<Company> list) {
        return companyDAO.batchUpdate(list);
    }

    @Override
    public List<Company> queryAll(User user) {
        return companyDAO.queryAll(user);
    }

    @Override
    public List<Company> queryByStatus(String status, User user) {
        return companyDAO.queryByStatus(status, user);
    }

    @Override
    public Company query(Company company, User user) {
        return companyDAO.query(company, user);
    }

    @Override
    public Company queryById(String id) {
        return companyDAO.queryById(id);
    }

    @Override
    public List<Company> queryByPager(Pager pager, User user) {
        return companyDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return companyDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return companyDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return companyDAO.active(id);
    }

    @Override
    public List<Company> queryByStatusPager(@Param("status") String status, @Param("pager") Pager pager, @Param("user") User user) {
        return companyDAO.queryByStatusPager(status, pager, user);
    }

    @Override
    public int statusCount(@Param("status") String status, @Param("user") User user) {
        return companyDAO.statusCount(status, user);
    }

    @Override
    public List<Company> searchByPager(String companyName, String userName, Pager pager) {
        return companyDAO.searchByPager(companyName, userName, pager);
    }

    @Override
    public int searchCount(String companyName, String userName) {
        return companyDAO.searchCount(companyName, userName);
    }

    @Override
    public List<Company> queryByTop(int top) {
        return companyDAO.queryByTop(top);
    }

    @Override
    public List<Company> queryByTop2(int index, int top) {
        return companyDAO.queryByTop2(index, top);
    }

}