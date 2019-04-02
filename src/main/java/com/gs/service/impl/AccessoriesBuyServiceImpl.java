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
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:53
 */
@Service
public class AccessoriesBuyServiceImpl implements AccessoriesBuyService {

    @Resource
    private AccessoriesBuyDAO accessoriesBuyDAO;
    @Override
    public int insert(AccessoriesBuy accessoriesBuy) {
        return accessoriesBuyDAO.insert(accessoriesBuy);
    }
    @Override
    public int batchInsert(List<AccessoriesBuy> list) {
        return accessoriesBuyDAO.batchInsert(list);
    }
    @Override
    public int delete(AccessoriesBuy accessoriesBuy) {
        return accessoriesBuyDAO.delete(accessoriesBuy);
    }
    @Override
    public int deleteById(String id) {
        return accessoriesBuyDAO.deleteById(id);
    }
    @Override
    public int batchDelete(List<AccessoriesBuy> list) {
        return accessoriesBuyDAO.batchDelete(list);
    }
    @Override
    public int update(AccessoriesBuy accessoriesBuy) {
        return accessoriesBuyDAO.update(accessoriesBuy);
    }
    @Override
    public int batchUpdate(List<AccessoriesBuy> list) {
        return accessoriesBuyDAO.batchUpdate(list);
    }
    @Override
    public List<AccessoriesBuy> queryAll(User user) {
        return accessoriesBuyDAO.queryAll(user);
    }
    @Override
    public List<AccessoriesBuy> queryByStatus(String status, User user) {
        return accessoriesBuyDAO.queryByStatus(status, user);
    }
    @Override
    public AccessoriesBuy query(AccessoriesBuy accessoriesBuy, User user) {
        return accessoriesBuyDAO.query(accessoriesBuy, user);
    }
    @Override
    public AccessoriesBuy queryById(String id) {
        return accessoriesBuyDAO.queryById(id);
    }
    @Override
    public List<AccessoriesBuy> queryByPager(Pager pager, User user) {
        return accessoriesBuyDAO.queryByPager(pager, user);
    }
    @Override
    public int count(User user) {
        return accessoriesBuyDAO.count(user);
    }
    @Override
    public int inactive(String id) {
        return accessoriesBuyDAO.inactive(id);
    }
    @Override
    public int active(String id) {
        return accessoriesBuyDAO.active(id);
    }

    @Override
    public int countByBuyState(User user) {
        return accessoriesBuyDAO.countByBuyState(user);
    }

    @Override
    public int countByCheckState(User user) {
        return accessoriesBuyDAO.countByCheckState(user);
    }

    @Override
    public List<AccessoriesBuy> queryByBuyStatePager(Pager pager, User user) {
        return accessoriesBuyDAO.queryByBuyStatePager(pager, user);
    }

    @Override
    public List<AccessoriesBuy> queryByCheckStatePager(Pager pager, User user) {
        return accessoriesBuyDAO.queryByCheckStatePager(pager, user);
    }

    @Override
    public List<AccessoriesBuy> queryByBuyTimeScopeByAccNamePager(Pager pager, String accName, String buyTimeStart, String buyTimeEnd, User user) {
        return accessoriesBuyDAO.queryByBuyTimeScopeByAccNamePager(pager, accName, buyTimeStart, buyTimeEnd, user);
    }

    @Override
    public int countByBuyTimeScope(String accName, String buyTimeStart, String buyTimeEnd, User user) {
        return accessoriesBuyDAO.countByBuyTimeScope(accName, buyTimeStart, buyTimeEnd, user);
    }

    @Override
    public List<AccessoriesBuy> accIsBuy(Pager pager, User user) {
        return accessoriesBuyDAO.accIsBuy(pager, user);
    }

    @Override
    public int countAccIsBuy(User user) {
        return accessoriesBuyDAO.countAccIsBuy(user);
    }

    @Override
    public void updateAccIsBuy(String id) {
        accessoriesBuyDAO.updateAccIsBuy(id);
    }

    @Override
    public void updateAccBuyCheck(String checkStatus, String accBuyId) {
        accessoriesBuyDAO.updateAccBuyCheck(checkStatus, accBuyId);
    }

    @Override
    public void batchUpdateBuyCheck(String[] ids) {
        accessoriesBuyDAO.batchUpdateBuyCheck(ids);
    }

    @Override
    public int dataPrimary(String name) {
        return accessoriesBuyDAO.dataPrimary(name);
    }

    @Override
    public List<AccessoriesBuy> queryByDefaultCount(String companyId) {
        return accessoriesBuyDAO.queryByDefaultCount(companyId);
    }

    @Override
    public List<AccessoriesBuy> queryByConditionCount(String startTime, String endTime, String type, String companyId) {
        return accessoriesBuyDAO.queryByConditionCount(startTime, endTime, type, companyId);
    }

    @Override
    public List<AccessoriesBuy> queryByDefaultPay(String companyId) {
        return accessoriesBuyDAO.queryByDefaultPay(companyId);
    }

    @Override
    public List<AccessoriesBuy> queryByConditionPay(String startTime, String endTime, String type, String companyId) {
        return accessoriesBuyDAO.queryByConditionPay(startTime, endTime, type, companyId);
    }


}