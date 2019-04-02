package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
import com.gs.bean.WorkInfo;
import com.gs.common.Constants;
import com.gs.common.bean.*;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.MaintainRecordService;
import com.gs.service.UserService;
import com.gs.service.WorkInfoService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.session.Session;
import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 温鑫
 * Created by Star on 2017/4/21.
 */
@Controller
@RequestMapping("peopleManage")
public class WorkInfoController {

    private Logger logger = (Logger) LoggerFactory.getLogger(WorkInfoController.class);

    @Resource
    private WorkInfoService workInfoService;

    @Resource
    private MaintainRecordService maintainRecordService;

    private String queryRole = Constants.COMPANY_ADMIN  + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.COMPANY_RECEIVE + "," + Constants.COMPANY_ARTIFICER;;

    private String queryRole2 = Constants.COMPANY_ARTIFICER;

    /**
     * 可以修改的角色：董事长、技师、接待员
     */
    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_RECEIVE + "," + Constants.COMPANY_ARTIFICER;

    /**
     * 可以查看的角色：董事长、财务员、超级管理员、普通管理员
     */
    private String queryRole1 = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_ACCOUNTING + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;

    /**
     * 工单显示
     * @return
     */
    @RequestMapping(value = "work", method = RequestMethod.GET)
    private String workInfo() {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(queryRole) || CheckRoleUtil.checkRoles(queryRole2)) {
                logger.info(" 工单显示");
                return "peopleManage/work";
            }
            return "error/notPermission";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    /**
     * 分页查询所有工单
     * @param pageNumber
     * @param pageSize
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "workInfo_pager", method= RequestMethod.GET)
    public Pager4EasyUI<WorkInfo> infoPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,HttpSession session){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询所有工单");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(workInfoService.count(user));
                    List<WorkInfo> workInfo = workInfoService.queryByPager(pager, user);
                    return new Pager4EasyUI<>(pager.getTotalRecords(), workInfo);
                } else if (CheckRoleUtil.checkRoles(queryRole2)){
                    logger.info("技师查询自己的工单");
                    User user = (User)session.getAttribute("user");
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(workInfoService.countWorkUserId(user.getUserId()));
                    List<WorkInfo> workInfos = workInfoService.queryWorkUserId(pager,user.getUserId());
                    return new Pager4EasyUI<>(pager.getTotalRecords(), workInfos);
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

    /**
     * 分页查询所有可用工单
     * @param pageNumber
     * @param pageSize
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "workInfo_Status_Y", method= RequestMethod.GET)
    public Pager4EasyUI<WorkInfo> infoPagerY(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,HttpSession session){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询所有可用工单");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(workInfoService.count_Y(user));
                    List<WorkInfo> workInfo = workInfoService.queryByPager_Y(pager, user);
                    return new Pager4EasyUI<>(pager.getTotalRecords(), workInfo);
                } else if (CheckRoleUtil.checkRoles(queryRole2)){
                    logger.info("技师查询自己可用的工单");
                    User user = (User)session.getAttribute("user");
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(workInfoService.countWorkUserId_Y(user.getUserId()));
                    List<WorkInfo> workInfos = workInfoService.queryWorkUserId_Y(pager,user.getUserId());
                    return new Pager4EasyUI<>(pager.getTotalRecords(), workInfos);
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

    /**
     * 分页查询所有不可用工单
     * @param pageNumber
     * @param pageSize
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "workInfo_Status_N", method= RequestMethod.GET)
    public Pager4EasyUI<WorkInfo> infoPagerN(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,HttpSession session){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询所有不可用工单");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(workInfoService.count_N(user));
                    List<WorkInfo> workInfo = workInfoService.queryByPager_N(pager, user);
                    return new Pager4EasyUI<>(pager.getTotalRecords(), workInfo);
                } else if (CheckRoleUtil.checkRoles(queryRole2)){
                    logger.info("技师查询自己不可用的工单");
                    User user = (User)session.getAttribute("user");
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(workInfoService.countWorkUserId_N(user.getUserId()));
                    List<WorkInfo> workInfos = workInfoService.queryWorkUserId_N(pager,user.getUserId());
                    return new Pager4EasyUI<>(pager.getTotalRecords(), workInfos);
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

    /**
     * 指派员工
     * @param workInfo
     * @param maintainRecord
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "workInfo_update", method = RequestMethod.POST)
    public ControllerResult infoUpdate(WorkInfo workInfo,MaintainRecord maintainRecord){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("指派员工");
                    workInfoService.update(workInfo);
                    maintainRecord.setSpeedStatus(Constants.MAINTAIN_FIX);
                    maintainRecordService.updateTime(maintainRecord);
                    return ControllerResult.getSuccessResult("员工指派成功");
                }
                return ControllerResult.getFailResult("指派员工失败，没有该权限操作");
            } catch (Exception e) {
                logger.info("指派员工失败，出现了异常");
                return ControllerResult.getFailResult("指派员工失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登录信息已失效，请重新登录");
        }
    }

    /**
     * 根据工单Id查询
     * @param workInfo
     */
    @ResponseBody
    @RequestMapping(value = "workInfo_byId", method = RequestMethod.GET)
    public void workById(WorkInfo workInfo) {
        try {
              workInfoService.queryById(workInfo.getWorkId());
        } catch (Exception e) {
            logger.info("根据工单Id查询失败，出现了一个错误");
        }
    }

    /**
     * 状态修改
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "workInfo_status", method = RequestMethod.GET)
    public ControllerResult infoStatus(@Param("id")String id, @Param("status")String status){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("状态修改");
                    if("Y".equals(status)){
                        workInfoService.inactive(id);
                    } else if ("N".equals(status)) {
                        workInfoService.active(id);
                    }
                    return ControllerResult.getSuccessResult("修改状态成功");
                }
                return ControllerResult.getFailResult("修改状态失败，没有权限操作");
            } catch (Exception e) {
                logger.info("修改状态失败，出现了一个错误");
                return ControllerResult.getFailResult("修改状态失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登录信息已失效，请重新登录");
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 默认查询本月工单报表显示
     * @param companyId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="query_default",method= RequestMethod.GET)
    public List<LineBasic> queryAll(@Param("companyId")String companyId){
       if(SessionGetUtil.isUser()) {
           if(CheckRoleUtil.checkRoles(queryRole1)) {
               logger.info("默认查询本月工单报表显示");
               List<LineBasic> lineBasics = new ArrayList<>();
               LineBasic lineBasic = new LineBasic();
               LineBasic lineBasic1 = new LineBasic();
               User user = SessionGetUtil.getUser();
               if(user.getCompanyId() != null && !"".equals(user.getCompanyId())){
                   companyId = user.getCompanyId();
               }
               lineBasic.setName("保养");
               dateDay("one", companyId);
               lineBasic.setData(HighchartsData.doubleDayOne);
               lineBasic1.setName("维修");
               dateDay("two", companyId);
               lineBasic1.setData(HighchartsData.doubleDayTwo);
               lineBasic.setCategories(HighchartsData.strDay);
               lineBasic1.setCategories(HighchartsData.strDay);
               lineBasics.add(lineBasic);
               lineBasics.add(lineBasic1);
               return lineBasics;
           }
           return null;
        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }

    }

    /**
     * 根据年，月，季度，周，日查询所有工单显示
     * @param start
     * @param end
     * @param type
     * @param companyId
     * @return
     */

    @ResponseBody
    @RequestMapping(value="query_condition",method= RequestMethod.GET)
    public List<LineBasic> queryCondition(@Param("start")String start,@Param("end")String end,
                                          @Param("type")String type,@Param("companyId")String companyId){
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRole1)) {
                logger.info("根据年，月，季度，周，日查询所有工单显示");
                List<LineBasic> lineBasics = new ArrayList<>();
                LineBasic lineBasic = new LineBasic();
                LineBasic lineBasic1 = new LineBasic();
                lineBasic.setName("保养");
                lineBasic1.setName("维修");
                User user = SessionGetUtil.getUser();
                if (user.getCompanyId() != null && !"".equals(user.getCompanyId())) {
                    companyId = user.getCompanyId();
                }
                if (start != null && !"".equals(start) && end != null && !"".equals(end) && type != null && !"".equals(type)) {
                    if ("year".equals(type)) {
                        HighchartsData.setStrYear(start, end);
                        dataCondition(start, end, "保养", type, "year", "one", companyId);
                        lineBasic.setData(HighchartsData.doubleYearOne);
                        dataCondition(start, end, "维修", type, "year", "two", companyId);
                        lineBasic1.setData(HighchartsData.doubleYearTwo);
                        lineBasic.setCategories(HighchartsData.strYear);
                        lineBasic1.setCategories(HighchartsData.strYear);
                    } else if ("quarter".equals(type)) {
                        dataCondition(start, end, "保养", type, "quarter", "one", companyId);
                        lineBasic.setData(HighchartsData.doubleQuarterOne);
                        dataCondition(start, end, "维修", type, "quarter", "two", companyId);
                        lineBasic1.setData(HighchartsData.doubleQuarterTwo);
                        lineBasic.setCategories(HighchartsData.strQuarter);
                        lineBasic1.setCategories(HighchartsData.strQuarter);
                    } else if ("month".equals(type)) {
                        dataCondition(start, end, "保养", type, "month", "one", companyId);
                        lineBasic.setData(HighchartsData.doubleMonthOne);
                        dataCondition(start, end, "维修", type, "month", "two", companyId);
                        lineBasic1.setData(HighchartsData.doubleMonthTwo);
                        lineBasic.setCategories(HighchartsData.strMonth);
                        lineBasic1.setCategories(HighchartsData.strMonth);
                    } else if ("week".equals(type)) {
                        HighchartsData.setStrWeek(start, end);
                        dataCondition(start, end, "保养", type, "week", "one", companyId);
                        lineBasic.setData(HighchartsData.doubleWeekOne);
                        dataCondition(start, end, "维修", type, "week", "two", companyId);
                        lineBasic1.setData(HighchartsData.doubleWeekTwo);
                        lineBasic.setCategories(HighchartsData.strWeek);
                        lineBasic1.setCategories(HighchartsData.strWeek);
                    } else if ("day".equals(type)) {
                        dataCondition(start, end, "保养", type, "day", "one", companyId);
                        lineBasic.setData(HighchartsData.doubleDayOne);
                        dataCondition(start, end, "维修", type, "day", "two", companyId);
                        lineBasic1.setData(HighchartsData.doubleDayTwo);
                        lineBasic.setCategories(HighchartsData.strDay);
                        lineBasic1.setCategories(HighchartsData.strDay);
                    }
                }
                lineBasics.add(lineBasic);
                lineBasics.add(lineBasic1);
                return lineBasics;
            }
            return null;
        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    /**
     * 默认查询本月的工单
     * @param type
     * @param companyId
     */
    public void dateDay(String type,String companyId){
        HighchartsData.doubleDayTwo = new double[31];
        HighchartsData.doubleDayOne = new double[31];
        List<WorkInfo> workInfos = null;
        if("one".equals(type)){
            workInfos = workInfoService.queryByDefault("保养",companyId);
        }else if("two".equals(type)){
            workInfos = workInfoService.queryByDefault("维修",companyId);
        }
        int i = 0;
        double[] doubles = new double[workInfos.size()];
        String[] strs = new String[workInfos.size()];
        for(WorkInfo io: workInfos) {
            doubles[i] = io.getCoont();
            strs[i] = HighchartsData.dateFormat(io.getWorkCreatedTime(),"day");
            i++;
        }
        for(int j = 0,len = HighchartsData.strDay.length; j <len ; j++){
            for(int k = 0; k < strs.length; k++){
                if(HighchartsData.strDay[j].equals(strs[k])){
                    if("two".equals(type)){
                        HighchartsData.doubleDayTwo[j] = doubles[k];
                    }else if("one".equals(type)){
                        HighchartsData.doubleDayOne[j] = doubles[k];
                    }

                }
            }
        }


    }

    /**
     * 按年，季度，月，周，日，查询 工单
     * @param start
     * @param end
     * @param maintainOrFix
     * @param type
     * @param date
     * @param species
     * @param companyId
     */
    public void dataCondition(String start,String end,String maintainOrFix,String type,String date,String species,String companyId){
        HighchartsData.doubleDayTwo = new double[31];
        HighchartsData.doubleDayOne = new double[31];
        HighchartsData. doubleMonthTwo = new double[12];
        HighchartsData.doubleMonthOne = new double[12];
        HighchartsData.doubleQuarterTwo = new double[4];
        HighchartsData.doubleQuarterOne = new double[4];
        HighchartsData.doubleYearTwo = new double[HighchartsData.yearLen];
        HighchartsData.doubleYearOne = new double[HighchartsData.yearLen];
        HighchartsData.doubleWeekTwo = new double[HighchartsData.weekLen];
        HighchartsData.doubleWeekOne = new double[HighchartsData.weekLen];
        List<WorkInfo> workInfos = workInfoService.queryByCondition(start,end,maintainOrFix,type,companyId);;
        int i = 0;
        double[] doubles = new double[workInfos.size()];
        String[] strs = new String[workInfos.size()];
        HighchartsData.len = 0;
        for(WorkInfo io: workInfos) {
            doubles[i] = io.getCoont();
            if("month".equals(date)) {
                strs[i] = HighchartsData.dateFormat(io.getWorkCreatedTime(), "month");
                HighchartsData.len = HighchartsData.strMonth.length;
            }else if("day".equals(date)){
                strs[i] = HighchartsData.dateFormat(io.getWorkCreatedTime(), "day");
                HighchartsData.len = HighchartsData.strDay.length;
            }else if("quarter".equals(date)){
                strs[i] = HighchartsData.dateFormat(io.getWorkCreatedTime(), "quarter");
                HighchartsData.len = HighchartsData.strQuarter.length;
            }else if("year".equals(date)){
                strs[i] = HighchartsData.dateFormat(io.getWorkCreatedTime(),"year");
                HighchartsData.len = HighchartsData.strYear.length;
            }else if("week".equals(date)){
                strs[i] = "第"+String.valueOf(HighchartsData.getWeek(HighchartsData.dateFormat(io.getWorkCreatedTime())))+"周";
                HighchartsData.len = HighchartsData.strWeek.length;
            }
            i++;
        }
        if("quarter".equals(date)) {
            HighchartsData.getQuarter(strs,doubles,species);
        }else if("month".equals(date)){
            HighchartsData.getMonth(strs,doubles,species);
        }else if("day".equals(date)){
            HighchartsData.getDay(strs,doubles,species);
        }else if("year".equals(date)){
            HighchartsData. getYear(strs,doubles,species);
        }else if("week".equals(date)){
            HighchartsData.getWeek(strs,doubles,species);
        }
    }
}
