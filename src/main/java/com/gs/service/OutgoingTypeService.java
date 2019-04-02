package com.gs.service;

import com.gs.bean.IncomingType;
import com.gs.bean.OutgoingType;
import com.gs.bean.User;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:36:52
*/
public interface OutgoingTypeService extends BaseService<String, OutgoingType>{

     OutgoingType queryByName(String outTypeName);

     List<OutgoingType> queryPagerStatus(String status,Pager pager,User user);
     int countStatus(String status,User user);

     List<OutgoingType> queryByPagerCondition(String companyId,String inTypeName,Pager pager);

     int countCondition(String companyId,String inTypeName);
}