package com.gs.service.impl;

import com.gs.bean.User;
import com.gs.bean.info.MaterialReturnInfo;
import com.gs.common.bean.Pager;
import com.gs.dao.MaterialReturnInfoDAO;
import com.gs.service.MaterialReturnInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Xiao-Qiang on 2017/5/16.
 */
@Service
public class MaterialReturnInfoServiceImpl implements MaterialReturnInfoService {

    @Resource
    private MaterialReturnInfoDAO mrid;

    @Override
    public void insertReturn(MaterialReturnInfo materialReturnInfo) {
        mrid.insertReturn(materialReturnInfo);
    }

    @Override
    public int isRecordExist(String recordId, String accId) {
        return mrid.isRecordExist(recordId, accId);
    }

    @Override
    public List<MaterialReturnInfo> queryBySpeedStatus(Pager pager, String recordId, User user) {
        return mrid.queryBySpeedStatus(pager, recordId, user);
    }

    @Override
    public int countBySpeedStatus(String recordId, User user) {
        return mrid.countBySpeedStatus(recordId, user);
    }
}
