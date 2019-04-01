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

    public List<MaterialListInfo> queryByStatus(Pager pager, String status, User user) {
        return materialListInfoDAO.queryByStatus(pager,status,user);
    }

    public int termCount(String userName, String startTime, String endTime, User user) {
        return materialListInfoDAO.termCount(userName,startTime,endTime,user);
    }

    public List<MaterialListInfo> termQueryPager(Pager pager, String userName, String startTime, String endTime, User user) {
        return materialListInfoDAO.termQueryPager(pager,userName,startTime,endTime,user);
    }

    public List<MaterialListInfo> queryBySpeedStatus(Pager pager, String recordId, User user) {
        return materialListInfoDAO.queryBySpeedStatus(pager,recordId,user);
    }

    public int countBySpeedStatus(String recordId, User user) {
        return materialListInfoDAO.countBySpeedStatus(recordId,user);
    }

    public List<MaterialListInfo> queryBySpeedStatusAndStatus(Pager pager, String recordId, String materialStatus, User user) {
        return materialListInfoDAO.queryBySpeedStatusAndStatus(pager,recordId,materialStatus,user);
    }

    public int statusCount(String recordId, String materialStatus, User user) {
        return materialListInfoDAO.statusCount(recordId,materialStatus,user);
    }

    public void updateCount(MaterialListInfo materialListInfo) {
        materialListInfoDAO.updateCount(materialListInfo);
    }

    public int insert(MaterialListInfo materialListInfo) {
        return 0;
    }

    public int batchInsert(List<MaterialListInfo> list) {
        return 0;
    }

    public int delete(MaterialListInfo materialListInfo) {
        return 0;
    }

    public int deleteById(String id) {
        return 0;
    }

    public int batchDelete(List<MaterialListInfo> list) {
        return 0;
    }

    public int update(MaterialListInfo materialListInfo) {
        return 0;
    }

    public int batchUpdate(List<MaterialListInfo> list) {
        return 0;
    }

    public List<MaterialListInfo> queryAll(User user) {
        return null;
    }

    public List<MaterialListInfo> queryByStatus(String status, User user) {
        return null;
    }

    public MaterialListInfo query(MaterialListInfo materialListInfo, User user) {
        return null;
    }

    public MaterialListInfo queryById(String id) {
        return null;
    }

    public List<MaterialListInfo> queryByPager(Pager pager, User user) {
        return null;
    }

    public int count(User user) {
        return 0;
    }

    public int inactive(String id) {
        return materialListInfoDAO.inactive(id);
    }

    public int active(String id) {
        return materialListInfoDAO.active(id);
    }
}
