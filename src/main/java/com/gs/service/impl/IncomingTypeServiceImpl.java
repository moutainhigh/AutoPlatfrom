package com.gs.service.impl;

import com.gs.bean.IncomingType;
import com.gs.bean.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.IncomingTypeDAO;
import com.gs.service.IncomingTypeService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class IncomingTypeServiceImpl implements IncomingTypeService {

    @Resource
    private IncomingTypeDAO incomingTypeDAO;

    @Override
    public int insert(IncomingType incomingType) {
        return incomingTypeDAO.insert(incomingType);
    }

    @Override
    public int batchInsert(List<IncomingType> list) {
        return incomingTypeDAO.batchInsert(list);
    }

    @Override
    public int delete(IncomingType incomingType) {
        return incomingTypeDAO.delete(incomingType);
    }

    @Override
    public int deleteById(String id) {
        return incomingTypeDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<IncomingType> list) {
        return incomingTypeDAO.batchDelete(list);
    }

    @Override
    public int update(IncomingType incomingType) {
        return incomingTypeDAO.update(incomingType);
    }

    @Override
    public int batchUpdate(List<IncomingType> list) {
        return incomingTypeDAO.batchUpdate(list);
    }

    @Override
    public List<IncomingType> queryAll(User user) {
        return incomingTypeDAO.queryAll(user);
    }

    @Override
    public List<IncomingType> queryByStatus(String status, User user) {
        return incomingTypeDAO.queryByStatus(status,user);
    }

    @Override
    public IncomingType query(IncomingType incomingType, User user) {
        return incomingTypeDAO.query(incomingType,user);
    }

    @Override
    public IncomingType queryById(String id) {
        return incomingTypeDAO.queryById(id);
    }

    @Override
    public List<IncomingType> queryByPager(Pager pager, User user) {
        return incomingTypeDAO.queryByPager(pager,user);
    }

    @Override
    public int count(User user) {
        return incomingTypeDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return incomingTypeDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return incomingTypeDAO.active(id);
    }

    @Override
    public List<IncomingType> queryPagerStatus(String status, Pager pager,User user) {
        return incomingTypeDAO.queryPagerStatus(status, pager,user);
    }

    @Override
    public int countStatus(String status,User user) {
        return incomingTypeDAO.countStatus(status,user);
    }

    @Override
    public IncomingType queryByName(String inTypeName) {
        return incomingTypeDAO.queryByName(inTypeName);
    }

    @Override
    public List<IncomingType> queryByPagerCondition(String companyId, String inTypeName, Pager pager) {
        return incomingTypeDAO.queryByPagerCondition(companyId,inTypeName,pager);
    }

    @Override
    public int countCondition(String companyId, String inTypeName) {
        return incomingTypeDAO.countCondition(companyId,inTypeName);
    }
}