package com.gs.service;

import com.gs.bean.IncomingOutgoing;
import com.gs.bean.User;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:36:51
*/
public interface IncomingOutgoingService extends BaseService<String, IncomingOutgoing>{

     List<IncomingOutgoing> queryByInOutType(Pager pager, IncomingOutgoing incomingOutgoing,User user);

     int countByInOutType(IncomingOutgoing incomingOutgoing,User user);

     List<IncomingOutgoing> queryByDefault(int inOutType,String companyId);

     List<IncomingOutgoing> queryByCondition(String startTime,String endTime,
                                                   int inOutType,String type,String companyId);

     void addInsert(List<IncomingOutgoing> incomingOutgoings);

     List<IncomingOutgoing> queryPagerStatus(String status,Pager pager,User user);
     int countStatus(String status,User user);
}