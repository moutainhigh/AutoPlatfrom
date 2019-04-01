package com.gs.dao;

import com.gs.bean.AccessoriesType;
import com.gs.bean.Module;
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
public interface AccessoriesTypeDAO extends BaseDAO<String, AccessoriesType>{

    public List<AccessoriesType> queryByStatusPager(@Param("accTypeStatus") String accTypeStatus, @Param("pager") Pager pager, @Param("user") User user);
    public int countByStatus(@Param("status") String status, @Param("user") User user);

    public int countByCondition(@Param("accessoriesType") AccessoriesType accessoriesType, @Param("user") User user);
    public List<AccessoriesType> queryByCondition(@Param("pager") Pager Pager, @Param("accessoriesType") AccessoriesType accessoriesType, @Param("user") User user);

    /*
    * 查询某个公司下的所有配件分类
    * */
    public List<AccessoriesType> queryByCompany(@Param("companyId")String companyId);
}