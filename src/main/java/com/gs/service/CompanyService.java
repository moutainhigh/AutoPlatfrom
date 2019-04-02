package com.gs.service;

import com.gs.bean.Company;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:36:51
*/
public interface CompanyService extends BaseService<String, Company>{
     List<Company> queryByStatusPager(@Param("status")String status, @Param("pager")Pager pager,@Param("user")User user);
     int statusCount(@Param("status")String status,@Param("user")User user);
     List<Company> searchByPager(@Param("companyName")String companyName, @Param("userName")String userName,@Param("pager")Pager pager);
     int searchCount(@Param("companyName") String companyName,@Param("userName") String userName);
     List<Company> queryByTop(int top);

    /**分页查找车主后台首页的公司*/
     List<Company> queryByTop2(int index, int top);

}