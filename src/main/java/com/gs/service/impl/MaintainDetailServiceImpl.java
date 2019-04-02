package com.gs.service.impl;

import com.gs.bean.MaintainDetail;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.MaintainDetailDAO;
import com.gs.service.MaintainDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class MaintainDetailServiceImpl implements MaintainDetailService {

    @Resource
    private MaintainDetailDAO maintainDetailDAO;

    @Override
    public int insert(MaintainDetail maintainDetail) {
        return maintainDetailDAO.insert(maintainDetail);
    }

    @Override
    public int batchInsert(List<MaintainDetail> list) {
        return maintainDetailDAO.batchInsert(list);
    }

    @Override
    public int delete(MaintainDetail maintainDetail) {
        return maintainDetailDAO.delete(maintainDetail);
    }

    @Override
    public int deleteById(String id) {
        return maintainDetailDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<MaintainDetail> list) {
        return maintainDetailDAO.batchDelete(list);
    }

    @Override
    public int update(MaintainDetail maintainDetail) {
        return maintainDetailDAO.update(maintainDetail);
    }

    @Override
    public int batchUpdate(List<MaintainDetail> list) {
        return maintainDetailDAO.batchUpdate(list);
    }

    @Override
    public List<MaintainDetail> queryAll(User user) {
        return maintainDetailDAO.queryAll(user);
    }

    @Override
    public List<MaintainDetail> queryByStatus(String status, User user) {
        return maintainDetailDAO.queryByStatus(status, user);
    }

    @Override
    public MaintainDetail query(MaintainDetail maintainDetail, User user) {
        return maintainDetailDAO.query(maintainDetail, user);
    }

    @Override
    public MaintainDetail queryById(String id) {
        return maintainDetailDAO.queryById(id);
    }

    @Override
    public List<MaintainDetail> queryByPager(Pager pager, User user) {
        return maintainDetailDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return maintainDetailDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return maintainDetailDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return maintainDetailDAO.active(id);
    }

    @Override
    public int countByRecordId(String recordId, User user) {
        return maintainDetailDAO.countByRecordId(recordId, user);
    }

    @Override
    public List<MaintainDetail> queryPagerByRecordId(Pager pager, String recordId, User user) {
        return maintainDetailDAO.queryPagerByRecordId(pager, recordId, user);
    }

    @Override
    public int queryIsDetail(String recordId, String maintainId) {
        return maintainDetailDAO.queryIsDetail(recordId, maintainId);
    }

    @Override
    public List<MaintainDetail> queryByDefault(String maintainOrFix, String companyId, String maintainId) {
        return maintainDetailDAO.queryByDefault(maintainOrFix,companyId,maintainId);
    }

    @Override
    public List<MaintainDetail> queryByCondition(String startTime, String endTime, String maintainOrFix, String type, String companyId, String maintainId) {
        return maintainDetailDAO.queryByCondition(startTime,endTime,maintainOrFix,type,companyId,maintainId);
    }
}