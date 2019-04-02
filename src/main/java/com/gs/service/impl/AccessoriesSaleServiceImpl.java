package com.gs.service.impl;

import com.gs.bean.AccessoriesSale;
import com.gs.bean.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.AccessoriesSaleDAO;
import com.gs.service.AccessoriesSaleService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:53
 */
@Service
public class AccessoriesSaleServiceImpl implements AccessoriesSaleService {

    @Resource
    private AccessoriesSaleDAO accessoriesSaleDAO;

    @Override
    public int insert(AccessoriesSale accessoriesSale) {
        return accessoriesSaleDAO.insert(accessoriesSale);
    }

    @Override
    public int batchInsert(List<AccessoriesSale> list) {
        return accessoriesSaleDAO.batchInsert(list);
    }

    @Override
    public int delete(AccessoriesSale accessoriesSale) {
        return accessoriesSaleDAO.delete(accessoriesSale);
    }

    @Override
    public int deleteById(String id) {
        return accessoriesSaleDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<AccessoriesSale> list) {
        return accessoriesSaleDAO.batchDelete(list);
    }

    @Override
    public int update(AccessoriesSale accessoriesSale) {
        return accessoriesSaleDAO.update(accessoriesSale);
    }

    @Override
    public int batchUpdate(List<AccessoriesSale> list) {
        return accessoriesSaleDAO.batchUpdate(list);
    }

    @Override
    public List<AccessoriesSale> queryAll(User user) {
        return accessoriesSaleDAO.queryAll(user);
    }

    @Override
    public List<AccessoriesSale> queryByStatus(String status, User user) {
        return accessoriesSaleDAO.queryByStatus(status, user);
    }

    @Override
    public AccessoriesSale query(AccessoriesSale accessoriesSale, User user) {
        return accessoriesSaleDAO.query(accessoriesSale, user);
    }

    @Override
    public AccessoriesSale queryById(String id) {
        return accessoriesSaleDAO.queryById(id);
    }

    @Override
    public List<AccessoriesSale> queryByPager(Pager pager, User user) {
        return accessoriesSaleDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return accessoriesSaleDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return accessoriesSaleDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return accessoriesSaleDAO.active(id);
    }

    @Override
    public int queryByUserIdIsSameResult(String id, String userName, User user) {
        return accessoriesSaleDAO.queryByUserIdIsSameResult(id, userName, user);
    }

    @Override
    public List<AccessoriesSale> queryByName(Pager pager, String name, User user) {
        return accessoriesSaleDAO.queryByName(pager, name, user);
    }

    @Override
    public int byNameCount(User user) {
        return accessoriesSaleDAO.byNameCount(user);
    }

    @Override
    public List<AccessoriesSale> queryOnlySale(Pager pager, User user) {
        return accessoriesSaleDAO.queryOnlySale(pager, user);
    }

    @Override
    public int onlySaleCount(User user) {
        return accessoriesSaleDAO.onlySaleCount(user);
    }

    @Override
    public List<AccessoriesSale> queryBySaleTimeScopeByAccNamePager(Pager pager, String accName, String userName, String saleTimeStart, String saleTimeEnd, User user) {
        return accessoriesSaleDAO.queryBySaleTimeScopeByAccNamePager(pager, accName, userName, saleTimeStart, saleTimeEnd, user);
    }

    @Override
    public int bySaleTimeScopeCount(String accName, String userName, String saleTimeStart, String saleTimeEnd, User user) {
        return accessoriesSaleDAO.bySaleTimeScopeCount(accName, userName, saleTimeStart, saleTimeEnd, user);
    }
}