package com.gs.service.impl;

import com.gs.bean.Checkin;
import com.gs.bean.IntentionCompany;
import com.gs.bean.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.IntentionCompanyDAO;
import com.gs.service.IntentionCompanyService;
import com.gs.common.bean.Pager;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @des 意向公司的service
 * @since 2017-05-17 20:40:15
 */
@Service
public class IntentionCompanyServiceImpl implements IntentionCompanyService {

    @Resource
    private IntentionCompanyDAO intentionCompanyDAO;

    public int insert(IntentionCompany intentionCompany) {
        return intentionCompanyDAO.insert(intentionCompany);
    }

    public int batchInsert(List<IntentionCompany> list) {
        return intentionCompanyDAO.batchInsert(list);
    }

    public int delete(IntentionCompany intentionCompany) {
        return intentionCompanyDAO.delete(intentionCompany);
    }

    public int deleteById(String id) {
        return intentionCompanyDAO.deleteById(id);
    }

    public int batchDelete(List<IntentionCompany> list) {
        return intentionCompanyDAO.batchDelete(list);
    }

    public int update(IntentionCompany intentionCompany) {
        return intentionCompanyDAO.update(intentionCompany);
    }

    public int batchUpdate(List<IntentionCompany> list) {
        return intentionCompanyDAO.batchUpdate(list);
    }

    public List<IntentionCompany> queryAll(User user) {
        return intentionCompanyDAO.queryAll(user);
    }

    public List<IntentionCompany> queryByStatus(String status, User user) {
        return intentionCompanyDAO.queryByStatus(status, user);
    }

    public IntentionCompany query(IntentionCompany intentionCompany, User user) {
        return intentionCompanyDAO.query(intentionCompany, user);
    }

    public IntentionCompany queryById(String id) {
        return intentionCompanyDAO.queryById(id);
    }

    public List<IntentionCompany> queryByPager(Pager pager, User user) {
        return intentionCompanyDAO.queryByPager(pager, user);
    }

    public int count(User user) {
        return intentionCompanyDAO.count(user);
    }

    public int inactive(String id) {
        return intentionCompanyDAO.inactive(id);
    }

    public int active(String id) {
        return intentionCompanyDAO.active(id);
    }

    @Override
    public int countByStatus(String status) {
        return intentionCompanyDAO.countByStatus(status);
    }

    @Override
    public List<IntentionCompany> queryPagerByStatus(Pager pager, String status) {
        return intentionCompanyDAO.queryPagerByStatus(pager, status);
    }

    @Override
    public int countByCondition(IntentionCompany intention) {
        return intentionCompanyDAO.countByCondition(intention);
    }

    @Override
    public List<IntentionCompany> queryPagerByCondition(Pager pager, IntentionCompany intention) {
        return intentionCompanyDAO.queryPagerByCondition(pager, intention);
    }

    @Override
    public List<IntentionCompany> queryByTop(int top) {
        return intentionCompanyDAO.queryByTop(top);
    }
}