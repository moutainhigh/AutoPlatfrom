package com.gs.service;

import com.gs.bean.Accessories;
import com.gs.bean.AccessoriesType;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:36:51
*/
public interface AccessoriesService extends BaseService<String, Accessories>{

     List<Accessories> queryByStatusPager(String accStatus, Pager pager, User user);

     int countByStatus(String status, User user);

     List<Accessories> queryByIdPager(String id, Pager pager,User user);

     int countByTypeId(String accTypeId, User user);

     int countByCondition(Accessories accessories, User user);

     List<Accessories> queryByCondition(Pager pager, Accessories accessories, User user);

     void updateIdle(@Param("id") String id, @Param("lastCount") int lastCount, User user);

     void updateAccUseTime(@Param("id") String id);

    /**领料申请同意后批量更新最近领料时间及库存*/
     void updateTotalAndIdle(List<Accessories> accessories);

    /**查询出对应配件编号对应的库存及可用数量*/
     Accessories queryByIdTotalAndIdle(String accId);

    /*
    * 根据年，月，季度，周，日查询所有配件总数量
    * */
     List<Accessories> queryByConditionTotal(String startTime,String endTime,
                                                   String type,String companyId,String accTypeId);

    /*
    * 根据年，月，季度，周，日查询所有配件可用数量
    * */
     List<Accessories> queryByConditionIdle(String startTime,String endTime,
                                                  String type,String companyId,String accTypeId);

}