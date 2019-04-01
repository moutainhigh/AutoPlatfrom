package com.gs.dao;

import com.gs.bean.SupplyType;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:35:15
*/
@Repository
public interface SupplyTypeDAO extends BaseDAO<String, SupplyType>{

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
    public List<SupplyType> queryPagerByStatus(@Param("pager") Pager pager, @Param("status") String status, @Param("user") User user);

    /**
     * 根据查询条件计数
     * @param supplyType
     * @return
     */
    public int countByCondition(@Param("supplyType") SupplyType supplyType, @Param("user") User user);

    /**
     * 根据查询条件分页查询
     * @param pager
     * @param supplyType
     * @return
     */
    public List<SupplyType> queryPagerByCondition(@Param("pager") Pager pager, @Param("supplyType") SupplyType supplyType, @Param("user") User user);

}