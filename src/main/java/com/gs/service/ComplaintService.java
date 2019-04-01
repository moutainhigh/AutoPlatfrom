package com.gs.service;

import com.gs.bean.Complaint;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:51
*/
public interface ComplaintService extends BaseService<String, Complaint>{

    public void updateReply(Complaint complaint);

    /**
     * 根据指定的{top}查询前{top}条数据
     * @param top
     * @param user
     * @return
     */
    public List<Complaint> queryByTop(int top, User user);

     /*
    * 车主查询自己的投诉
    * */

    public List<Complaint> queryByPagerUser(User user,Pager pager);
    public int countByUser(User user);

}