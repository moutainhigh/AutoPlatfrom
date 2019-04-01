package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.MaintainRecordService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 温鑫
 * 维修保养进度管理
 * Created by Star on 2017/4/26.
 */
@Controller
@RequestMapping("progress")
public class ProgressController {


    @Resource
    private MaintainRecordService maintainRecordService;

    private String queryRole = Constants.COMPANY_RECEIVE + "," + Constants.COMPANY_ADMIN;

    private String queryRole2 = Constants.CAR_OWNER;

    private String queryRole3 = Constants.COMPANY_ARTIFICER;

    private Logger logger = (Logger) LoggerFactory.getLogger(ProgressController.class);

    @RequestMapping(value = "progress_page", method = RequestMethod.GET)
    public String progressInfo() {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(queryRole) || CheckRoleUtil.checkRoles(queryRole2) || CheckRoleUtil.checkRoles(queryRole3)) {
                logger.info(" 维修保养进度页面");
                return "maintenanceProgress/car_maintenance_progress";
            }
            return "error/notPermission";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    @ResponseBody
    @RequestMapping(value="progress_pager",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,HttpSession session){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询进度");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(maintainRecordService.countByProgressPager(user));
                    List<MaintainRecord> maintainRecords = maintainRecordService.queryByProgressPager(pager, user);
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecords);
                } else if (CheckRoleUtil.checkRoles(queryRole2)){
                    logger.info("车主查询自己进度");
                    User user = (User)session.getAttribute("user");
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(maintainRecordService.countByUser(user));
                    List<MaintainRecord> maintainRecords = maintainRecordService.queryByUser(pager,user);
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecords);
                } else if (CheckRoleUtil.checkRoles(queryRole3)){
                    logger.info("技师查询自己管理进度");
                    User user = (User)session.getAttribute("user");
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(maintainRecordService.countByEmp(user));
                    List<MaintainRecord> maintainRecords = maintainRecordService.queryByEmp(pager,user);
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecords);
                }
                return null;
            } catch (Exception e) {
                logger.info("分页查询失败，出现了异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }


    @ResponseBody
    @RequestMapping(value="progress_pager_N",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPager_N(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,HttpSession session){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询不可用进度");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(maintainRecordService.countByProgressPager_N(user));
                    List<MaintainRecord> maintainRecords = maintainRecordService.queryByProgressPager_N(pager, user);
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecords);
                } else if (CheckRoleUtil.checkRoles(queryRole2)){
                    logger.info("车主查询自己不可用进度");
                    User user = (User)session.getAttribute("user");
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(maintainRecordService.countByUser_N(user));
                    List<MaintainRecord> maintainRecords = maintainRecordService.queryByUser_N(pager,user);
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecords);
                } else if (CheckRoleUtil.checkRoles(queryRole3)){
                    logger.info("技师查询自己管理不可用进度");
                    User user = (User)session.getAttribute("user");
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(maintainRecordService.countByEmp_N(user));
                    List<MaintainRecord> maintainRecords = maintainRecordService.queryByEmp_N(pager,user);
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecords);
                }
                return null;
            } catch (Exception e) {
                logger.info("分页查询失败，出现了异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }




    @ResponseBody
    @RequestMapping(value="progress_pager_Y",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPager_Y(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,HttpSession session){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询可用进度");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(maintainRecordService.countByProgressPager_Y(user));
                    List<MaintainRecord> maintainRecords = maintainRecordService.queryByProgressPager_Y(pager, user);
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecords);
                } else if (CheckRoleUtil.checkRoles(queryRole2)){
                    logger.info("车主查询自己可用进度");
                    User user = (User)session.getAttribute("user");
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(maintainRecordService.countByUser_Y(user));
                    List<MaintainRecord> maintainRecords = maintainRecordService.queryByUser_Y(pager,user);
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecords);
                } else if (CheckRoleUtil.checkRoles(queryRole3)){
                    logger.info("技师查询自己管理可用进度");
                    User user = (User)session.getAttribute("user");
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(maintainRecordService.countByEmp_Y(user));
                    List<MaintainRecord> maintainRecords = maintainRecordService.queryByEmp_Y(pager,user);
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecords);
                }
                return null;
            } catch (Exception e) {
                logger.info("分页查询失败，出现了异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }
}

