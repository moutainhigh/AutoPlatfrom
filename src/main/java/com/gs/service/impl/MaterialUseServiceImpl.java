package com.gs.service.impl;

import com.gs.bean.MaterialUse;
import com.gs.bean.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.MaterialUseDAO;
import com.gs.service.MaterialUseService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class MaterialUseServiceImpl implements MaterialUseService {

    @Resource
    private MaterialUseDAO materialUseDAO;

    @Override
    public int insert(MaterialUse materialUse) {
        return materialUseDAO.insert(materialUse);
    }

    @Override
    public int batchInsert(List<MaterialUse> list) {
        return materialUseDAO.batchInsert(list);
    }

    @Override
    public int delete(MaterialUse materialUse) {
        return materialUseDAO.delete(materialUse);
    }

    @Override
    public int deleteById(String id) {
        return materialUseDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<MaterialUse> list) {
        return materialUseDAO.batchDelete(list);
    }

    @Override
    public int update(MaterialUse materialUse) {
        return materialUseDAO.update(materialUse);
    }

    @Override
    public int batchUpdate(List<MaterialUse> list) {
        return materialUseDAO.batchUpdate(list);
    }

    @Override
    public List<MaterialUse> queryAll(User user) {
        return materialUseDAO.queryAll(user);
    }

    @Override
    public List<MaterialUse> queryByStatus(String status, User user) {
        return materialUseDAO.queryByStatus(status, user);
    }

    @Override
    public MaterialUse query(MaterialUse materialUse, User user) {
        return materialUseDAO.query(materialUse, user);
    }

    @Override
    public MaterialUse queryById(String id) {
        return materialUseDAO.queryById(id);
    }

    @Override
    public List<MaterialUse> queryByPager(Pager pager, User user) {
        return materialUseDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return materialUseDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return materialUseDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return materialUseDAO.active(id);
    }

    @Override
    public List<MaterialUse> queryByConditionUse(String startTime, String endTime, String type, String companyId, String accTypeId) {
        return materialUseDAO.queryByConditionUse(startTime, endTime, type, companyId, accTypeId);
    }
}