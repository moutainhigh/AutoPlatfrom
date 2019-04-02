package com.gs.service.impl;

import com.gs.bean.Checkin;
import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import com.gs.dao.MaintainRecordDAO;
import com.gs.service.MaintainRecordService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class MaintainRecordServiceImpl implements MaintainRecordService {

    @Resource
    private MaintainRecordDAO maintainRecordDAO;

    @Override
    public int insert(MaintainRecord maintainRecord) {
        return maintainRecordDAO.insert(maintainRecord);
    }

    @Override
    public int batchInsert(List<MaintainRecord> list) {
        return maintainRecordDAO.batchInsert(list);
    }

    @Override
    public int delete(MaintainRecord maintainRecord) {
        return maintainRecordDAO.delete(maintainRecord);
    }

    @Override
    public int deleteById(String id) {
        return maintainRecordDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<MaintainRecord> list) {
        return maintainRecordDAO.batchDelete(list);
    }

    @Override
    public int update(MaintainRecord maintainRecord) {
        return maintainRecordDAO.update(maintainRecord);
    }

    @Override
    public int batchUpdate(List<MaintainRecord> list) {
        return maintainRecordDAO.batchUpdate(list);
    }

    @Override
    public List<MaintainRecord> queryAll(User user) {
        return maintainRecordDAO.queryAll(user);
    }

    @Override
    public List<MaintainRecord> queryByStatus(String status, User user) {
        return maintainRecordDAO.queryByStatus(status, user);
    }

    @Override
    public MaintainRecord query(MaintainRecord maintainRecord, User user) {
        return maintainRecordDAO.query(maintainRecord, user);
    }

    @Override
    public MaintainRecord queryById(String id) {
        return maintainRecordDAO.queryById(id);
    }

    @Override
    public List<MaintainRecord> queryByPager(Pager pager, User user) {
        return maintainRecordDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return maintainRecordDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return maintainRecordDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return maintainRecordDAO.active(id);
    }

    @Override
    public int countByStatus(String status, User user) {
        return maintainRecordDAO.countByStatus(status, user);
    }

    @Override
    public List<MaintainRecord> queryPagerByStatus(Pager pager, String status, User user) {
        return maintainRecordDAO.queryPagerByStatus(pager, status, user);
    }

    @Override
    public int countByCondition(MaintainRecord record, User user) {
        String[] speedStatus = record.getSpeedStatus().split(",");
        return maintainRecordDAO.countByCondition(record, user, speedStatus);
    }

    @Override
    public List<MaintainRecord> queryPagerByCondition(Pager pager, MaintainRecord record, User user) {
        String[] speedStatus = record.getSpeedStatus().split(",");
        return maintainRecordDAO.queryPagerByCondition(pager, record, user, speedStatus);
    }

    @Override
    public int countByTrackStatus(String status, User user) {
        return maintainRecordDAO.countByTrackStatus(status, user);
    }

    @Override
    public List<MaintainRecord> queryPagerByTrackStatus(Pager pager, String status, User user) {
        return maintainRecordDAO.queryPagerByTrackStatus(pager, status, user);
    }

    @Override
    public void updateTrackStatus(String trackStatus, String checkinId) {
        maintainRecordDAO.updateTrackStatus(trackStatus, checkinId);
    }

    @Override
    public void updateTime(MaintainRecord maintainRecord) {
        maintainRecordDAO.updateTime(maintainRecord);
    }

    @Override
    public int updateSpeedStatusById(String speedStatus, String id) {
        return maintainRecordDAO.updateSpeedStatusById(speedStatus, id);
    }

    @Override
    public int countBySpeedStatus(String[] speedStatus, User user) {
        return maintainRecordDAO.countBySpeedStatus(speedStatus, user);
    }

    @Override
    public List<MaintainRecord> queryPagerBySpeedStatus(Pager pager, String[] speedStatus, User user) {
        return maintainRecordDAO.queryPagerBySpeedStatus(speedStatus, pager, user);
    }

    @Override
    public int updatePickupTime(String recordId) {
        return maintainRecordDAO.updatePickupTime(recordId);
    }

    @Override
    public List<MaintainRecord> queryByDefault(String maintainOrFix, String companyId) {
        return maintainRecordDAO.queryByDefault(maintainOrFix,companyId);
    }

    @Override
    public List<MaintainRecord> queryByCondition(String startTime, String endTime, String maintainOrFix, String type, String companyId) {
        return maintainRecordDAO.queryByCondition(startTime,endTime,maintainOrFix,type,companyId);
    }

    @Override
    public int countByUserId(User user, String pickingStatus, String[] speedStatus) {
        return maintainRecordDAO.countByUserId(user, pickingStatus, speedStatus);
    }

    @Override
    public List<MaintainRecord> queryPagerByUserId(Pager pager, User user, String pickingStatus, String[] speedStatus) {
        return maintainRecordDAO.queryPagerByUserId(pager, user, pickingStatus, speedStatus);
    }

    @Override
    public int updatePickingStatusById(String pickingStatus, String id) {
        return maintainRecordDAO.updatePickingStatusById(pickingStatus, id);
    }

    @Override
    public int countBySpeedStatusAndPickingStatus(String[] speedStatus, String pickingStatus, User user) {
        return maintainRecordDAO.countBySpeedStatusAndPickingStatus(speedStatus, pickingStatus, user);
    }

    @Override
    public List<MaintainRecord> queryPagerBySpeedStatusAndPickingStatus(String[] speedStatus, Pager pager, String pickingStatus, User user) {
        return maintainRecordDAO.queryPagerBySpeedStatusAndPickingStatus(speedStatus, pager, pickingStatus, user);
    }

    @Override
    public List<MaintainRecord> queryByProgressPager(Pager pager, User user) {
        return maintainRecordDAO.queryByProgressPager(pager, user);
    }

    @Override
    public int countByProgressPager(User user) {
        return maintainRecordDAO.countByProgressPager(user);
    }

    @Override
    public List<MaintainRecord> queryByProgressPager_Y(Pager pager, User user) {
        return maintainRecordDAO.queryByProgressPager_Y(pager, user);
    }
    @Override
    public int updateEndTimeById(Date endTime, String id) {
        return maintainRecordDAO.updateEndTimeById(endTime, id);
    }

    @Override
    public List<MaintainRecord> queryCustomerCar(String userPhone) {
        return maintainRecordDAO.queryCustomerCar(userPhone);
    }

    @Override
    public int countByProgressPager_Y(User user) {
        return maintainRecordDAO.countByProgressPager_Y(user);
    }

    @Override
    public List<MaintainRecord> queryByProgressPager_N(Pager pager, User user) {
        return maintainRecordDAO.queryByProgressPager_N(pager, user);
    }

    @Override
    public int countByProgressPager_N(User user) {
        return maintainRecordDAO.countByProgressPager_N(user);
    }

    @Override
    public List<MaintainRecord> queryByUser(Pager pager, User user) {
        return maintainRecordDAO.queryByUser(pager, user);
    }

    @Override
    public int countByUser(User user) {
        return maintainRecordDAO.countByUser(user);
    }

    @Override
    public List<MaintainRecord> queryByEmp(Pager pager, User user) {
        return maintainRecordDAO.queryByEmp(pager, user);
    }

    @Override
    public int countByEmp(User user) {
        return maintainRecordDAO.countByEmp(user);
    }

    @Override
    public List<MaintainRecord> queryByUser_Y(Pager pager, User user) {
        return maintainRecordDAO.queryByUser_Y(pager, user);
    }

    @Override
    public int countByUser_Y(User user) {
        return maintainRecordDAO.countByUser_Y(user);
    }

    @Override
    public List<MaintainRecord> queryByEmp_Y(Pager pager, User user) {
        return maintainRecordDAO.queryByEmp_Y(pager, user);
    }

    @Override
    public int countByEmp_Y(User user) {
        return maintainRecordDAO.countByEmp_Y(user);
    }

    @Override
    public List<MaintainRecord> queryByUser_N(Pager pager, User user) {
        return maintainRecordDAO.queryByUser_N(pager, user);
    }

    @Override
    public int countByUser_N(User user) {
        return maintainRecordDAO.countByUser_N(user);
    }

    @Override
    public List<MaintainRecord> queryByEmp_N(Pager pager, User user) {
        return maintainRecordDAO.queryByEmp_N(pager, user);
    }

    @Override
    public int countByEmp_N(User user) {
        return maintainRecordDAO.countByEmp_N(user);
    }

    @Override
    public List<MaintainRecord> queryByMyName(Pager pager, User user){
        return maintainRecordDAO.queryByMyName(pager, user);
    }

    @Override
    public List<MaintainRecord> queryMyName(User user) {
        return maintainRecordDAO.queryMyName(user);
    }

    @Override
    public int updateSign(String id, String status) {
        return maintainRecordDAO.updateSign(id, status);
    }
}