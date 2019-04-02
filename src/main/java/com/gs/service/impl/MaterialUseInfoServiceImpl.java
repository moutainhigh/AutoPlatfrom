package com.gs.service.impl;

import com.gs.bean.User;
import com.gs.bean.info.MaterialUseInfo;
import com.gs.common.bean.Pager;
import com.gs.dao.MaterialUseInfoDAO;
import com.gs.service.MaterialUseInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Xiao-Qiang on 2017/5/15.
 */
@Service
public class MaterialUseInfoServiceImpl implements MaterialUseInfoService {

    @Resource
    private MaterialUseInfoDAO muiDAO;

    @Override
    public List<MaterialUseInfo> queryAll(String recordId, User user) {
        return muiDAO.queryAll(recordId, user);
    }

    @Override
    public void addByRecordIdMu(List<MaterialUseInfo> muis) {
        muiDAO.addByRecordIdMu(muis);
    }

    @Override
    public List<MaterialUseInfo> queryBySpeedStatus(Pager pager, String recordId, User user) {
        return muiDAO.queryBySpeedStatus(pager, recordId, user);
    }

    @Override
    public int countBySpeedStatus(String recordId, User user) {
        return muiDAO.countBySpeedStatus(recordId, user);
    }

    @Override
    public MaterialUseInfo queryByIdAccCount(String materialUseId) {
        return muiDAO.queryByIdAccCount(materialUseId);
    }
}
