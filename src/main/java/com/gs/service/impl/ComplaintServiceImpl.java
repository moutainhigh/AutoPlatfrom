package com.gs.service.impl;

import com.gs.bean.Complaint;
import com.gs.bean.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.ComplaintDAO;
import com.gs.service.ComplaintService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class ComplaintServiceImpl implements ComplaintService {

	@Resource
	private ComplaintDAO complaintDAO;

	public int insert(Complaint complaint) { return complaintDAO.insert(complaint); }
	public int batchInsert(List<Complaint> list) { return complaintDAO.batchInsert(list); }
	public int delete(Complaint complaint) { return complaintDAO.delete(complaint); }
	public int deleteById(String id) {
        return complaintDAO.deleteById(id);
    }
	public int batchDelete(List<Complaint> list) { return complaintDAO.batchDelete(list); }
	public int update(Complaint complaint) { return complaintDAO.update(complaint); }
	public int batchUpdate(List<Complaint> list) { return complaintDAO.batchUpdate(list); }
	public List<Complaint> queryAll(User user) { return complaintDAO.queryAll(user); }
	public List<Complaint> queryByStatus(String status,User user) { return complaintDAO.queryByStatus(status,user); }
	public Complaint query(Complaint complaint,User user) { return complaintDAO.query(complaint,user); }
	public Complaint queryById(String id) { return complaintDAO.queryById(id); }
	public List<Complaint> queryByPager(Pager pager,User user) { return complaintDAO.queryByPager(pager,user); }
	public int count(User user) { return complaintDAO.count(user); }
	public int inactive(String id) { return complaintDAO.inactive(id); }
	public int active(String id) { return complaintDAO.active(id); }

	public void updateReply(Complaint complaint) {
		complaintDAO.updateReply(complaint);
	}

	@Override
	public List<Complaint> queryByTop(int top, User user) {
		return complaintDAO.queryByTop(top, user);
	}

	@Override
	public List<Complaint> queryByPagerUser(User user, Pager pager) {
		return complaintDAO.queryByPagerUser(user,pager);
	}

	@Override
	public int countByUser(User user) {
		return complaintDAO.countByUser(user);
	}
}