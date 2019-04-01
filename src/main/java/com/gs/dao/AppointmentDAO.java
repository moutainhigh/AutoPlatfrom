package com.gs.dao;

import com.gs.bean.Appointment;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:35:15
*/
@Repository
public interface AppointmentDAO extends BaseDAO<String, Appointment>{

    public int countByStatus(@Param("status")String status, @Param("user")User user);

    public List<Appointment> queryPagerByStatus(@Param("pager") Pager pager, @Param("status") String status,@Param("user") User user);

    public List<Appointment> querySpeedStatus(@Param("pager")Pager pager,@Param("user") User user );

    public int countByCondition(@Param("appointment") Appointment appointment,@Param("user") User user);

    public List<Appointment> queryPagerByCondition(@Param("pager") Pager pager, @Param("appointment") Appointment appointment,@Param("user") User user);

    public int updateSpeedStatusById(@Param("speedStatus") String speedStatus, @Param("id") String id);

    /**
     * 查询指定的前{top}条数据
     * @param top
     * @param user
     * @return
     */
    public List<Appointment> queryPagerByTop(@Param("top") int top, @Param("user") User user);

    public List<Appointment> queryMyName(@Param("user") User user);

    public int queryByPhone(@Param("phone") String phone, @Param("user") User user);

    /**
     * 根据用户手机号更新预约记录
     * @param user
     * @return
     */
    public int updateAppByPhone(@Param("user") User user);
}