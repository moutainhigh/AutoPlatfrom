package com.gs.service;

import com.gs.bean.Checkin;
import com.gs.bean.IntentionCompany;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-05-17 20:40:15
*@des 意向公司的service
*/
public interface IntentionCompanyService extends BaseService<String, IntentionCompany>{
    /**
     * 根据状态计数
     * @param status
     * @return
     */
     int countByStatus(String status);

    /**
     * 根据状态分页查询
     * @param pager
     * @param status
     * @return
     */
     List<IntentionCompany> queryPagerByStatus(Pager pager, String status);

    /**
     * 根据查询条件计数
     * @param intention
     * @return
     */
     int countByCondition(IntentionCompany intention);

    /**
     * 根据查询条件分页查询
     * @param pager
     * @param intention
     * @return
     */
     List<IntentionCompany> queryPagerByCondition(Pager pager, IntentionCompany intention);

    /**
     * 根据指定的{top}查询前{top}条数据
     * @param top
     * @return
     */
     List<IntentionCompany> queryByTop(int top);
}