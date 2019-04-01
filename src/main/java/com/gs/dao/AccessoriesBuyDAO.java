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
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
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
    public List<AccessoriesBuy> queryByCheckStatePager(@Param("pager") Pager pager, @Param("user") User user);

    /**
     * 根据审核状态计数
     *
     * @param user
     * @return
     */
    public int countByCheckState(@Param("user") User user);


    /**
     * 根据配件状态查询
     *
     * @param pager
     * @param user
     * @return
     */
    public List<AccessoriesBuy> queryByBuyStatePager(@Param("pager") Pager pager, @Param("user") User user);

    /**
     * 根据配件状态计数
     *
     * @param user
     * @return
     */
    public int countByBuyState(@Param("user") User user);


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
    public List<AccessoriesBuy> queryByBuyTimeScopeByAccNamePager(@Param("pager") Pager pager, @Param("accName") String accName, @Param("buyTimeStart") String buyTimeStart, @Param("buyTimeEnd") String buyTimeEnd, @Param("user") User user);

    /**
     * 根据某个时间段内模糊查询某个配件
     *
     * @param accName
     * @param buyTimeStart
     * @param buyTimeEnd
     * @param user
     * @return
     */
    public int countByBuyTimeScope(@Param("accName") String accName, @Param("buyTimeStart") String buyTimeStart, @Param("buyTimeEnd") String buyTimeEnd, @Param("user") User user);

    /**
     * 根据配件是否采购查询
     *
     * @param pager
     * @param user
     * @return
     */
    public List<AccessoriesBuy> accIsBuy(@Param("pager") Pager pager, @Param("user") User user);

    /**
     * 根据配件是否采购计数
     *
     * @param user
     * @return
     */
    public int countAccIsBuy(@Param("user") User user);

    /**
     * 更新配件是否采购
     *
     * @param id
     */
    public void updateAccIsBuy(@Param("id") String id);


    public void updateAccBuyCheck(@Param("checkStatus") String checkStatus, @Param("accBuyId") String accBuyId);

    public void batchUpdateBuyCheck(@Param("ids") String[] ids);

    public int dataPrimary(@Param("name") String name);

    /*
    * 默认查询本月的下单统计
    * */
    public List<AccessoriesBuy> queryByDefaultCount(@Param("companyId") String companyId);

    /*
    * 根据年，月，季度，周，日查询所有下单统计
    * */
    public List<AccessoriesBuy> queryByConditionCount(@Param("startTime") String startTime, @Param("endTime") String endTime,
                                                      @Param("type") String type, @Param("companyId") String companyId);

    /*
   * 默认查询本月的支付统计
   * */
    public List<AccessoriesBuy> queryByDefaultPay(@Param("companyId") String companyId);

    /*
    * 根据年，月，季度，周，日查询所有支付统计
    * */
    public List<AccessoriesBuy> queryByConditionPay(@Param("startTime") String startTime, @Param("endTime") String endTime,
                                                    @Param("type") String type, @Param("companyId") String companyId);
}