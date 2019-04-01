package com.gs.dao;

import com.gs.bean.IncomingType;
import com.gs.bean.OutgoingType;
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
public interface OutgoingTypeDAO extends BaseDAO<String, OutgoingType>{

    public OutgoingType queryByName(@Param("outTypeName") String outTypeName);

    public List<OutgoingType> queryPagerStatus(@Param("status")String status, @Param("pager")Pager pager,@Param("user")User user);
    public int countStatus(@Param("status") String status,@Param("user")User user);

    public List<OutgoingType> queryByPagerCondition(@Param("companyId")String companyId,
                                                    @Param("inTypeName") String inTypeName,
                                                    @Param("pager")Pager pager);

    public int countCondition(@Param("companyId")String companyId,@Param("inTypeName")String inTypeName);
}