package com.gs.dao;

import com.gs.bean.Checkin;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:35:15
*/
@Repository
public interface CheckinDAO extends BaseDAO<String, Checkin>{

    /**
     * 根据状态计数
     * @param status
     * @return
     */
     int countByStatus(@Param("status") String status, @Param("user") User user);

    /**
     * 根据状态分页查询
     * @param pager
     * @param status
     * @return
     */
     List<Checkin> queryPagerByStatus(@Param("pager") Pager pager, @Param("status") String status, @Param("user") User user);

    /**
     * 根据查询条件计数
     * @param checkin
     * @return
     */
     int countByCondition(@Param("checkin") Checkin checkin, @Param("user") User user);

    /**
     * 根据查询条件分页查询
     * @param pager
     * @param checkin
     * @return
     */
     List<Checkin> queryPagerByCondition(@Param("pager") Pager pager, @Param("checkin") Checkin checkin, @Param("user") User user);

    /**
     * 根据查询userId查询回访状态
     * @param userId
     * @return
     */
     Checkin queryByTrackStatus(@Param("userId") String userId, @Param("user") User user);

    /**
     * 根据指定的{top}查询前{top}条数据
     * @param top
     * @param user
     * @return
     */
     List<Checkin> queryByTop(@Param("top") int top, @Param("user") User user);

    /**
     * 车主用户查询自己的登记信息
     * @param user
     * @return
     */
     List<Checkin> queryMyName(@Param("user") User user);

    /**
     * 根据手机号查询登记记录，防止重复添加登记记录
     * @param phone
     * @param user
     * @return
     */
     int queryByPhone(@Param("phone") String phone, @Param("user") User user);

    /**
     * 根据用户手机号更新登记记录
     * @param user
     * @return
     */
     int updateCheckinByPhone(@Param("user") User user);
}