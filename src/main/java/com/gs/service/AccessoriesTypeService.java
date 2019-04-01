package com.gs.service;

import com.gs.bean.AccessoriesType;
import com.gs.bean.Module;
import com.gs.bean.User;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:51
*/
public interface AccessoriesTypeService extends BaseService<String, AccessoriesType>{

    public List<AccessoriesType> queryByStatusPager(String accTypeStatus, Pager pager, User user);
    public int countByStatus(String status, User user);

    public int countByCondition(AccessoriesType accessoriesType, User user);
    public List<AccessoriesType> queryByCondition(Pager pager, AccessoriesType accessoriesType, User user);

    /*
   * 查询某个公司下的所有配件分类
   * */
    public List<AccessoriesType> queryByCompany(String companyId);

}