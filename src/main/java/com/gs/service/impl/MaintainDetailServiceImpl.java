package com.gs.service.impl;

import com.gs.bean.MaintainDetail;
import com.gs.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.MaintainDetailDAO;
import com.gs.service.MaintainDetailService;
import com.gs.common.bean.Pager;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:58:54
 */
@Service
public class MaintainDetailServiceImpl implements MaintainDetailService {

    @Resource
    private MaintainDetailDAO maintainDetailDAO;

    public int insert(MaintainDetail maintainDetail) {
        return maintainDetailDAO.insert(maintainDetail);
    }

    public int batchInsert(List<MaintainDetail> list) {
        return maintainDetailDAO.batchInsert(list);
    }

    public int delete(MaintainDetail maintainDetail) {
        return maintainDetailDAO.delete(maintainDetail);
    }

    public int deleteById(String id) {
        return maintainDetailDAO.deleteById(id);
    }

    public int batchDelete(List<MaintainDetail> list) {
        return maintainDetailDAO.batchDelete(list);
    }

    public int update(MaintainDetail maintainDetail) {
        return maintainDetailDAO.update(maintainDetail);
    }

    public int batchUpdate(List<MaintainDetail> list) {
        return maintainDetailDAO.batchUpdate(list);
    }

    public List<MaintainDetail> queryAll(User user) {
        return maintainDetailDAO.queryAll(user);
    }

    public List<MaintainDetail> queryByStatus(String status, User user) {
        return maintainDetailDAO.queryByStatus(status, user);
    }

    public MaintainDetail query(MaintainDetail maintainDetail, User user) {
        return maintainDetailDAO.query(maintainDetail, user);
    }

    public MaintainDetail queryById(String id) {
        return maintainDetailDAO.queryById(id);
    }

    public List<MaintainDetail> queryByPager(Pager pager, User user) {
        return maintainDetailDAO.queryByPager(pager, user);
    }

    public int count(User user) {
        return maintainDetailDAO.count(user);
    }

    public int inactive(String id) {
        return maintainDetailDAO.inactive(id);
    }

    public int active(String id) {
        return maintainDetailDAO.active(id);
    }

    public int countByRecordId(String recordId, User user) {
        return maintainDetailDAO.countByRecordId(recordId, user);
    }

    public List<MaintainDetail> queryPagerByRecordId(Pager pager, String recordId, User user) {
        return maintainDetailDAO.queryPagerByRecordId(pager, recordId, user);
    }

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