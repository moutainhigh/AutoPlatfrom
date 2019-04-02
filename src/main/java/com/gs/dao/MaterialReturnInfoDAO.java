package com.gs.dao;

import com.gs.bean.User;
import com.gs.bean.info.MaterialReturnInfo;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Xiao-Qiang on 2017/5/16.
 */
@Repository
public interface MaterialReturnInfoDAO {

    /**添加退料信息*/
     void insertReturn(MaterialReturnInfo materialReturnInfo);

    /**通过维修记录编号查询是否存在有这条记录*/
     int isRecordExist(@Param("recordId") String recordId, @Param("accId") String accId);

    /**根据维修记录编号分页*/
     List<MaterialReturnInfo> queryBySpeedStatus(@Param("pager") Pager pager, @Param("recordId") String recordId, @Param("user") User user);

    /**根据维修记录编号统计*/
     int countBySpeedStatus(@Param("recordId") String recordId, @Param("user") User user);
}
