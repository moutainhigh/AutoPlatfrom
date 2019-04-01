package com.gs.service.impl;

import com.gs.bean.Appointment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import com.gs.bean.User;
import com.gs.dao.AppointmentDAO;
import com.gs.service.AppointmentService;
import com.gs.common.bean.Pager;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:58:54
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Resource
    private AppointmentDAO appointmentDAO;

    public int insert(Appointment appointment) {
        return appointmentDAO.insert(appointment);
    }

    public int batchInsert(List<Appointment> list) {
        return appointmentDAO.batchInsert(list);
    }

    public int delete(Appointment appointment) {
        return appointmentDAO.delete(appointment);
    }

    public int deleteById(String id) {
        return appointmentDAO.deleteById(id);
    }

    public int batchDelete(List<Appointment> list) {
        return appointmentDAO.batchDelete(list);
    }

    public int update(Appointment appointment) {
        return appointmentDAO.update(appointment);
    }

    public int batchUpdate(List<Appointment> list) {
        return appointmentDAO.batchUpdate(list);
    }

    public List<Appointment> queryAll(User user) {
        return appointmentDAO.queryAll(user);
    }

    public List<Appointment> queryByStatus(String status,User user) {
        return appointmentDAO.queryByStatus(status,user);
    }

    public Appointment query(Appointment appointment,User user) {
        return appointmentDAO.query(appointment,user);
    }

    public Appointment queryById(String id) {
        return appointmentDAO.queryById(id);
    }

    public List<Appointment> queryByPager(Pager pager,User user) {
        return appointmentDAO.queryByPager(pager,user);
    }

    public int count(User user) {
        return appointmentDAO.count(user);
    }

    public int inactive(String id) {
        return appointmentDAO.inactive(id);
    }

    public int active(String id) {
        return appointmentDAO.active(id);
    }

    public int countByStatus(String status, User user) {
        return appointmentDAO.countByStatus(status,user);
    }

    public int countByCondition(Appointment appointment,User user) {
        return appointmentDAO.countByCondition(appointment,user);
    }

    public List<Appointment> queryPagerByCondition(Pager pager, Appointment appointment,User user) {
        return appointmentDAO.queryPagerByCondition(pager, appointment,user);
    }

    public int updateSpeedStatusById(String speedStatus, String id) {
        return appointmentDAO.updateSpeedStatusById(speedStatus, id);
    }

    @Override
    public List<Appointment> queryPagerByTop(int top, User user) {
        return appointmentDAO.queryPagerByTop(top, user);
    }

    public List<Appointment> queryPagerByStatus(Pager pager, String status,User user) {
        return appointmentDAO.queryPagerByStatus(pager, status, user);
    }

    public List<Appointment> querySpeedStatus(Pager pager,User user) {
        return appointmentDAO.querySpeedStatus(pager,user);
    }

    public List<Appointment> queryMyName(User user){
        return appointmentDAO.queryMyName(user);
    }

    @Override
    public int queryByPhone(String phone, User user) {
        return appointmentDAO.queryByPhone(phone, user);
    }

    @Override
    public int updateAppByPhone(User user) {
        return appointmentDAO.updateAppByPhone(user);
    }
}