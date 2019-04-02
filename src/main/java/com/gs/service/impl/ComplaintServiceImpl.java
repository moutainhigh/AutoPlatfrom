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
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Resource
    private ComplaintDAO complaintDAO;

    @Override
    public int insert(Complaint complaint) {
        return complaintDAO.insert(complaint);
    }

    @Override
    public int batchInsert(List<Complaint> list) {
        return complaintDAO.batchInsert(list);
    }

    @Override
    public int delete(Complaint complaint) {
        return complaintDAO.delete(complaint);
    }

    @Override
    public int deleteById(String id) {
        return complaintDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<Complaint> list) {
        return complaintDAO.batchDelete(list);
    }

    @Override
    public int update(Complaint complaint) {
        return complaintDAO.update(complaint);
    }

    @Override
    public int batchUpdate(List<Complaint> list) {
        return complaintDAO.batchUpdate(list);
    }

    @Override
    public List<Complaint> queryAll(User user) {
        return complaintDAO.queryAll(user);
    }

    @Override
    public List<Complaint> queryByStatus(String status, User user) {
        return complaintDAO.queryByStatus(status, user);
    }

    @Override
    public Complaint query(Complaint complaint, User user) {
        return complaintDAO.query(complaint, user);
    }

    @Override
    public Complaint queryById(String id) {
        return complaintDAO.queryById(id);
    }

    @Override
    public List<Complaint> queryByPager(Pager pager, User user) {
        return complaintDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return complaintDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return complaintDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return complaintDAO.active(id);
    }

    @Override
    public void updateReply(Complaint complaint) {
        complaintDAO.updateReply(complaint);
    }

    @Override
    public List<Complaint> queryByTop(int top, User user) {
        return complaintDAO.queryByTop(top, user);
    }

    @Override
    public List<Complaint> queryByPagerUser(User user, Pager pager) {
        return complaintDAO.queryByPagerUser(user, pager);
    }

    @Override
    public int countByUser(User user) {
        return complaintDAO.countByUser(user);
    }
}