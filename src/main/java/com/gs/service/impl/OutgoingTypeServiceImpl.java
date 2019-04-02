package com.gs.service.impl;

import com.gs.bean.IncomingType;
import com.gs.bean.OutgoingType;
import com.gs.bean.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.OutgoingTypeDAO;
import com.gs.service.OutgoingTypeService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class OutgoingTypeServiceImpl implements OutgoingTypeService {

    @Resource
    private OutgoingTypeDAO outgoingTypeDAO;

    @Override
    public int insert(OutgoingType outgoingType) {
        return outgoingTypeDAO.insert(outgoingType);
    }

    @Override
    public int batchInsert(List<OutgoingType> list) {
        return outgoingTypeDAO.batchInsert(list);
    }

    @Override
    public int delete(OutgoingType outgoingType) {
        return outgoingTypeDAO.delete(outgoingType);
    }

    @Override
    public int deleteById(String id) {
        return outgoingTypeDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<OutgoingType> list) {
        return outgoingTypeDAO.batchDelete(list);
    }

    @Override
    public int update(OutgoingType outgoingType) {
        return outgoingTypeDAO.update(outgoingType);
    }

    @Override
    public int batchUpdate(List<OutgoingType> list) {
        return outgoingTypeDAO.batchUpdate(list);
    }

    @Override
    public List<OutgoingType> queryAll(User user) {
        return outgoingTypeDAO.queryAll(user);
    }

    @Override
    public List<OutgoingType> queryByStatus(String status, User user) {
        return outgoingTypeDAO.queryByStatus(status,user);
    }

    @Override
    public OutgoingType query(OutgoingType outgoingType, User user) {
        return outgoingTypeDAO.query(outgoingType,user);
    }

    @Override
    public OutgoingType queryById(String id) {
        return outgoingTypeDAO.queryById(id);
    }

    @Override
    public List<OutgoingType> queryByPager(Pager pager, User user) {
        return outgoingTypeDAO.queryByPager(pager,user);
    }

    @Override
    public int count(User user) {
        return outgoingTypeDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return outgoingTypeDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return outgoingTypeDAO.active(id);
    }

    @Override
    public OutgoingType queryByName(String outTypeName) {
        return outgoingTypeDAO.queryByName(outTypeName);
    }

    @Override
    public List<OutgoingType> queryPagerStatus(String status, Pager pager,User user) {
        return outgoingTypeDAO.queryPagerStatus(status, pager,user);
    }

    @Override
    public int countStatus(String status,User user) {
        return outgoingTypeDAO.countStatus(status,user);
    }

    @Override
    public List<OutgoingType> queryByPagerCondition(String companyId, String inTypeName, Pager pager) {
        return outgoingTypeDAO.queryByPagerCondition(companyId,inTypeName,pager);
    }

    @Override
    public int countCondition(String companyId, String inTypeName) {
        return outgoingTypeDAO.countCondition(companyId,inTypeName);
    }
}