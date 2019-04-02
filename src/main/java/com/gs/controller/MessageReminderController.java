package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Checkin;
import com.gs.bean.MaintainRecord;
import com.gs.bean.MaintainRemind;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.MaintainRemindService;
import org.apache.ibatis.annotations.Param;
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
 * Created by JangoGuo on 2017/4/18.
 */
@Controller
@RequestMapping("/MessageReminder")
public class MessageReminderController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MessageReminderController.class);


    @Resource
    private MaintainRemindService maintainRemindService;

    private String queryRole = Constants.COMPANY_ADMIN + ","+ Constants.COMPANY_RECEIVE+ ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;;
    private String editRole = Constants.COMPANY_ADMIN + ","+ Constants.COMPANY_RECEIVE;

    /**
     * 显示维修保养提醒页面
     * @return
     */
    @RequestMapping(value = "show_MessageReminder", method = RequestMethod.GET)
    public String messageReminder() {
        if (!SessionGetUtil.isUser()) {
            logger.info("登陆已失效，请重新登入");
            return "index/notLogin";
        } if (!CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("无权访问，想要访问请联系管理员!");
            return "error/notPermission";
        }
        logger.info("显示维修保养提醒页面");
        return "customer/maintenance_reminder";
    }

    /**
     * 分页查询所有提醒
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value="query_pager",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainRemind> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询所有提醒");
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("登陆已失效，请重新登入");
            return null;
        }
        User loginUser = SessionGetUtil.getUser();
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(maintainRemindService.count(loginUser));
        List<MaintainRemind> maintainRemindList = maintainRemindService.queryByPager(pager,loginUser);
        return new Pager4EasyUI<>(pager.getTotalRecords(), maintainRemindList);
    }

    /**
     * 更新维修保养提醒
     * @param maintainRemind
     * @return
     */
    @ResponseBody
    @RequestMapping(value="edit", method=RequestMethod.POST)
    public ControllerResult editMainteranceRecord(MaintainRemind maintainRemind) {
        logger.info("更新维修保养提醒");
        if (!SessionGetUtil.isUser()) {
            logger.info("登陆已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        if (!CheckRoleUtil.checkRoles(editRole)) {
            logger.info("更新失败");
            return ControllerResult.getFailResult("更新失败，没有该权限操作");
        }
        try {
            maintainRemindService.update(maintainRemind);
            return ControllerResult.getSuccessResult("更新成功");
        } catch (Exception e) {
            logger.info("更新失败，出现了一个错误");
            return ControllerResult.getFailResult("更新失败，出现了一个错误");
        }
    }

    /**
     * 根据条件分页查询维修保养记录提醒
     * @param pageNumber
     * @param pageSize
     * @param userName
     * @param searchRemindType
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "condition_pager", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainRemind> queryPagerByCondition(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,
                                                              @Param("userName")String userName, @Param("searchRemindType")String searchRemindType
                                                              ) {
        if (SessionGetUtil.isUser()  && CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("根据条件分页查询维修保养记录提醒");
            MaintainRemind remind = new MaintainRemind();
            Checkin checkin = new Checkin();
            checkin.setUserName(userName);
            remind.setCheckin(checkin);
            remind.setRemindType(searchRemindType);
            Pager pager = new Pager();
            pager.setPageNo(Integer.valueOf(pageNumber));
            pager.setPageSize(Integer.valueOf(pageSize));
            List<MaintainRemind> reminds = new ArrayList<>();
            User loginUser = SessionGetUtil.getUser();
            pager.setTotalRecords(maintainRemindService.countByCondition(remind,loginUser));
            reminds = maintainRemindService.queryPagerByCondition(pager, remind,loginUser);

            return new Pager4EasyUI<>(pager.getTotalRecords(), reminds);
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }




    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


}

































