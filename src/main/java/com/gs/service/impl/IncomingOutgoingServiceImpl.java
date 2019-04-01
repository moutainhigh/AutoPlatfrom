package com.gs.service.impl;

import com.gs.bean.IncomingOutgoing;
import com.gs.bean.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.IncomingOutgoingDAO;
import com.gs.service.IncomingOutgoingService;
import com.gs.common.bean.Pager;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:58:54
 */
@Service
public class IncomingOutgoingServiceImpl implements IncomingOutgoingService {

    @Resource
    private IncomingOutgoingDAO incomingOutgoingDAO;

    public int insert(IncomingOutgoing incomingOutgoing) {
        return incomingOutgoingDAO.insert(incomingOutgoing);
    }

    public int batchInsert(List<IncomingOutgoing> list) {
        return incomingOutgoingDAO.batchInsert(list);
    }

    public int delete(IncomingOutgoing incomingOutgoing) {
        return incomingOutgoingDAO.delete(incomingOutgoing);
    }

    public int deleteById(String id) {
        return incomingOutgoingDAO.deleteById(id);
    }

    public int batchDelete(List<IncomingOutgoing> list) {
        return incomingOutgoingDAO.batchDelete(list);
    }

    public int update(IncomingOutgoing incomingOutgoing) {
        return incomingOutgoingDAO.update(incomingOutgoing);
    }

    public int batchUpdate(List<IncomingOutgoing> list) {
        return incomingOutgoingDAO.batchUpdate(list);
    }

    public List<IncomingOutgoing> queryAll(User user) {
        return incomingOutgoingDAO.queryAll(user);
    }

    public List<IncomingOutgoing> queryByStatus(String status,User user) {
        return incomingOutgoingDAO.queryByStatus(status,user);
    }

    public IncomingOutgoing query(IncomingOutgoing incomingOutgoing,User user) {
        return incomingOutgoingDAO.query(incomingOutgoing,user);
    }

    public IncomingOutgoing queryById(String id) {
        return incomingOutgoingDAO.queryById(id);
    }

    public List<IncomingOutgoing> queryByPager(Pager pager,User user) {
        return incomingOutgoingDAO.queryByPager(pager,user);
    }

    public int count(User user) {
        return incomingOutgoingDAO.count(user);
    }

    public int inactive(String id) {
        return incomingOutgoingDAO.inactive(id);
    }

    public int active(String id) {
        return incomingOutgoingDAO.active(id);
    }

    public List<IncomingOutgoing> queryByInOutType(Pager pager, IncomingOutgoing incomingOutgoing,User user) {
        return incomingOutgoingDAO.queryByInOutType(pager, incomingOutgoing,user);
    }

    public int countByInOutType(IncomingOutgoing incomingOutgoing,User user) {
        return incomingOutgoingDAO.countByInOutType(incomingOutgoing,user);
    }

    public List<IncomingOutgoing> queryByDefault(int inOutType, String companyId) {
        return incomingOutgoingDAO.queryByDefault(inOutType, companyId);
    }

    @Override
    public List<IncomingOutgoing> queryByCondition(String startTime, String endTime, int inOutType, String type, String companyId) {
        return incomingOutgoingDAO.queryByCondition(startTime, endTime, inOutType, type, companyId);
    }

    @Override
    public void addInsert(List<IncomingOutgoing> incomingOutgoings) {
        incomingOutgoingDAO.addInsert(incomingOutgoings);
    }

    @Override
    public List<IncomingOutgoing> queryPagerStatus(String status, Pager pager,User user) {
        return incomingOutgoingDAO.queryPagerStatus(status, pager,user);
    }

    @Override
    public int countStatus(String status,User user) {
        return incomingOutgoingDAO.countStatus(status,user);
    }
}