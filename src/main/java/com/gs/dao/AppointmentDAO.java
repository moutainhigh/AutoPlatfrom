package com.gs.dao;

import com.gs.bean.Appointment;
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
public interface AppointmentDAO extends BaseDAO<String, Appointment>{

     int countByStatus(@Param("status")String status, @Param("user")User user);

     List<Appointment> queryPagerByStatus(@Param("pager") Pager pager, @Param("status") String status,@Param("user") User user);

     List<Appointment> querySpeedStatus(@Param("pager")Pager pager,@Param("user") User user );

     int countByCondition(@Param("appointment") Appointment appointment,@Param("user") User user);

     List<Appointment> queryPagerByCondition(@Param("pager") Pager pager, @Param("appointment") Appointment appointment,@Param("user") User user);

     int updateSpeedStatusById(@Param("speedStatus") String speedStatus, @Param("id") String id);

    /**
     * 查询指定的前{top}条数据
     * @param top
     * @param user
     * @return
     */
     List<Appointment> queryPagerByTop(@Param("top") int top, @Param("user") User user);

     List<Appointment> queryMyName(@Param("user") User user);

     int queryByPhone(@Param("phone") String phone, @Param("user") User user);

    /**
     * 根据用户手机号更新预约记录
     * @param user
     * @return
     */
     int updateAppByPhone(@Param("user") User user);
}