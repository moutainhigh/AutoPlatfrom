package com.gs.service;

import com.gs.bean.IncomingType;
import com.gs.bean.OutgoingType;
import com.gs.bean.User;
import com.gs.common.bean.Pager;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface OutgoingTypeService extends BaseService<String, OutgoingType>{

    public OutgoingType queryByName(String outTypeName);

    public List<OutgoingType> queryPagerStatus(String status,Pager pager,User user);
    public int countStatus(String status,User user);

    public List<OutgoingType> queryByPagerCondition(String companyId,
                                                    String inTypeName,
                                                    Pager pager);

    public int countCondition(String companyId,String inTypeName);
}