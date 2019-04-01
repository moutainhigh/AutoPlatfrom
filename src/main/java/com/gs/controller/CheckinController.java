package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.common.Constants;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.EncryptUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.*;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017-04-16.
 * <p>
 * 登记表对应的Controller
 */
@Controller
@RequestMapping("checkin")
public class CheckinController {

    private Logger logger = (Logger) LoggerFactory.getLogger(CheckinController.class);

    @Resource
    private CheckinService checkinService;

    @Resource
    private MaintainRecordService maintainRecordService;

    @Resource
    private AppointmentService appointmentService;

    // 可以看的角色：董事长、接待员、超级管理员、普通管理员
    private String queryRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_RECEIVE + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;

    // 可以操作的角色：董事长、接待员
    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_RECEIVE;

    @RequestMapping(value = "checkin_page", method = RequestMethod.GET)
    public String checkinPage() {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(queryRole)) {
                logger.info("访问登记页面");
                return "maintenanceReception/reception_register";
            }
            return "error/notPermission";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }

    }

    @RequestMapping(value = "my_checkin", method = RequestMethod.GET)
    public ModelAndView carOwerAppointment(){
        ModelAndView mav = new ModelAndView();
        User user = SessionGetUtil.getUser();
        if (!SessionGetUtil.isUser()){
            mav.setViewName("index/notLogin");
            return mav;
        }
        logger.info("车主用户查看我的接待");
        mav.setViewName("customerClient/checkin");
        mav.addObject("chs", checkinService.queryMyName(user));
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "checkin_pager", method = RequestMethod.GET)
    public Pager4EasyUI<Checkin> checkinPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("status") String status) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询登记记录");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<Checkin> checkins = new ArrayList<Checkin>();
                    if (status.equals("ALL")) {
                        pager.setTotalRecords(checkinService.count(user));
                        checkins = checkinService.queryByPager(pager, user);
                    } else {
                        pager.setTotalRecords(checkinService.countByStatus(status, user));
                        checkins = checkinService.queryPagerByStatus(pager, status, user);
                    }
                    return new Pager4EasyUI<Checkin>(pager.getTotalRecords(), checkins);
                }
                return null;
            } catch (Exception e) {
                logger.info("查询失败，出现了异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "condition_pager", method = RequestMethod.GET)
    public Pager4EasyUI<Checkin> queryPagerByCondition(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize,
                                                       @Param("userName") String userName, @Param("userPhone") String userPhone,
                                                       @Param("carPlate") String carPlate, @Param("maintainOrFix") String maintainOrFix,
                                                       @Param("companyId") String companyId) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("根据条件分页查询登记记录");
                    User user = SessionGetUtil.getUser();
                    Checkin checkin = new Checkin();
                    checkin.setUserName(userName);
                    checkin.setUserPhone(userPhone);
                    checkin.setCarPlate(carPlate);
                    checkin.setMaintainOrFix(maintainOrFix);
                    checkin.setCompanyId(companyId);
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<Checkin> checkins = new ArrayList<Checkin>();
                    pager.setTotalRecords(checkinService.countByCondition(checkin, user));
                    checkins = checkinService.queryPagerByCondition(pager, checkin, user);

                    return new Pager4EasyUI<Checkin>(pager.getTotalRecords(), checkins);
                }
                return null;
            } catch (Exception e) {
            logger.info("查询失败，出现了异常");
            return null;
        }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult addCheckin(@Param("checkin") Checkin checkin, @Param("isApp") boolean isApp) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    User loginUser = SessionGetUtil.getUser();
                    if (checkinService.queryByPhone(checkin.getUserPhone(), loginUser) == 0) {
                        logger.info("添加登记记录,自动生成" + checkin.getMaintainOrFix() + "记录");
                        String checkinId = UUIDUtil.uuid();
                        checkin.setCheckinId(checkinId);
                        checkin.setCompanyId(loginUser.getCompanyId());

                        MaintainRecord maintainRecord = new MaintainRecord();
                        String recordId = UUIDUtil.uuid();
                        maintainRecord.setRecordId(recordId);
                        maintainRecord.setCheckinId(checkinId);
                        maintainRecord.setCompanyId(loginUser.getCompanyId());

                        if (isApp) {
                            appointmentService.updateSpeedStatusById(Constants.CHECKIN, checkin.getAppointmentId());
                            appointmentService.inactive(checkin.getAppointmentId());
                        }

                        maintainRecordService.insert(maintainRecord);
                        checkinService.insert(checkin);
                        return ControllerResult.getSuccessResult("添加成功," + checkin.getMaintainOrFix() + "记录已经自动生成");
                    }
                    return ControllerResult.getFailResult("添加登记记录失败，已经存在该记录，请不要重复添加");
                }
                return ControllerResult.getFailResult("添加登记记录失败，没有该权限操作");
            } catch (Exception e) {
                logger.info("添加登记记录失败，出现了一个错误");
                return ControllerResult.getFailResult("添加登记记录失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ControllerResult editCheckin(Checkin checkin) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("修改登记记录");
                    checkinService.update(checkin);
                    return ControllerResult.getSuccessResult("修改成功");
                }
                return ControllerResult.getFailResult("修改失败，没有权限操作");
            } catch (Exception e) {
                logger.info("修改失败，出现了一个错误");
                return ControllerResult.getFailResult("修改失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value = "update_status", method = RequestMethod.GET)
    public ControllerResult updateCheckinStatus(String checkinId, String status) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("更新登记记录的状态");
                    if (status.equals("Y")) {
                        checkinService.inactive(checkinId);
                    } else {
                        checkinService.active(checkinId);
                    }
                    return ControllerResult.getSuccessResult("更新成功");
                }
                return ControllerResult.getFailResult("更新登记记录状态失败，没有权限操作");
            } catch (Exception e) {
                logger.info("更新登记记录状态失败，出现了一个错误");
                return ControllerResult.getFailResult("更新登记记录状态失败，出现了一个错误");
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
