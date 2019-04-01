package com.gs.service;

import com.gs.bean.Salary;
import com.gs.bean.User;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface SalaryService extends BaseService<String, Salary>{

    public List<Salary> queryByUserId(Pager pager, String userId);
    public int countByUserId(String userId);

    public boolean saveBatchInsert(List<Salary> salarys);

    public List<Salary> queryByPagerSearch(Pager pager,Salary salary,User user);
    public int countSearch(Salary salary,User user);
}