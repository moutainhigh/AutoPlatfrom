package com.gs.service;

import com.gs.bean.MaintainRemind;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:36:52
*/
public interface MaintainRemindService extends BaseService<String, MaintainRemind>{
    /**
     * 根据查询条件计数
     * @param remind
     * @return
     */
     int countByCondition(MaintainRemind remind,User user);

    /**
     * 根据查询条件分页查询
     * @param pager
     * @param remind
     * @return
     */
     List<MaintainRemind> queryPagerByCondition(Pager pager, MaintainRemind remind,User user);

    /**
     * 根据指定的{top}查询前{top}条数据
     * @param top
     * @param user
     * @return
     */
     List<MaintainRemind> queryByTop(int top, User user);
}