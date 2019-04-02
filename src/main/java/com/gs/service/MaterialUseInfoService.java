package com.gs.service;

import com.gs.bean.User;
import com.gs.bean.info.MaterialUseInfo;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Xiao-Qiang on 2017/5/15.
 */
public interface MaterialUseInfoService {

    /**根据维修记录编号查询全部*/
     List<MaterialUseInfo> queryAll(@Param("recordId") String recordId, @Param("user") User user);

    /**添加这条维修保养记录下的所有物料申请*/
     void addByRecordIdMu(List<MaterialUseInfo> muis);

    /**根据维修记录编号分页*/
     List<MaterialUseInfo> queryBySpeedStatus(@Param("pager") Pager pager, @Param("recordId") String recordId, @Param("user") User user);

    /**根据维修记录编号统计*/
     int countBySpeedStatus(@Param("recordId") String recordId, @Param("user") User user);

    /**根据领料编号来查当前数据的领料数据*/
     MaterialUseInfo queryByIdAccCount(String materialUseId);
}
