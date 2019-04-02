package com.gs.controller;

import com.gs.bean.*;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.*;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-04-24. 维修保养明细控制器
 */
@Controller
@RequestMapping("detail")
public class MaintainDetailController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MaintainDetailController.class);

    @Resource
    private MaintainDetailService detailService;

    @Resource
    private MaintainFixAccService maintainFixAccService;

    @Resource
    private MaterialListService materialListService;

    @Resource
    private WorkInfoService workInfoService;

    @Resource
    private MaintainRecordService maintainRecordService;

    /**
     * 可以查看的角色：董事长、接待员、普通管理员、超级管理员、技师
     */
    private String queryRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_RECEIVE + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.COMPANY_ARTIFICER + "," + Constants.CAR_OWNER;

    /**
     * 可以操作的角色：董事长、技师、接待员、车主用于用户前置
     */
    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_ARTIFICER + "," + Constants.CAR_OWNER + "," + Constants.COMPANY_RECEIVE ;

    /**
     * 添加维修保养明细
     * @param detail
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult addMaintainDetail(MaintainDetail detail) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("添加维修保养明细");
                    detailService.insert(detail);
                    return ControllerResult.getSuccessResult("添加成功");
                }
                logger.info("添加维修保养明细失败，没有权限操作");
                return ControllerResult.getFailResult("添加维修保养明细失败，没有权限操作");
            } catch (Exception e) {
                logger.info("添加维修保养明细失败，出现了一个异常");
                return ControllerResult.getFailResult("添加维修保养明细失败，请稍后再试");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    /**
     * 分页查询指定维修保养记录的所有明细
     * @param pageNumber
     * @param pageSize
     * @param recordId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="pager",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainDetail> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, @Param("recordId") String recordId){
        if (SessionGetUtil.isUser() && CheckRoleUtil.checkRoles(queryRole)) {
            try {
                logger.info("分页查询指定维修保养记录的所有明细");
                User user = SessionGetUtil.getUser();
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setTotalRecords(detailService.countByRecordId(recordId, user));
                List<MaintainDetail> maintainDetails = detailService.queryPagerByRecordId(pager, recordId, user);
                return new Pager4EasyUI<MaintainDetail>(pager.getTotalRecords(), maintainDetails);
            } catch (Exception e) {
                logger.info("分页查询指定维修保养记录的所有明细失败，出现了一个异常");
                return null;
            }
        } else {
            logger.info("Session已失效或者没有权限，无法查看！");
            return null;
        }
    }

    /**
     * 修改维修保养明细记录
     * @param detail
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ControllerResult editMaintainDetail(MaintainDetail detail) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("修改维修保养明细记录");
                    detailService.update(detail);
                    return ControllerResult.getSuccessResult("修改成功");
                }
                logger.info("修改维修保养明细记录失败，没有权限操作");
                return ControllerResult.getFailResult("修改维修保养明细记录失败，没有权限操作");
            } catch (Exception e) {
                logger.info("修改维修保养明细记录失败，出现了一个异常");
                return ControllerResult.getFailResult("修改维修保养明细记录失败，出现了一个异常");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    /**
     * 用户签字确认
     * @param recordId
     * @param maintainIds
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "confirm", method = RequestMethod.GET)
    public ControllerResult userConfirm(@Param("recordId") String recordId, @Param("maintainIds") String maintainIds) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("用户签字确认");
                    String[] maintainIds1 = maintainIds.split(",");
                    List<MaintainFixAcc> maintainFixAccs = maintainFixAccService.queryAllByMaintainId(maintainIds1);
                    if (maintainFixAccs.size() <= 0) {
                        return ControllerResult.getFailResult("用户签字失败，该项目还没有对应的配件");
                    }
                    List<MaterialList> materialLists = new ArrayList<>();
                    for (MaintainFixAcc maintainFixAcc : maintainFixAccs) {
                        MaterialList materialList = new MaterialList();
                        materialList.setRecordId(recordId);
                        materialList.setAccId(maintainFixAcc.getAccId());
                        materialList.setMaterialCount(maintainFixAcc.getAccCount());
                        materialLists.add(materialList);
                    }
                    WorkInfo workInfo = new WorkInfo();
                    workInfo.setRecordId(recordId);

                    maintainRecordService.updateSign(recordId, "Y");
                    workInfoService.insert(workInfo);
                    materialListService.batchInsert(materialLists);
                    return ControllerResult.getSuccessResult("用户已经确认签字，工单信息和物料清单已经自动生成");
                }
                logger.info("用户确认失败，没有权限操作");
                return ControllerResult.getFailResult("用户确认失败，没有权限操作");
            } catch (Exception e) {
                logger.info("用户确认失败，出现了一个异常");
                return ControllerResult.getFailResult("用户确认失败，出现了一个异常");
            }

        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    /**
     * 查询配件详情
     * @param recordId
     * @param maintainId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "query_detail", method = RequestMethod.GET)
    public ControllerResult queryIsDetail(@Param("recordId") String recordId, @Param("maintainId") String maintainId) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    int flag = detailService.queryIsDetail(recordId, maintainId);
                    logger.info("根据记录id和项目id判断该记录是否已经存在该项目，返回1表示存在，返回0表示不存在。result=" + flag);
                    if (flag == 0) {
                        return ControllerResult.getSuccessResult("此维修保养记录不存在该项目，可正常添加");
                    } else {
                        return ControllerResult.getFailResult("此维修保养记录已经存在该项目，不能添加重复的项目");
                    }
                }
                logger.info("判断此维修保养记录是否已经存在该项目失败，没有权限操作");
                return ControllerResult.getFailResult("判断此维修保养记录是否已经存在该项目失败，没有权限操作");
            } catch (Exception e) {
                logger.info("判断此维修保养记录是否已经存在该项目失败，出现了一个异常");
                return ControllerResult.getFailResult("判断此维修保养记录是否已经存在该项目失败，出现了一个异常");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    /**
     * 查询配件
     * @param maintainIds
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "query_acc", method = RequestMethod.GET)
    public ControllerResult queryIsAcc(String maintainIds) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("根据项目id判断该项目有没有配件");
                    String[] maintainId = maintainIds.split(",");
                    List<MaintainFixAcc> maintainFixAccs = maintainFixAccService.queryAllByMaintainId(maintainId);
                    if (maintainFixAccs.size() <= 0) {
                        return ControllerResult.getFailResult("该项目还没有配件哦");
                    } else {
                        return ControllerResult.getSuccessResult("该项目已经有配件了");
                    }
                }
                logger.info("根据项目id判断该项目有没有配件失败，没有权限操作");
                return ControllerResult.getFailResult("根据项目id判断该项目有没有配件失败，没有权限操作");
            } catch (Exception e) {
                logger.info("根据项目id判断该项目有没有配件失败，出现了一个异常");
                return ControllerResult.getFailResult("根据项目id判断该项目有没有配件失败，出现了一个异常");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
