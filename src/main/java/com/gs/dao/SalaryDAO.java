package com.gs.dao;

import com.gs.bean.Salary;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:35:15
*/
@Repository
public interface SalaryDAO extends BaseDAO<String, Salary>{

    // 查询自己所有的工资，分页显示
    public List<Salary> queryByUserId(@Param("pager")Pager pager,@Param("userId") String userId);
    public int countByUserId(String userId);

    // 批量添加，导入时要用
    public void addInsert(List<Salary> salarys);

    /*
    * 条件查询
    * */
    public List<Salary> queryByPagerSearch(@Param("pager")Pager pager, @Param("salary")Salary salary,@Param("user")User user);
    public int countSearch(@Param("salary")Salary salary,@Param("user")User user);

}