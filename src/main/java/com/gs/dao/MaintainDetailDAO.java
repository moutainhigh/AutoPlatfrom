package com.gs.dao;

import com.gs.bean.MaintainDetail;
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
public interface MaintainDetailDAO extends BaseDAO<String, MaintainDetail>{

    /**
     * 根据维修保养记录ID计数
     * @param recordId
     * @return
     */
     int countByRecordId(@Param("recordId") String recordId, @Param("user") User user);

    /**
     * 根据维修保养记录ID分页查询
     * @param pager
     * @param recordId
     * @return
     */
     List<MaintainDetail> queryPagerByRecordId(@Param("pager") Pager pager, @Param("recordId") String recordId, @Param("usr") User user);

    /**
     * 根据记录id和项目id判断该记录是否已经存在该项目，返回1表示存在，返回0表示不存在
     * @param recordId 维修保养记录的id
     * @param maintainId 维修保养项目的id
     * @return
     */
     int queryIsDetail(@Param("recordId") String recordId, @Param("maintainId") String maintainId);

    /*
    * 默认查询本月的维修保养次数统计
    * */
     List<MaintainDetail> queryByDefault(@Param("maintainOrFix") String maintainOrFix, @Param("companyId")String companyId
                                               ,@Param("maintainId")String maintainId );

    /*
    * 根据年，月，季度，周，日查询维修保养次数统计
    * */
     List<MaintainDetail> queryByCondition(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("maintainOrFix")String maintainOrFix,
                                           @Param("type")String type, @Param("companyId")String companyId,@Param("maintainId")String maintainId);

}