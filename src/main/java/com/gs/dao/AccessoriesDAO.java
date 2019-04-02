package com.gs.dao;

import com.gs.bean.Accessories;
import com.gs.bean.AccessoriesType;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:35:15
*/
@Repository
public interface AccessoriesDAO extends BaseDAO<String, Accessories>{

     List<Accessories> queryByStatusPager(@Param("accStatus") String accStatus, @Param("pager") Pager pager, @Param("user") User user);
     int countByStatus(@Param("status") String status, @Param("user") User user);

     List<Accessories> queryByIdPager(@Param("id") String id, @Param("pager") Pager pager, @Param("user") User user);
     int countByTypeId(@Param("accTypeId") String accTypeId, @Param("user") User user);
     int countByCondition(@Param("accessories") Accessories accessories, @Param("user") User user);
     List<Accessories> queryByCondition(@Param("pager") Pager pager, @Param("accessories") Accessories accessories, @Param("user") User user);

     void updateIdle(@Param("id") String id,  @Param("lastCount") int  lastCount, @Param("user") User user);

     void updateAccUseTime(@Param("id") String id);

    /**领料申请同意后批量更新最近领料时间及库存*/
     void updateTotalAndIdle(List<Accessories> accessories);

    /**查询出对应配件编号对应的库存及可用数量*/
     Accessories queryByIdTotalAndIdle(String accId);

    /*
    * 根据年，月，季度，周，日查询所有配件总数量
    * */
     List<Accessories> queryByConditionTotal(@Param("startTime")String startTime,@Param("endTime")String endTime,
                                           @Param("type")String type, @Param("companyId")String companyId,@Param("accTypeId")String accTypeId);

    /*
 * 根据年，月，季度，周，日查询所有配件可用数量
 * */
     List<Accessories> queryByConditionIdle(@Param("startTime")String startTime,@Param("endTime")String endTime,
                                                   @Param("type")String type, @Param("companyId")String companyId,@Param("accTypeId")String accTypeId);
}