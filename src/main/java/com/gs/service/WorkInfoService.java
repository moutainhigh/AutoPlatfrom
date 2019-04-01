package com.gs.service;

import com.gs.bean.User;
import com.gs.bean.WorkInfo;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface WorkInfoService extends BaseService<String, WorkInfo>{

    // 技师查询自己的工单
    public List<WorkInfo> queryWorkUserId(@Param("pager")Pager pager, @Param("userId") String userId);

    // 技师查询自己的工单个数
    public int countWorkUserId(String userId);


    // 分页查询不可用的工单
    public List<WorkInfo> queryByPager_N(Pager pager, User user);

    // 分页查询不可用的工单个数
    public int count_N(User user);

    // 技师查询自己可用的工单
    public List<WorkInfo> queryWorkUserId_N(Pager pager, String userId);

    // 技师查询自己可用的工单个数
    public int countWorkUserId_N(String userId);


    // 分页查询可用的工单
    public List<WorkInfo> queryByPager_Y(Pager pager, User user);

    // 分页查询可用的工单个数
    public int count_Y(User user);

    // 技师查询自己可用的工单
    public List<WorkInfo> queryWorkUserId_Y(Pager pager, String userId);

    // 技师查询自己可用的工单个数
    public int countWorkUserId_Y(String userId);


    /**根据维修记录编号查询该记录有没有被指定员工*/
    public WorkInfo queryByRocordIdUserId(String recordId);

    /*
   * 默认查询本月的工单统计
   * */
    public List<WorkInfo> queryByDefault(String maintainOrFix, String companyId);

    /*
    * 根据年，月，季度，周，日查询所有工单
    * */
    public List<WorkInfo> queryByCondition(String startTime, String endTime, String maintainOrFix,
                                           String type, String companyId);
}