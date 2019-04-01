package com.gs.dao;

import com.gs.bean.ChargeBill;
import com.gs.bean.Checkin;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:35:15
*/
@Repository
public interface ChargeBillDAO extends BaseDAO<String, ChargeBill>{
    /**
     * 根据状态计数
     * @param status
     * @return
     */
    public int countByStatus(@Param("status") String status, @Param("user") User user);

    /**
     * 根据状态分页查询
     * @param pager
     * @param status
     * @return
     */
    public List<ChargeBill> queryPagerByStatus(@Param("pager") Pager pager, @Param("status") String status, @Param("user") User user);

    /**
     * 根据查询条件计数
     * @param chargeBill
     * @return
     */
    public int countByCondition(@Param("chargeBill") ChargeBill chargeBill, @Param("user") User user);

    /**
     * 根据查询条件分页查询
     * @param pager
     * @param chargeBill
     * @return
     */
    public List<ChargeBill> queryPagerByCondition(@Param("pager") Pager pager, @Param("chargeBill") ChargeBill chargeBill, @Param("user") User user);

    /*
    * 默认查询本月车主用户消费统计
    * */
    public List<ChargeBill> queryByDefault(@Param("maintainOrFix") String maintainOrFix, @Param("userId")String userId);

    /*
    * 根据年，月，季度，周，日查询所有工单
    * */
    public List<ChargeBill> queryByCondition(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("maintainOrFix")String maintainOrFix,
                                           @Param("type")String type, @Param("userId")String userId);

    public List<ChargeBill> queryMyName(@Param("user") User user);
}
