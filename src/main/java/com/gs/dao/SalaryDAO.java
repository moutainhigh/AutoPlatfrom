package com.gs.dao;

import com.gs.bean.Salary;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:35:15
*/
@Repository
public interface SalaryDAO extends BaseDAO<String, Salary>{

    // 查询自己所有的工资，分页显示
     List<Salary> queryByUserId(@Param("pager")Pager pager,@Param("userId") String userId);
     int countByUserId(String userId);

    // 批量添加，导入时要用
     void addInsert(List<Salary> salarys);

    /*
    * 条件查询
    * */
     List<Salary> queryByPagerSearch(@Param("pager")Pager pager, @Param("salary")Salary salary,@Param("user")User user);
     int countSearch(@Param("salary")Salary salary,@Param("user")User user);

}