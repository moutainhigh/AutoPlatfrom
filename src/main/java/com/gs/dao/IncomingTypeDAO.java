package com.gs.dao;

import com.gs.bean.IncomingType;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:35:15
*/
@Repository
public interface IncomingTypeDAO extends BaseDAO<String, IncomingType>{

     List<IncomingType> queryPagerStatus(@Param("status")String status,@Param("pager")Pager pager,@Param("user")User user);
     int countStatus(@Param("status") String status,@Param("user")User user);

     IncomingType queryByName(@Param("inTypeName") String inTypeName);

     List<IncomingType> queryByPagerCondition(@Param("companyId")String companyId,
                                               @Param("inTypeName") String inTypeName,
                                               @Param("pager")Pager pager);

     int countCondition(@Param("companyId")String companyId,@Param("inTypeName")String inTypeName);

}