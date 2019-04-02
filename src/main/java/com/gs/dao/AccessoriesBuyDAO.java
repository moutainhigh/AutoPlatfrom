package com.gs.dao;

import com.gs.bean.Accessories;
import com.gs.bean.AccessoriesBuy;
import com.gs.bean.User;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:35:15
 */
@Repository
public interface AccessoriesBuyDAO extends BaseDAO<String, AccessoriesBuy> {

    /**
     * 根据配件状态查询
     *
     * @param pager
     * @param user
     * @return
     */
     List<AccessoriesBuy> queryByCheckStatePager(@Param("pager") Pager pager, @Param("user") User user);

    /**
     * 根据审核状态计数
     *
     * @param user
     * @return
     */
     int countByCheckState(@Param("user") User user);


    /**
     * 根据配件状态查询
     *
     * @param pager
     * @param user
     * @return
     */
     List<AccessoriesBuy> queryByBuyStatePager(@Param("pager") Pager pager, @Param("user") User user);

    /**
     * 根据配件状态计数
     *
     * @param user
     * @return
     */
     int countByBuyState(@Param("user") User user);


    /**
     * 根据某个时间段内模糊查询某个配件
     *
     * @param pager
     * @param accName
     * @param buyTimeStart
     * @param buyTimeEnd
     * @param user
     * @return
     */
     List<AccessoriesBuy> queryByBuyTimeScopeByAccNamePager(@Param("pager") Pager pager, @Param("accName") String accName, @Param("buyTimeStart") String buyTimeStart, @Param("buyTimeEnd") String buyTimeEnd, @Param("user") User user);

    /**
     * 根据某个时间段内模糊查询某个配件
     *
     * @param accName
     * @param buyTimeStart
     * @param buyTimeEnd
     * @param user
     * @return
     */
     int countByBuyTimeScope(@Param("accName") String accName, @Param("buyTimeStart") String buyTimeStart, @Param("buyTimeEnd") String buyTimeEnd, @Param("user") User user);

    /**
     * 根据配件是否采购查询
     *
     * @param pager
     * @param user
     * @return
     */
     List<AccessoriesBuy> accIsBuy(@Param("pager") Pager pager, @Param("user") User user);

    /**
     * 根据配件是否采购计数
     *
     * @param user
     * @return
     */
     int countAccIsBuy(@Param("user") User user);

    /**
     * 更新配件是否采购
     *
     * @param id
     */
     void updateAccIsBuy(@Param("id") String id);


     void updateAccBuyCheck(@Param("checkStatus") String checkStatus, @Param("accBuyId") String accBuyId);

     void batchUpdateBuyCheck(@Param("ids") String[] ids);

     int dataPrimary(@Param("name") String name);

    /**
     * 默认查询本月的下单统计
     * @param companyId
     * @return
     */
     List<AccessoriesBuy> queryByDefaultCount(@Param("companyId") String companyId);


    /**
     * 根据年，月，季度，周，日查询所有下单统计
     * @param startTime
     * @param endTime
     * @param type
     * @param companyId
     * @return
     */
     List<AccessoriesBuy> queryByConditionCount(@Param("startTime") String startTime, @Param("endTime") String endTime,
                                                      @Param("type") String type, @Param("companyId") String companyId);

    /**
     * 默认查询本月的支付统计
     * @param companyId
     * @return
     */
     List<AccessoriesBuy> queryByDefaultPay(@Param("companyId") String companyId);


    /**
     * 根据年，月，季度，周，日查询所有支付统计
     * @param startTime
     * @param endTime
     * @param type
     * @param companyId
     * @return
     */
     List<AccessoriesBuy> queryByConditionPay(@Param("startTime") String startTime, @Param("endTime") String endTime,
                                                    @Param("type") String type, @Param("companyId") String companyId);
}