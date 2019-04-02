package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Accessories;
import com.gs.bean.MaterialReturn;
import com.gs.bean.User;
import com.gs.bean.info.MaterialReturnInfo;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.AccessoriesService;
import com.gs.service.MaterialReturnInfoService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xiao-Qiang on 2017/4/17.
 */
@Controller
@RequestMapping("materialReturn")
public class MaterialReturnController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MaterialReturn.class);

    @Resource
    private MaterialReturnInfoService mris;

    @Resource
    private AccessoriesService as;

    private String queryRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_REPERTORY + "," +
            Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.COMPANY_ARTIFICER;

    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_REPERTORY + "," + Constants.COMPANY_ARTIFICER;

    /**
     * 显示退料信息
     * @return
     */
    @RequestMapping(value = "info", method = RequestMethod.GET)
    private String showMaterialReturnInfo() {
        logger.info("显示退料信息");
        return "dispatchingPicking/material_return";
    }

    /**
     * 分页查询当前维修记录领料明细
     * @param pageNumber
     * @param pageSize
     * @param recordId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "query_pager", method = RequestMethod.GET)
    public Pager4EasyUI<MaterialReturnInfo> queryBySpeedStatus(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("recordId") String recordId) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        User user = SessionGetUtil.getUser();
        logger.info("分页查询当前维修记录领料明细");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(mris.countBySpeedStatus(recordId, user));
        List<MaterialReturnInfo> materialReturnInfos = mris.queryBySpeedStatus(pager, recordId, user);
        return new Pager4EasyUI<>(pager.getTotalRecords(), materialReturnInfos);
    }

    /**
     * 添加退料并增加配件库存
     * @param mrStr
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add_return", method = RequestMethod.GET)
    public ControllerResult addReturn(String[] mrStr) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole)) {
                logger.info("添加退料失败");
                return ControllerResult.getFailResult("添加退料失败，没有该权限操作");
            }
            logger.info("添加退料并增加配件库存");
            MaterialReturnInfo materialReturnInfo = new MaterialReturnInfo();
            materialReturnInfo.setRecordId(mrStr[0]);
            materialReturnInfo.setAccId(mrStr[1]);
            materialReturnInfo.setAccCount(Integer.valueOf(mrStr[2]));
            mris.insertReturn(materialReturnInfo);
            List<Accessories> accessories = new ArrayList<>();
            Accessories a = new Accessories();
            a.setAccId(mrStr[1]);
            Accessories a2 = as.queryByIdTotalAndIdle(a.getAccId());
            a.setAccTotal(a2.getAccTotal() + Integer.valueOf(mrStr[2]));
            a.setAccIdle(a2.getAccIdle() + Integer.valueOf(mrStr[2]));
            accessories.add(a);
            as.updateTotalAndIdle(accessories);
            return ControllerResult.getSuccessResult("添加成功!");
        } catch (Exception e) {
            logger.info("退料失败，出现了一个错误");
            return ControllerResult.getFailResult("退料失败，出现了一个错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "is_recordExist", method = RequestMethod.GET)
    public String isRecordExist(@Param("recordId") String recordId, @Param("accId") String accId) {
        int bear = mris.isRecordExist(recordId, accId);
        if (bear > 0) {
            return "0";
        }
        return "1";
    }

}
