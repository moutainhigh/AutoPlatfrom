package com.gs.dao;

import com.gs.bean.MaintainFixAcc;
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
public interface MaintainFixAccDAO extends BaseDAO<String, MaintainFixAcc>{

     List<MaintainFixAcc> queryAllByMaintainId(String[] ids);

     List<MaintainFixAcc> queryAllByPager(@Param("id") String id, @Param("user") User user, @Param("pager")Pager pager);
     int queryAllByCount(@Param("id") String id,@Param("user") User user);
}