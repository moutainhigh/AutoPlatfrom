package com.gs.dao;

import com.gs.bean.MaintainFix;
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
public interface MaintainFixDAO extends BaseDAO<String, MaintainFix>{
     List<MaintainFix> queryBymaintainPager(@Param("pager") Pager pager,@Param("user") User user);
     int MaintainCont(User user);
     List<MaintainFix> byStatusPager(@Param("status") String status, @Param("pager") Pager pager,@Param("user") User user);
     int countStatus(@Param("status")String status,@Param("user") User user);
     List<MaintainFix> searchByPager(@Param("name")String name, @Param("pager")Pager pager,@Param("user") User user);
     int searCount(@Param("name")String name,@Param("user")User user);

     List<MaintainFix> repairByStatusPager(@Param("status") String status, @Param("pager") Pager pager,@Param("user") User user);
     int repairCountStatus(@Param("status")String status,@Param("user") User user);
     List<MaintainFix> searchByRepairPager(@Param("name")String name, @Param("pager")Pager pager,@Param("user") User user);
     int searRepairCount(@Param("name")String name,@Param("user")User user);

    /*
    * 查询项目是维修还是保养
    * */
     List<MaintainFix> queryByType(@Param("companyId")String companyId,@Param("maintainOrFix")String maintainOrFix);
}