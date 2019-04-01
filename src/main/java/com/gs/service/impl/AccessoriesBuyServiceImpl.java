package com.gs.service.impl;

import com.gs.bean.Accessories;
import com.gs.bean.AccessoriesBuy;
import com.gs.bean.User;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.dao.AccessoriesBuyDAO;
import com.gs.service.AccessoriesBuyService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:58:53
 */
@Service
public class AccessoriesBuyServiceImpl implements AccessoriesBuyService {

    @Resource
    private AccessoriesBuyDAO accessoriesBuyDAO;

    public int insert(AccessoriesBuy accessoriesBuy) {
        return accessoriesBuyDAO.insert(accessoriesBuy);
    }

    public int batchInsert(List<AccessoriesBuy> list) {
        return accessoriesBuyDAO.batchInsert(list);
    }

    public int delete(AccessoriesBuy accessoriesBuy) {
        return accessoriesBuyDAO.delete(accessoriesBuy);
    }

    public int deleteById(String id) {
        return accessoriesBuyDAO.deleteById(id);
    }

    public int batchDelete(List<AccessoriesBuy> list) {
        return accessoriesBuyDAO.batchDelete(list);
    }

    public int update(AccessoriesBuy accessoriesBuy) {
        return accessoriesBuyDAO.update(accessoriesBuy);
    }

    public int batchUpdate(List<AccessoriesBuy> list) {
        return accessoriesBuyDAO.batchUpdate(list);
    }

    public List<AccessoriesBuy> queryAll(User user) {
        return accessoriesBuyDAO.queryAll(user);
    }

    public List<AccessoriesBuy> queryByStatus(String status, User user) {
        return accessoriesBuyDAO.queryByStatus(status, user);
    }

    public AccessoriesBuy query(AccessoriesBuy accessoriesBuy, User user) {
        return accessoriesBuyDAO.query(accessoriesBuy, user);
    }

    public AccessoriesBuy queryById(String id) {
        return accessoriesBuyDAO.queryById(id);
    }

    public List<AccessoriesBuy> queryByPager(Pager pager, User user) {
        return accessoriesBuyDAO.queryByPager(pager, user);
    }

    public int count(User user) {
        return accessoriesBuyDAO.count(user);
    }

    public int inactive(String id) {
        return accessoriesBuyDAO.inactive(id);
    }

    public int active(String id) {
        return accessoriesBuyDAO.active(id);
    }


    public int countByBuyState(User user) {
        return accessoriesBuyDAO.countByBuyState(user);
    }

    public int countByCheckState(User user) {
        return accessoriesBuyDAO.countByCheckState(user);
    }

    public List<AccessoriesBuy> queryByBuyStatePager(Pager pager, User user) {
        return accessoriesBuyDAO.queryByBuyStatePager(pager, user);
    }

    public List<AccessoriesBuy> queryByCheckStatePager(Pager pager, User user) {
        return accessoriesBuyDAO.queryByCheckStatePager(pager, user);
    }

    public List<AccessoriesBuy> queryByBuyTimeScopeByAccNamePager(Pager pager, String accName, String buyTimeStart, String buyTimeEnd, User user) {
        return accessoriesBuyDAO.queryByBuyTimeScopeByAccNamePager(pager, accName, buyTimeStart, buyTimeEnd, user);
    }

    public int countByBuyTimeScope(String accName, String buyTimeStart, String buyTimeEnd, User user) {
        return accessoriesBuyDAO.countByBuyTimeScope(accName, buyTimeStart, buyTimeEnd, user);
    }

    public List<AccessoriesBuy> accIsBuy(Pager pager, User user) {
        return accessoriesBuyDAO.accIsBuy(pager, user);
    }

    public int countAccIsBuy(User user) {
        return accessoriesBuyDAO.countAccIsBuy(user);
    }

    public void updateAccIsBuy(String id) {
        accessoriesBuyDAO.updateAccIsBuy(id);
    }

    public void updateAccBuyCheck(String checkStatus, String accBuyId) {
        accessoriesBuyDAO.updateAccBuyCheck(checkStatus, accBuyId);
    }

    public void batchUpdateBuyCheck(String[] ids) {
        accessoriesBuyDAO.batchUpdateBuyCheck(ids);
    }

    public int dataPrimary(String name) {
        return accessoriesBuyDAO.dataPrimary(name);
    }

    public List<AccessoriesBuy> queryByDefaultCount(String companyId) {
        return accessoriesBuyDAO.queryByDefaultCount(companyId);
    }

    public List<AccessoriesBuy> queryByConditionCount(String startTime, String endTime, String type, String companyId) {
        return accessoriesBuyDAO.queryByConditionCount(startTime, endTime, type, companyId);
    }

    public List<AccessoriesBuy> queryByDefaultPay(String companyId) {
        return accessoriesBuyDAO.queryByDefaultPay(companyId);
    }

    public List<AccessoriesBuy> queryByConditionPay(String startTime, String endTime, String type, String companyId) {
        return accessoriesBuyDAO.queryByConditionPay(startTime, endTime, type, companyId);
    }


}