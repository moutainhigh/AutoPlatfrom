package com.gs.service;

import com.gs.bean.IncomingType;
import com.gs.bean.User;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:36:51
*/
public interface IncomingTypeService extends BaseService<String, IncomingType>{

     List<IncomingType> queryPagerStatus(String status, Pager pager,User user);
     int countStatus(String status,User user);

     IncomingType queryByName(String inTypeName);

     List<IncomingType> queryByPagerCondition(String companyId,
                                                     String inTypeName,
                                                   Pager pager);

     int countCondition(String companyId,String inTypeName);
}