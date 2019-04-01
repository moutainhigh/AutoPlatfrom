package com.gs.dao;

import com.gs.bean.info.MaterialListInfo;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Xiao-Qiang on 2017/4/26.
 */

@Repository
public interface MaterialListInfoDAO  extends BaseDAO<String, MaterialListInfo> {

    /**根据状态分页*/
    public List<MaterialListInfo> queryByStatus(@Param("pager") Pager pager, @Param("status") String status, @Param("user") User user);

    /**根据物料清单条件统计*/
    public int termCount(@Param("userName") String userName, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("user") User user);

    /**根据条件分页*/
    public List<MaterialListInfo> termQueryPager(@Param("pager") Pager pager,@Param("userName") String userName, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("user") User user);

    /**根据维修记录编号分页*/
    public List<MaterialListInfo> queryBySpeedStatus(@Param("pager") Pager pager, @Param("recordId") String recordId, @Param("user") User user);

    /**根据维修记录编号统计*/
    public int countBySpeedStatus(@Param("recordId") String recordId, @Param("user") User user);

    /**根据维修记录编号和状态分页*/
    public List<MaterialListInfo> queryBySpeedStatusAndStatus(@Param("pager") Pager pager, @Param("recordId") String recordId, @Param("materialStatus") String materialStatus, @Param("user") User user);

    /**根据维修记录编号和状态统计物料清单*/
    public int statusCount(@Param("recordId") String recordId, @Param("materialStatus") String materialStatus, @Param("user") User user);

    /**根据物料清单编号更新物料清单数量*/
    public void updateCount(MaterialListInfo materialListInfo);
}
