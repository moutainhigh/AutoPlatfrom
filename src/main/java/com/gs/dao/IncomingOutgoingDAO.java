package com.gs.dao;

import com.gs.bean.IncomingOutgoing;
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
public interface IncomingOutgoingDAO extends BaseDAO<String, IncomingOutgoing>{

    /*
    *  查询支出和收入
    * */
    public List<IncomingOutgoing> queryByInOutType(@Param("pager")Pager pager,@Param("incomingOutgoing")IncomingOutgoing incomingOutgoing,@Param("user")User user);

    public int countByInOutType(@Param("incomingOutgoing") IncomingOutgoing incomingOutgoing,@Param("user")User user);

    /*
    * 默认查询本月的财务统计
    * */
    public List<IncomingOutgoing> queryByDefault(@Param("inOutType") int inOutType,@Param("companyId")String companyId);

    /*
    * 根据年，月，季度，周，日查询所有收支记录
    * */
    public List<IncomingOutgoing> queryByCondition(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("inOutType")int inOutType,
                                                   @Param("type")String type, @Param("companyId")String companyId);

    /*
   * 批量添加
   * */
    public void addInsert(List<IncomingOutgoing> incomingOutgoings);

    public List<IncomingOutgoing> queryPagerStatus(@Param("status")String status,@Param("pager")Pager pager,@Param("user")User user);
    public int countStatus(@Param("status") String status,@Param("user")User user);
}