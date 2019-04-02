package com.gs.service.impl;

import com.gs.bean.info.MaterialListInfo;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.MaterialListInfoDAO;
import com.gs.service.MaterialListInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Xiao-Qiang on 2017/4/26.
 */
@Service
public class MaterialListInfoServiceImpl implements MaterialListInfoService {

    @Resource
    private MaterialListInfoDAO materialListInfoDAO;

    @Override
    public List<MaterialListInfo> queryByStatus(Pager pager, String status, User user) {
        return materialListInfoDAO.queryByStatus(pager, status, user);
    }

    @Override
    public int termCount(String userName, String startTime, String endTime, User user) {
        return materialListInfoDAO.termCount(userName, startTime, endTime, user);
    }

    @Override
    public List<MaterialListInfo> termQueryPager(Pager pager, String userName, String startTime, String endTime, User user) {
        return materialListInfoDAO.termQueryPager(pager, userName, startTime, endTime, user);
    }

    @Override
    public List<MaterialListInfo> queryBySpeedStatus(Pager pager, String recordId, User user) {
        return materialListInfoDAO.queryBySpeedStatus(pager, recordId, user);
    }

    @Override
    public int countBySpeedStatus(String recordId, User user) {
        return materialListInfoDAO.countBySpeedStatus(recordId, user);
    }

    @Override
    public List<MaterialListInfo> queryBySpeedStatusAndStatus(Pager pager, String recordId, String materialStatus, User user) {
        return materialListInfoDAO.queryBySpeedStatusAndStatus(pager, recordId, materialStatus, user);
    }

    @Override
    public int statusCount(String recordId, String materialStatus, User user) {
        return materialListInfoDAO.statusCount(recordId, materialStatus, user);
    }

    @Override
    public void updateCount(MaterialListInfo materialListInfo) {
        materialListInfoDAO.updateCount(materialListInfo);
    }

    @Override
    public int insert(MaterialListInfo materialListInfo) {
        return 0;
    }

    @Override
    public int batchInsert(List<MaterialListInfo> list) {
        return 0;
    }

    @Override
    public int delete(MaterialListInfo materialListInfo) {
        return 0;
    }

    @Override
    public int deleteById(String id) {
        return 0;
    }

    @Override
    public int batchDelete(List<MaterialListInfo> list) {
        return 0;
    }

    @Override
    public int update(MaterialListInfo materialListInfo) {
        return 0;
    }

    @Override
    public int batchUpdate(List<MaterialListInfo> list) {
        return 0;
    }

    @Override
    public List<MaterialListInfo> queryAll(User user) {
        return null;
    }

    @Override
    public List<MaterialListInfo> queryByStatus(String status, User user) {
        return null;
    }

    @Override
    public MaterialListInfo query(MaterialListInfo materialListInfo, User user) {
        return null;
    }

    @Override
    public MaterialListInfo queryById(String id) {
        return null;
    }

    @Override
    public List<MaterialListInfo> queryByPager(Pager pager, User user) {
        return null;
    }

    @Override
    public int count(User user) {
        return 0;
    }

    @Override
    public int inactive(String id) {
        return materialListInfoDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return materialListInfoDAO.active(id);
    }
}
