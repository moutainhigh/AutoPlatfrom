package com.gs.service.impl;

import com.gs.bean.MaterialList;
import com.gs.bean.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.dao.MaterialListDAO;
import com.gs.service.MaterialListService;
import com.gs.common.bean.Pager;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:58:54
 */
@Service
public class MaterialListServiceImpl implements MaterialListService {

    @Resource
    private MaterialListDAO materialListDAO;

    @Override
    public int insert(MaterialList materialList) {
        return materialListDAO.insert(materialList);
    }

    @Override
    public int batchInsert(List<MaterialList> list) {
        return materialListDAO.batchInsert(list);
    }

    @Override
    public int delete(MaterialList materialList) {
        return materialListDAO.delete(materialList);
    }

    @Override
    public int deleteById(String id) {
        return materialListDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<MaterialList> list) {
        return materialListDAO.batchDelete(list);
    }

    @Override
    public int update(MaterialList materialList) {
        return materialListDAO.update(materialList);
    }

    @Override
    public int batchUpdate(List<MaterialList> list) {
        return materialListDAO.batchUpdate(list);
    }

    @Override
    public List<MaterialList> queryAll(User user) {
        return materialListDAO.queryAll(user);
    }

    @Override
    public List<MaterialList> queryByStatus(String status, User user) {
        return materialListDAO.queryByStatus(status, user);
    }

    @Override
    public MaterialList query(MaterialList materialList, User user) {
        return materialListDAO.query(materialList, user);
    }

    @Override
    public MaterialList queryById(String id) {
        return materialListDAO.queryById(id);
    }

    @Override
    public List<MaterialList> queryByPager(Pager pager, User user) {
        return materialListDAO.queryByPager(pager, user);
    }

    @Override
    public int count(User user) {
        return materialListDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return materialListDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return materialListDAO.active(id);
    }

}