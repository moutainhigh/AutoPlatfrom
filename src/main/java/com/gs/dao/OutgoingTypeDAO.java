package com.gs.dao;

import com.gs.bean.IncomingType;
import com.gs.bean.OutgoingType;
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
public interface OutgoingTypeDAO extends BaseDAO<String, OutgoingType>{

     OutgoingType queryByName(@Param("outTypeName") String outTypeName);

     List<OutgoingType> queryPagerStatus(@Param("status")String status, @Param("pager")Pager pager,@Param("user")User user);
     int countStatus(@Param("status") String status,@Param("user")User user);

     List<OutgoingType> queryByPagerCondition(@Param("companyId")String companyId,
                                                    @Param("inTypeName") String inTypeName,
                                                    @Param("pager")Pager pager);

     int countCondition(@Param("companyId")String companyId,@Param("inTypeName")String inTypeName);
}