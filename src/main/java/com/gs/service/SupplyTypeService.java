package com.gs.service;

import com.gs.bean.SupplyType;
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
public interface SupplyTypeService extends BaseService<String, SupplyType>{

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
     List<SupplyType> queryPagerByStatus(Pager pager, String status, User user);

    /**
     * 根据查询条件计数
     * @param supplyType
     * @return
     */
     int countByCondition(SupplyType supplyType, User user);

    /**
     * 根据查询条件分页查询
     * @param pager
     * @param supplyType
     * @return
     */
     List<SupplyType> queryPagerByCondition(Pager pager, SupplyType supplyType, User user);

}