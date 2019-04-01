package com.gs.service;

import com.gs.bean.IncomingType;
import com.gs.bean.User;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:51
*/
public interface IncomingTypeService extends BaseService<String, IncomingType>{

    public List<IncomingType> queryPagerStatus(String status, Pager pager,User user);
    public int countStatus(String status,User user);

    public IncomingType queryByName(String inTypeName);

    public List<IncomingType> queryByPagerCondition(String companyId,
                                                     String inTypeName,
                                                   Pager pager);

    public int countCondition(String companyId,String inTypeName);
}