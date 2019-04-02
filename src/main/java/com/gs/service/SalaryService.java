package com.gs.service;

import com.gs.bean.Salary;
import com.gs.bean.User;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:36:52
*/
public interface SalaryService extends BaseService<String, Salary>{

     List<Salary> queryByUserId(Pager pager, String userId);
     int countByUserId(String userId);

     boolean saveBatchInsert(List<Salary> salarys);

     List<Salary> queryByPagerSearch(Pager pager,Salary salary,User user);
     int countSearch(Salary salary,User user);
}