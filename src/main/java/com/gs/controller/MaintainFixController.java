package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.MaintainDetail;
import com.gs.bean.MaintainFix;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.bean.*;

import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.MaintainDetailService;
import com.gs.service.MaintainFixService;
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
 * Created by root on 2017/4/21.
 */
@Controller
@RequestMapping("/maintainFix")
public class MaintainFixController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MaintainFixController.class);

    @Resource
    private MaintainFixService maintainFixService;

    @Resource
    private MaintainDetailService maintainDetailService;

    private String queryRole  = Constants.COMPANY_ADMIN + ","
            + Constants.SYSTEM_SUPER_ADMIN + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + ","
            + Constants.COMPANY_HUMAN_MANAGER + ","
            + Constants.COMPANY_ACCOUNTING
            + Constants.COMPANY_EMP + ","
            + Constants.COMPANY_SALES + ","
            + Constants.COMPANY_RECEIVE + ","
            + Constants.COMPANY_ARTIFICER;

    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_ARTIFICER + "," + Constants.COMPANY_RECEIVE;

    // 可以查看的角色：董事长、财务员、超级管理员、普通管理员
    private String queryRole1 = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_ACCOUNTING + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;

    @ResponseBody
    @RequestMapping(value = "InsertMaintainItem", method = RequestMethod.POST)
    public ControllerResult InsertMaintainFix(MaintainFix maintainFix) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }else{
            try {
                if(CheckRoleUtil.checkRoles(editRole)){
                    User user = SessionGetUtil.getUser();
                    logger.info("添加维修项目");
                    maintainFix.setMaintainOrFix("维修");
                    maintainFix.setCompanyId(user.getCompanyId());
                    maintainFixService.insert(maintainFix);
                    return ControllerResult.getSuccessResult("添加成功");
                }else{
                    return ControllerResult.getFailResult("添加失败，你没有权限操作");
                }

            } catch (Exception e) {
                logger.info("添加失败，出现了一个错误");
                return ControllerResult.getFailResult("添加失败，出现了一个错误");
            }
        }

    }

    @ResponseBody
    @RequestMapping(value = "InsertMaintain", method = RequestMethod.POST)
    public ControllerResult InsertMaintain(MaintainFix maintainFix) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }else{
            try {
                if(CheckRoleUtil.checkRoles(editRole)){
                    User user = SessionGetUtil.getUser();
                    logger.info("添加保养项目");
                    maintainFix.setCompanyId(user.getCompanyId());
                    maintainFix.setMaintainOrFix("保养");
                    maintainFixService.insert(maintainFix);
                    return ControllerResult.getSuccessResult("添加成功");
                }else{
                    return ControllerResult.getFailResult("添加失败，你没有权限操作");
                }

            } catch (Exception e) {
                logger.info("添加失败，出现了一个错误");
                return ControllerResult.getFailResult("添加失败，出现了一个错误");
            }
        }

    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ControllerResult UpdateMaintainFix(MaintainFix maintainFix) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }else{
            try {
                if(CheckRoleUtil.checkRoles(editRole)){
                    logger.info("更新保养项目");
                    maintainFixService.update(maintainFix);
                    return ControllerResult.getSuccessResult("更新成功");
                }else{
                    return ControllerResult.getFailResult("更新失败，你没有权限操作");
                }

            } catch (Exception e) {
                logger.info("更新失败，出现了一个错误");
                return ControllerResult.getFailResult("更新失败，出现了一个错误");
            }
        }

    }

    @ResponseBody
    @RequestMapping(value = "StatusModify", method = RequestMethod.GET)
    public ControllerResult companyStatusModify(@Param("id") String id, @Param("status") String status) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }else{
            if(CheckRoleUtil.checkRoles(editRole)){
                if (status.equals("Y")) {
                    logger.info("冻结项目");
                    maintainFixService.inactive(id);
                } else if (status.equals("N")) {
                    logger.info("激活项目");
                    maintainFixService.active(id);
                }
                return ControllerResult.getSuccessResult("操作成功");
            }else{
                return ControllerResult.getFailResult("操作失败，你没有权限操作");
            }
        }

    }

    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainFix> queryByPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser()|| !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        Pager pager = new Pager();
        User user = SessionGetUtil.getUser();
        logger.info("分页查询所有维修项目");
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(maintainFixService.count(user));
        List<MaintainFix> maintainFixList = maintainFixService.queryByPager(pager,user);
        return new Pager4EasyUI<MaintainFix>(pager.getTotalRecords(), maintainFixList);
    }

    @ResponseBody
    @RequestMapping(value = "queryByMaintenanceItemPager", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainFix> queryByMaintenanceItemPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser()|| !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        Pager pager = new Pager();
        logger.info("分页查询所有保养项目");
        User user = SessionGetUtil.getUser();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(maintainFixService.MaintainCont(user));
        List<MaintainFix> maintainFixList = maintainFixService.queryBymaintainPager(pager,user);
        return new Pager4EasyUI<MaintainFix>(pager.getTotalRecords(), maintainFixList);
    }

    @ResponseBody
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainFix> search(@Param("name")String name,@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser()|| !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        Pager pager = new Pager();
        logger.info("模糊查询所有保养项目");
        User user = SessionGetUtil.getUser();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(maintainFixService.searCount(name,user));
        List<MaintainFix> maintainFixList = maintainFixService.searchByPager(name,pager,user);
        return new Pager4EasyUI<MaintainFix>(pager.getTotalRecords(), maintainFixList);
    }


    @ResponseBody
    @RequestMapping(value = "statusPager", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainFix> queryByStatus(@Param("status")String status,@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser()|| !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        Pager pager = new Pager();
        logger.info("根据状态查询保养项目");
        User user = SessionGetUtil.getUser();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(maintainFixService.countStatus(status,user));
        List<MaintainFix> maintainFixList = maintainFixService.byStatusPager(status,pager,user);
        return new Pager4EasyUI<MaintainFix>(pager.getTotalRecords(), maintainFixList);
    }


    @ResponseBody
    @RequestMapping(value = "statusRepairPager", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainFix> queryByRepairStatus(@Param("status")String status,@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser()|| !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        Pager pager = new Pager();
        logger.info("根据状态查询维修项目");
        User user = SessionGetUtil.getUser();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(maintainFixService.repairCountStatus(status,user));
        List<MaintainFix> maintainFixList = maintainFixService.repairByStatusPager(status,pager,user);
        return new Pager4EasyUI<MaintainFix>(pager.getTotalRecords(), maintainFixList);
    }


    @ResponseBody
    @RequestMapping(value = "searchRepair", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainFix> searchRepair(@Param("name")String name,@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser()|| !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        Pager pager = new Pager();
        logger.info("模糊查询所有维修项目");
        User user = SessionGetUtil.getUser();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(maintainFixService.searRepairCount(name,user));
        List<MaintainFix> maintainFixList = maintainFixService.searchByRepairPager(name,pager,user);
        return new Pager4EasyUI<MaintainFix>(pager.getTotalRecords(), maintainFixList);
    }


    @ResponseBody
    @RequestMapping(value = "maintain_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryUserAll() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效");
            return null;
        }
        logger.info("查询所有维修保养项目，封装成ComboBox");
        User user = SessionGetUtil.getUser();
        List<MaintainFix> maintainFices = maintainFixService.queryAll(user);
        List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
        for (MaintainFix maintainFix : maintainFices) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(maintainFix.getMaintainId());
            comboBox4EasyUI.setText(maintainFix.getMaintainName());
            comboBox4EasyUIs.add(comboBox4EasyUI);
        }
        return comboBox4EasyUIs;
    }

    @ResponseBody
    @RequestMapping(value="query_default",method= RequestMethod.GET)
    public List<LineBasic> queryDefault(@Param("companyId")String companyId,@Param("maintain")String maintain){
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRole1)) {
                logger.info("默认查询本月维修保养项目次数统计");
                User user = SessionGetUtil.getUser();

                List<LineBasic> lineBasics = new ArrayList<LineBasic>();
                if(user.getCompanyId() != null && !user.getCompanyId().equals("")){
                    companyId = user.getCompanyId();
                }
                List<MaintainFix> maintainFices = maintainFixService.queryByType(companyId,maintain);
                for(MaintainFix m: maintainFices){
                    LineBasic lineBasic = new LineBasic();
                    lineBasic.setName(m.getMaintainName());
                    dateDay(maintain,companyId,m.getMaintainId());
                    lineBasic.setCategories(HighchartsData.strDay);
                    lineBasic.setData(HighchartsData.doubleDayOne);
                    lineBasics.add(lineBasic);
                }
                return lineBasics;
            }
            return null;
        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value="query_condition",method= RequestMethod.GET)
    public List<LineBasic> queryCondition(@Param("start")String start,@Param("end")String end,
                                          @Param("type")String type,@Param("companyId")String companyId,
                                          @Param("maintain")String maintain){
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRole1)) {
                logger.info("根据年，月，季度，周，日查询维修保养项目次数统计");
                List<LineBasic> lineBasics = new ArrayList<LineBasic>();
                User user = SessionGetUtil.getUser();
                if (user.getCompanyId() != null && !user.getCompanyId().equals("")) {
                    companyId = user.getCompanyId();
                }
                List<MaintainFix> maintainFices = maintainFixService.queryByType(companyId,maintain);
                if (start != null && !start.equals("") && end != null && !end.equals("") && type != null && !type.equals("")) {
                    if (type.equals("year")) {
                        HighchartsData.setStrYear(start, end);
                        for(MaintainFix m: maintainFices){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(m.getMaintainName());
                            dateDay(maintain,companyId,m.getMaintainId());
                            dataCondition(start,end,maintain,type,"year","one",companyId,m.getMaintainId());
                            lineBasic.setCategories(HighchartsData.strYear);
                            lineBasic.setData(HighchartsData.doubleYearOne);
                            lineBasics.add(lineBasic);
                        }
                    } else if (type.equals("quarter")) {
                        for(MaintainFix m: maintainFices){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(m.getMaintainName());
                            dataCondition(start,end,maintain,type,"quarter","one",companyId,m.getMaintainId());
                            lineBasic.setCategories(HighchartsData.strQuarter);
                            lineBasic.setData(HighchartsData.doubleQuarterOne);
                            lineBasics.add(lineBasic);
                        }
                    } else if (type.equals("month")) {
                        for(MaintainFix m: maintainFices){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(m.getMaintainName());
                            dataCondition(start,end,maintain,type,"month","one",companyId,m.getMaintainId());
                            lineBasic.setCategories(HighchartsData.strMonth);
                            lineBasic.setData(HighchartsData.doubleMonthOne);
                            lineBasics.add(lineBasic);
                        }
                    } else if (type.equals("week")) {
                        HighchartsData.setStrWeek(start, end);
                        for(MaintainFix m: maintainFices){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(m.getMaintainName());
                            dataCondition(start,end,maintain,type,"week","one",companyId,m.getMaintainId());
                            lineBasic.setCategories(HighchartsData.strWeek);
                            lineBasic.setData(HighchartsData.doubleWeekOne);
                            lineBasics.add(lineBasic);
                        }
                    } else if (type.equals("day")) {
                        for(MaintainFix m: maintainFices){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(m.getMaintainName());
                            dataCondition(start,end,maintain,type,"day","one",companyId,m.getMaintainId());
                            lineBasic.setCategories(HighchartsData.strDay);
                            lineBasic.setData(HighchartsData.doubleDayOne);
                            lineBasics.add(lineBasic);
                        }
                    }
                }
                return lineBasics;
            }
            return null;
        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    /*  默认查询本月的维修保养次数统计
     * */
    public void dateDay(String type,String companyId,String maintainId){
        HighchartsData.doubleDayOne = new double[31];
        List<MaintainDetail> maintainDetails =  maintainDetailService.queryByDefault(type,companyId,maintainId);
        int i = 0;
        double[] doubles = new double[maintainDetails.size()];
        String[] strs = new String[maintainDetails.size()];
        for(MaintainDetail io: maintainDetails) {
            doubles[i] = io.getCoont();
            strs[i] = HighchartsData.dateFormat(io.getDetailCreatedTime(),"day");
            i++;
        }
        for(int j = 0,len = HighchartsData.strDay.length; j <len ; j++){
            for(int k = 0; k < strs.length; k++){
                if(HighchartsData.strDay[j].equals(strs[k])){
                    HighchartsData.doubleDayOne[j] = doubles[k];
                }
            }
        }
    }

    /*
    *  按年，季度，月，周，日，查询 维修保养次数统计
    * */
    public void dataCondition(String start,String end,String maintainOrFix,String type,String date,String species,String companyId,String maintainId){
        HighchartsData.doubleDayOne = new double[31];
        HighchartsData.doubleMonthOne = new double[12];
        HighchartsData.doubleQuarterOne = new double[4];
        HighchartsData.doubleYearOne = new double[HighchartsData.yearLen];
        HighchartsData.doubleWeekOne = new double[HighchartsData.weekLen];
        List<MaintainDetail> maintainDetails = maintainDetailService.queryByCondition(start,end,maintainOrFix,type,companyId,maintainId);
        int i = 0;
        double[] doubles = new double[maintainDetails.size()];
        String[] strs = new String[maintainDetails.size()];
        HighchartsData.len = 0;
        for(MaintainDetail io: maintainDetails) {
            doubles[i] = io.getCoont();
            if(date.equals("month")) {
                strs[i] = HighchartsData.dateFormat(io.getDetailCreatedTime(), "month");
                HighchartsData.len = HighchartsData.strMonth.length;
            }else if(date.equals("day")){
                strs[i] = HighchartsData.dateFormat(io.getDetailCreatedTime(), "day");
                HighchartsData.len = HighchartsData.strDay.length;
            }else if(date.equals("quarter")){
                strs[i] = HighchartsData.dateFormat(io.getDetailCreatedTime(), "quarter");
                HighchartsData.len = HighchartsData.strQuarter.length;
            }else if(date.equals("year")){
                strs[i] = HighchartsData.dateFormat(io.getDetailCreatedTime(),"year");
                HighchartsData.len = HighchartsData.strYear.length;
            }else if(date.equals("week")){
                strs[i] = "第"+String.valueOf(HighchartsData.getWeek(HighchartsData.dateFormat(io.getDetailCreatedTime())))+"周";
                HighchartsData.len = HighchartsData.strWeek.length;
            }
            i++;
        }
        if(date.equals("quarter")) {
            HighchartsData.getQuarter(strs,doubles,species);
        }else if(date.equals("month")){
            HighchartsData.getMonth(strs,doubles,species);
        }else if(date.equals("day")){
            HighchartsData.getDay(strs,doubles,species);
        }else if(date.equals("year")){
            HighchartsData. getYear(strs,doubles,species);
        }else if(date.equals("week")){
            HighchartsData.getWeek(strs,doubles,species);
        }
    }
}
