package com.gs.service;

import com.gs.bean.SupplyType;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface SupplyTypeService extends BaseService<String, SupplyType>{

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
    public List<SupplyType> queryPagerByStatus(Pager pager, String status, User user);

    /**
     * 根据查询条件计数
     * @param supplyType
     * @return
     */
    public int countByCondition(SupplyType supplyType, User user);

    /**
     * 根据查询条件分页查询
     * @param pager
     * @param supplyType
     * @return
     */
    public List<SupplyType> queryPagerByCondition(Pager pager, SupplyType supplyType, User user);

}