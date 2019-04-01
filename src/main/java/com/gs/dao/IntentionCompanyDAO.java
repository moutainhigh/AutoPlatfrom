package com.gs.dao;

import com.gs.bean.Checkin;
import com.gs.bean.IntentionCompany;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-05-17 20:40:15
*@des 意向公司的dao
*/
@Repository
public interface IntentionCompanyDAO extends BaseDAO<String, IntentionCompany>{
    /**
     * 根据状态计数
     * @param status
     * @return
     */
    public int countByStatus(@Param("status") String status);

    /**
     * 根据状态分页查询
     * @param pager
     * @param status
     * @return
     */
    public List<IntentionCompany> queryPagerByStatus(@Param("pager") Pager pager, @Param("status") String status);

    /**
     * 根据查询条件计数
     * @param intention
     * @return
     */
    public int countByCondition(@Param("intention") IntentionCompany intention);

    /**
     * 根据查询条件分页查询
     * @param pager
     * @param intention
     * @return
     */
    public List<IntentionCompany> queryPagerByCondition(@Param("pager") Pager pager, @Param("intention") IntentionCompany intention);

    /**
     * 根据指定的{top}查询前{top}条数据
     * @param top
     * @return
     */
    public List<IntentionCompany> queryByTop(@Param("top") int top);
}