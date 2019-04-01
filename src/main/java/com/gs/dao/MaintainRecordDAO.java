package com.gs.dao;

import com.gs.bean.Checkin;
import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:35:15
 */
@Repository
public interface MaintainRecordDAO extends BaseDAO<String, MaintainRecord> {
    /**
     * 根据状态计数
     *
     * @param status
     * @return
     */
    public int countByStatus(@Param("status") String status, @Param("user") User user);

    /**
     * 根据状态分页查询
     *
     * @param pager
     * @param status
     * @return
     */
    public List<MaintainRecord> queryPagerByStatus(@Param("pager") Pager pager, @Param("status") String status, @Param("user") User user);

    /**
     * 根据查询条件计数
     *
     * @param record
     * @return
     */
    public int countByCondition(@Param("record") MaintainRecord record, @Param("user") User user, @Param("speedStatus") String[] speedStatus);

    /**
     * 根据查询条件分页查询
     *
     * @param pager
     * @param record
     * @return
     */
    public List<MaintainRecord> queryPagerByCondition(@Param("pager") Pager pager, @Param("record") MaintainRecord record, @Param("user") User user, @Param("speedStatus") String[] speedStatus);

    /**
     * 根据状态计数
     *
     * @param status
     * @return
     */
    public int countByTrackStatus(@Param("status") String status, @Param("user") User user);

    /**
     * 根据状态分页查询
     *
     * @param pager
     * @param status
     * @return
     */
    public List<MaintainRecord> queryPagerByTrackStatus(@Param("pager") Pager pager, @Param("status") String status, @Param("user") User user);

    /**
     * 根据状态checkId更新
     *
     * @param checkinId
     * @return
     */
    public void updateTrackStatus(@Param("trackStatus") String trackStatus, @Param("checkinId") String checkinId);

    /**
     * 根据id更新记录的进度
     *
     * @param speedStatus 当前进度
     * @param id          维修保养记录的id
     * @return
     */
    public int updateSpeedStatusById(@Param("speedStatus") String speedStatus, @Param("id") String id);

    /**
     * 根据进度状态计数
     *
     * @param speedStatus
     * @return
     */
    public int countBySpeedStatus(@Param("speedStatus") String[] speedStatus, @Param("user") User user);

    /**
     * 根据进度状态分页查询
     *
     * @param pager
     * @param speedStatus
     * @return
     */
    public List<MaintainRecord> queryPagerBySpeedStatus(@Param("speedStatus") String[] speedStatus, @Param("pager") Pager pager, @Param("user") User user);

    /**
     * 根据维修保养记录id把提车时间更新成当前时间
     *
     * @param recordId
     * @return
     */
    public int updatePickupTime(String recordId);

    /*修改预计结束时间*/
    public void updateTime(MaintainRecord maintainRecord);

    /*
    * 默认查询本月的维修保养记录统计
    * */
    public List<MaintainRecord> queryByDefault(@Param("maintainOrFix") String maintainOrFix, @Param("companyId") String companyId);

    /*
    * 根据年，月，季度，周，日查询所有维修保养统计
    * */
    public List<MaintainRecord> queryByCondition(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("maintainOrFix") String maintainOrFix,
                                                 @Param("type") String type, @Param("companyId") String companyId);

    /**
     * 根据员工的id查询该员工负责的维修保养记录，计算个数
     * @param user
     * @return
     */
    public int countByUserId(@Param("user") User user, @Param("pickingStatus") String pickingStatus, @Param("speedStatus") String[] speedStatus);

    /**
     * 根据员工的id查询该员工负责的维修保养记录，分页查询
     * @param pager
     * @param user
     * @return
     */
    public List<MaintainRecord> queryPagerByUserId(@Param("pager") Pager pager, @Param("user") User user, @Param("pickingStatus") String pickingStatus, @Param("speedStatus") String[] speedStatus);

    /**
     * 根据id更新记录的进度
     *
     * @param pickingStatus 领料状态
     * @param id          维修保养记录的id
     * @return
     */
    public int updatePickingStatusById(@Param("pickingStatus") String pickingStatus, @Param("id") String id);

    /**
     * 根据进度状态和领料状态计数
     *
     * @param speedStatus
     * @return
     */
    public int countBySpeedStatusAndPickingStatus(@Param("speedStatus") String[] speedStatus, @Param("pickingStatus") String pickingStatus, @Param("user") User user);

    /**
     * 根据进度状态和领料状态分页查询
     *
     * @param pager
     * @param speedStatus
     * @return
     */
    public List<MaintainRecord> queryPagerBySpeedStatusAndPickingStatus(@Param("speedStatus") String[] speedStatus, @Param("pager") Pager pager, @Param("pickingStatus") String pickingStatus, @Param("user") User user);


    /**
     * 根据维修保养记录分页查询车主进度
     * @return
     */
    public List<MaintainRecord> queryByProgressPager(@Param("pager") Pager pager, @Param("user")User user);

    /**
     * 根据维修保养记录分页查询车主进度个数
     * @return
     */
    public int countByProgressPager(User user);

    /**
     * 根据维修保养记录分页查询可用车主进度
     * @return
     */
    public List<MaintainRecord> queryByProgressPager_Y(@Param("pager") Pager pager, @Param("user")User user);

    /**
     * 根据维修保养记录分页查询可用车主进度个数
     * @return
     */
    public int countByProgressPager_Y(User user);

    /**
     * 根据维修保养记录分页查询不可用车主进度
     * @return
     */
    public List<MaintainRecord> queryByProgressPager_N(@Param("pager") Pager pager, @Param("user")User user);

    /**
     * 根据维修保养记录分页查询不可用车主进度个数
     * @return
     */
    public int countByProgressPager_N(User user);

    /**
     * 根据维修保养记录分页查询车主自己进度
     * @return
     */
    public List<MaintainRecord> queryByUser(@Param("pager") Pager pager, @Param("user")User user);

    /**
     * 根据维修保养记录分页查询车主自己进度个数
     * @return
     */
    public int countByUser(User user);

    /**
     * 根据维修保养记录分页查询技师管理进度
     * @return
     */
    public List<MaintainRecord> queryByEmp(@Param("pager") Pager pager, @Param("user")User user);

    /**
     * 根据维修保养记录分页查询技师管理进度个数
     * @return
     */
    public int countByEmp(User user);

    /**
     * 根据维修保养记录分页查询车主自己可用进度
     * @return
     */
    public List<MaintainRecord> queryByUser_Y(@Param("pager") Pager pager, @Param("user")User user);

    /**
     * 根据维修保养记录分页查询车主自己可用进度个数
     * @return
     */
    public int countByUser_Y(User user);

    /**
     * 根据维修保养记录分页查询技师管理可用进度
     * @return
     */
    public List<MaintainRecord> queryByEmp_Y(@Param("pager") Pager pager, @Param("user")User user);

    /**
     * 根据维修保养记录分页查询技师管理可用进度个数
     * @return
     */
    public int countByEmp_Y(User user);


    /**
     * 根据维修保养记录分页查询车主自己不可用进度
     * @return
     */
    public List<MaintainRecord> queryByUser_N(@Param("pager") Pager pager, @Param("user")User user);

    /**
     * 根据维修保养记录分页查询车主自己不可用进度个数
     * @return
     */
    public int countByUser_N(User user);

    /**
     * 根据维修保养记录分页查询技师管理不可用进度
     * @return
     */
    public List<MaintainRecord> queryByEmp_N(@Param("pager") Pager pager, @Param("user")User user);

    /**
     * 根据维修保养记录分页查询技师管理不可用进度个数
     * @return
     */
    public int countByEmp_N(User user);


    /**
     * 根据id更新记录的实际结束时间
     *
     * @param endTime 领料状态
     * @param id          维修保养记录的id
     * @return
     */
    public int updateEndTimeById(@Param("endTime") Date endTime, @Param("id") String id);

    public List<MaintainRecord> queryByMyName(@Param("pager") Pager pager, @Param("user") User user);
    /**
     * 前台根据用户手机号查询数据
     * @param userPhone
     * @return
     */
    public List<MaintainRecord> queryCustomerCar(@Param("userPhone") String userPhone);

    /**
     * 查询自己的维修保养记录
     * @param user
     * @return
     */
    public List<MaintainRecord> queryMyName(@Param("user") User user);

    /**
     * 更新用户签字状态
     * @param id
     * @param status
     * @return
     */
    public int updateSign(@Param("id") String id, @Param("status") String status);

}