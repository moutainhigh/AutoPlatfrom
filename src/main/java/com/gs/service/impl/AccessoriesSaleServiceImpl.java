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
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:58:53
 */
@Service
public class AccessoriesSaleServiceImpl implements AccessoriesSaleService {

    @Resource
    private AccessoriesSaleDAO accessoriesSaleDAO;

    public int insert(AccessoriesSale accessoriesSale) {
        return accessoriesSaleDAO.insert(accessoriesSale);
    }

    public int batchInsert(List<AccessoriesSale> list) {
        return accessoriesSaleDAO.batchInsert(list);
    }

    public int delete(AccessoriesSale accessoriesSale) {
        return accessoriesSaleDAO.delete(accessoriesSale);
    }

    public int deleteById(String id) {
        return accessoriesSaleDAO.deleteById(id);
    }

    public int batchDelete(List<AccessoriesSale> list) {
        return accessoriesSaleDAO.batchDelete(list);
    }

    public int update(AccessoriesSale accessoriesSale) {
        return accessoriesSaleDAO.update(accessoriesSale);
    }

    public int batchUpdate(List<AccessoriesSale> list) {
        return accessoriesSaleDAO.batchUpdate(list);
    }

    public List<AccessoriesSale> queryAll(User user) {
        return accessoriesSaleDAO.queryAll(user);
    }

    public List<AccessoriesSale> queryByStatus(String status, User user) {
        return accessoriesSaleDAO.queryByStatus(status, user);
    }

    public AccessoriesSale query(AccessoriesSale accessoriesSale, User user) {
        return accessoriesSaleDAO.query(accessoriesSale, user);
    }

    public AccessoriesSale queryById(String id) {
        return accessoriesSaleDAO.queryById(id);
    }

    public List<AccessoriesSale> queryByPager(Pager pager, User user) {
        return accessoriesSaleDAO.queryByPager(pager, user);
    }

    public int count(User user) {
        return accessoriesSaleDAO.count(user);
    }

    public int inactive(String id) {
        return accessoriesSaleDAO.inactive(id);
    }

    public int active(String id) {
        return accessoriesSaleDAO.active(id);
    }

    public int queryByUserIdIsSameResult(String id, String userName, User user) {
        return accessoriesSaleDAO.queryByUserIdIsSameResult(id, userName, user);
    }

    public List<AccessoriesSale> queryByName(Pager pager, String name, User user) {
        return accessoriesSaleDAO.queryByName(pager, name, user);
    }

    public int byNameCount(User user) {
        return accessoriesSaleDAO.byNameCount(user);
    }

    public List<AccessoriesSale> queryOnlySale(Pager pager, User user) {
        return accessoriesSaleDAO.queryOnlySale(pager, user);
    }

    public int onlySaleCount(User user) {
        return accessoriesSaleDAO.onlySaleCount(user);
    }

    public List<AccessoriesSale> queryBySaleTimeScopeByAccNamePager(Pager pager, String accName, String userName, String saleTimeStart, String saleTimeEnd, User user) {
        return accessoriesSaleDAO.queryBySaleTimeScopeByAccNamePager(pager, accName, userName, saleTimeStart, saleTimeEnd, user);
    }

    public int bySaleTimeScopeCount(String accName, String userName, String saleTimeStart, String saleTimeEnd, User user) {
        return accessoriesSaleDAO.bySaleTimeScopeCount(accName, userName, saleTimeStart, saleTimeEnd, user);
    }
}