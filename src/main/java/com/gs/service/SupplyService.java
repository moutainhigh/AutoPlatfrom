package com.gs.service;

import com.gs.bean.Supply;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:36:52
*/
public interface SupplyService extends BaseService<String, Supply>{

    /**
     * 根据状态计数
     * @param status
     * @return
     */
     int countByStatus(String status, User user);

    /**
     * 根据状态分页查询
     * @param pager
     * @param status
     * @return
     */
     List<Supply> queryPagerByStatus(Pager pager, String status, User user);

    /**
     * 根据查询条件计数
     * @param supply
     * @return
     */
     int countByCondition(Supply supply, User user);

    /**
     * 根据查询条件分页查询
     * @param pager
     * @param supply
     * @return
     */
     List<Supply> queryPagerByCondition(Pager pager, Supply supply, User user);

}