package com.gs.service;

import com.gs.bean.MaintainDetail;
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
public interface MaintainDetailService extends BaseService<String, MaintainDetail>{
    /**
     * 根据维修保养记录ID计数
     * @param recordId
     * @return
     */
     int countByRecordId(String recordId, User user);

    /**
     * 根据维修保养记录ID分页查询
     * @param pager
     * @param recordId
     * @return
     */
     List<MaintainDetail> queryPagerByRecordId(Pager pager, String recordId, User user);

    /**
     * 根据记录id和项目id判断该记录是否已经存在该项目，返回1表示存在，返回0表示不存在
     * @param recordId 维修保养记录的id
     * @param maintainId 维修保养项目的id
     * @return
     */
     int queryIsDetail(String recordId, String maintainId);

    /*
 * 默认查询本月的维修保养次数统计
 * */
     List<MaintainDetail> queryByDefault( String maintainOrFix, String companyId
                                                ,String maintainId );

    /*
    * 根据年，月，季度，周，日查询维修保养次数统计
    * */
     List<MaintainDetail> queryByCondition(String startTime,String endTime,String maintainOrFix,
                                                 String type, String companyId,String maintainId);

}