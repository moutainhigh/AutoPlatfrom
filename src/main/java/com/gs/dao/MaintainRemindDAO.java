package com.gs.dao;

import com.gs.bean.MaintainRemind;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:35:15
*/
@Repository
public interface MaintainRemindDAO extends BaseDAO<String, MaintainRemind>{

    /**
     * 根据查询条件计数
     * @param remind
     * @param user
     * @return
     */
     int countByCondition(@Param("remind")MaintainRemind remind,@Param("user") User user);

    /**
     * 根据查询条件分页查询
     * @param pager
     * @param remind
     * @param user
     * @return
     */
     List<MaintainRemind> queryPagerByCondition(@Param("pager") Pager pager, @Param("remind") MaintainRemind remind,@Param("user") User user);

    /**
     * 根据指定的{top}查询前{top}条数据
     * @param top
     * @param user
     * @return
     */
     List<MaintainRemind> queryByTop(@Param("top") int top, @Param("user") User user);
}