package com.gs.service;

import com.gs.bean.ChargeBill;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:51
*/
public interface ChargeBillService extends BaseService<String, ChargeBill>{
    /**
     * 根据状态计数
     * @param status
     * @return
     */
    public int countByStatus(String status, User user);

    /**
     * 根据状态分页查询
     * @param pager
     * @param status
     * @return
     */
    public List<ChargeBill> queryPagerByStatus(Pager pager, String status, User user);

    /**
     * 根据查询条件计数
     * @param chargeBill
     * @return
     */
    public int countByCondition(ChargeBill chargeBill, User user);

    /**
     * 根据查询条件分页查询
     * @param pager
     * @param chargeBill
     * @return
     */
    public List<ChargeBill> queryPagerByCondition(Pager pager, ChargeBill chargeBill, User user);

    /*
   * 默认查询本月车主用户消费统计
   * */
    public List<ChargeBill> queryByDefault(String maintainOrFix, String userId);

    /*
    * 根据年，月，季度，周，日查询所有工单
    * */
    public List<ChargeBill> queryByCondition(String startTime,String endTime,String maintainOrFix,
                                             String type, String userId);

    public List<ChargeBill> queryMyName(User user);
}