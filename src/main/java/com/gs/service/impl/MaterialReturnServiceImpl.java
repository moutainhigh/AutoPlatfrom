package com.gs.service.impl;

import com.gs.bean.MaterialReturn;
import com.gs.bean.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.MaterialReturnDAO;
import com.gs.service.MaterialReturnService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class MaterialReturnServiceImpl implements MaterialReturnService {

    @Resource
    private MaterialReturnDAO materialReturnDAO;

    @Override
    public int insert(MaterialReturn materialReturn) {
        return materialReturnDAO.insert(materialReturn);
    }

    @Override
    public int batchInsert(List<MaterialReturn> list) {
        return materialReturnDAO.batchInsert(list);
    }

    @Override
    public int delete(MaterialReturn materialReturn) {
        return materialReturnDAO.delete(materialReturn);
    }

    @Override
    public int deleteById(String id) {
        return materialReturnDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<MaterialReturn> list) {
        return materialReturnDAO.batchDelete(list);
    }

    @Override
    public int update(MaterialReturn materialReturn) {
        return materialReturnDAO.update(materialReturn);
    }

    @Override
    public int batchUpdate(List<MaterialReturn> list) {
        return materialReturnDAO.batchUpdate(list);
    }

    @Override
    public List<MaterialReturn> queryAll(User user) {
        return materialReturnDAO.queryAll(user);
    }

    @Override
    public List<MaterialReturn> queryByStatus(String status, User user) {
        return materialReturnDAO.queryByStatus(status, user);
    }

    @Override
    public MaterialReturn query(MaterialReturn materialReturn, User user) {
        return materialReturnDAO.query(materialReturn, user);
    }

    @Override
    public MaterialReturn queryById(String id) {
        return materialReturnDAO.queryById(id);
    }

    @Override
    public List<MaterialReturn> queryByPager(Pager pager, User user) {
        return materialReturnDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return materialReturnDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return materialReturnDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return materialReturnDAO.active(id);
    }

    @Override
    public List<MaterialReturn> queryByConditionReturn(String startTime, String endTime, String type, String companyId, String accTypeId) {
        return materialReturnDAO.queryByConditionReturn(startTime, endTime, type, companyId, accTypeId);
    }
}