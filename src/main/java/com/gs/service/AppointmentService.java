package com.gs.service;

import com.gs.bean.Appointment;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppointmentService extends BaseService<String, Appointment>{

     int countByStatus(String status, User user);

     List<Appointment> queryPagerByStatus(Pager pager, String status,User user);

     List<Appointment> querySpeedStatus(Pager pager,User user);

     int countByCondition(Appointment appointment,User user);

     List<Appointment> queryPagerByCondition(Pager pager, Appointment appointment,User user);

     int updateSpeedStatusById(String speedStatus, String id);
    /**
     * 查询指定的前{top}条数据
     * @param top
     * @param user
     * @return
     */
     List<Appointment> queryPagerByTop(int top, User user);

     List<Appointment> queryMyName(User user);

     int queryByPhone(String phone, User user);

    /**
     * 根据用户手机号更新预约记录
     * @param user
     * @return
     */
     int updateAppByPhone(User user);
}