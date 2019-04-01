package com.gs.service;

import com.gs.bean.MaintainFix;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface MaintainFixService extends BaseService<String, MaintainFix>{
    public List<MaintainFix> queryBymaintainPager(Pager pager,User user);
    public int MaintainCont(User user);

    public List<MaintainFix> byStatusPager(@Param("status") String status, @Param("pager") Pager pager,@Param("user") User user);
    public int countStatus(@Param("status")String status,@Param("user") User user);

    public List<MaintainFix> searchByPager(@Param("name")String name, @Param("pager")Pager pager,@Param("user") User user);

    public int searCount(@Param("name") String name,@Param("user") User user);

    public List<MaintainFix> repairByStatusPager(@Param("status") String status, @Param("pager") Pager pager,@Param("user") User user);
    public int repairCountStatus(@Param("status")String status,@Param("user") User user);
    public List<MaintainFix> searchByRepairPager(@Param("name")String name, @Param("pager")Pager pager,@Param("user") User user);
    public int searRepairCount(@Param("name")String name,@Param("user")User user);
    /*
   * 查询项目是维修还是保养
   * */
    public List<MaintainFix> queryByType(String companyId,String maintainOrFix);
}