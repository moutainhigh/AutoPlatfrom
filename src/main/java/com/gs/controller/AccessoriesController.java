package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Accessories;
import com.gs.bean.AccessoriesType;
import com.gs.bean.CarBrand;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.bean.*;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.AccessoriesService;
import com.gs.service.AccessoriesTypeService;
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
import javax.naming.ldap.PagedResultsControl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by GOD on 2017/4/19.
 */
@Controller
@RequestMapping("accessories")
public class AccessoriesController {

    private Logger logger = (Logger) LoggerFactory.getLogger(AccessoriesController.class);

    @Resource
    private AccessoriesService accessoriesService;

    @Resource
    private AccessoriesTypeService accessoriesTypeService;

    private String queryRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_REPERTORY + ","
            + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.COMPANY_SALES + "," + Constants.COMPANY_BUYER;

    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_REPERTORY;

    // 可以查看的角色：董事长、财务员、超级管理员、普通管理员
    private String queryRole1 = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_ACCOUNTING + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;

    @RequestMapping(value = "stock", method = RequestMethod.GET)
    private String stock() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
        if (!CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("无权访问，想要访问请联系管理员!");
            return "error/notPermission";
        }
        logger.info("显示配件信息");
        return "accessories/stock";
    }

    @ResponseBody
    @RequestMapping(value = "pager", method = RequestMethod.GET)
    public Pager4EasyUI<Accessories> queryPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        User user = SessionGetUtil.getUser();
        logger.info("分页查询");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(accessoriesService.count(user));
        List<Accessories> accessories = accessoriesService.queryByPager(pager, user);
        return new Pager4EasyUI<Accessories>(pager.getTotalRecords(), accessories);
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult addAcc(Accessories accessories) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole)) {
                logger.info("添加失败");
                return ControllerResult.getFailResult("添加失败，没有该权限操作");
            }
            logger.info("添加");
            System.out.println(accessories);
            accessories.setAccId(UUIDUtil.uuid());
            accessories.setAccStatus("Y");
            accessoriesService.insert(accessories);
            return ControllerResult.getSuccessResult("添加成功");
        } catch (Exception e) {
            logger.info("添加失败，出现了一个错误");
            return ControllerResult.getFailResult("添加失败，出现了一个错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ControllerResult updateAcc(Accessories accessories) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole)) {
                logger.info("更新失败");
                return ControllerResult.getFailResult("更新失败，没有该权限操作");
            }
            logger.info("更新");
            accessories.setAccStatus("Y");
            accessoriesService.update(accessories);
            return ControllerResult.getSuccessResult("修改成功");
        } catch (Exception e) {
            logger.info("修改失败，出现了一个错误");
            return ControllerResult.getFailResult("修改失败，出现了一个错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "update_status", method = RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id") String id, @Param("status") String status) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole)) {
                logger.info("更新状态失败");
                return ControllerResult.getFailResult("更新状态失败，没有该权限操作");
            }
            logger.info("更新状态");
            if (status.equals("Y")) {
                accessoriesService.active(id);
            } else if (status.equals("N")) {
                accessoriesService.inactive(id);
            }
            return ControllerResult.getSuccessResult("更新成功");
        } catch (Exception e) {
            logger.info("操作失败，出现了一个错误");
            return ControllerResult.getFailResult("操作失败，出现了一个错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryAll", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryUserAll() {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        User user = SessionGetUtil.getUser();
        logger.info("查询配件");
        List<Accessories> accessoriesList = accessoriesService.queryAll(user);
        List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
        for (Accessories accessoriess : accessoriesList) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(accessoriess.getAccId());
            comboBox4EasyUI.setText(accessoriess.getAccName());
            comboBox4EasyUIs.add(comboBox4EasyUI);
        }
        return comboBox4EasyUIs;
    }

    @ResponseBody
    @RequestMapping(value = "queryByIdAcc", method = RequestMethod.GET)
    public Pager4EasyUI<Accessories> queryByIdAccPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("id") String id) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        User user = SessionGetUtil.getUser();
        Pager pager = new Pager();
        logger.info("分页查询某个分类下的配件");
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(accessoriesService.countByTypeId(id, user));
        List<Accessories> accessoriesList = accessoriesService.queryByIdPager(id, pager, user);
        return new Pager4EasyUI<Accessories>(pager.getTotalRecords(), accessoriesList);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ResponseBody
    @RequestMapping(value = "queryByStatus_Acc", method = RequestMethod.GET)
    public Pager4EasyUI<Accessories> queryByStatusAcc(@Param("status") String status, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        if (status.equals("Y")) {
            logger.info("分页查询可用的配件");
        } else {
            logger.info("分页查询不可用的配件");
        }
        User user = SessionGetUtil.getUser();
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(accessoriesService.countByStatus(status,user));
        List<Accessories> accessoriess = accessoriesService.queryByStatusPager(status, pager,user);
        return new Pager4EasyUI<Accessories>(pager.getTotalRecords(), accessoriess);
    }

    @ResponseBody
    @RequestMapping(value = "queryByCondition", method = RequestMethod.GET)
    public Pager4EasyUI<Accessories> queryByCondition(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize,
                                                      @Param("accName") String accName, @Param("accCommodityCode") String accCommodityCode,
                                                      @Param("accTypeId") String accTypeId, @Param("companyId") String companyId) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        User user = SessionGetUtil.getUser();
        logger.info("条件查询配件");
        Accessories accessories = new Accessories();
        accessories.setAccName(accName);
        accessories.setAccTypeId(accTypeId);
        accessories.setCompanyId(companyId);
        accessories.setAccCommodityCode(accCommodityCode);
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        List<Accessories> accessoriesList = new ArrayList<Accessories>();
        pager.setTotalRecords(accessoriesService.countByCondition(accessories,user));
        accessoriesList = accessoriesService.queryByCondition(pager, accessories,user);

        return new Pager4EasyUI<Accessories>(pager.getTotalRecords(), accessoriesList);
    }
    @ResponseBody
    @RequestMapping(value="query_default",method= RequestMethod.GET)
    public List<LineBasic> queryAll(@Param("companyId")String companyId,@Param("quantity")String quantity){
        if(SessionGetUtil.isUser()) {

                logger.info("默认查询本月库存统计");
                List<LineBasic> lineBasics = new ArrayList<LineBasic>();
                User user = SessionGetUtil.getUser();
                if(user.getCompanyId() != null && !user.getCompanyId().equals("")){
                    companyId = user.getCompanyId();
                }
                List<AccessoriesType> accessoriesTypes = accessoriesTypeService.queryByCompany(companyId);
                for(AccessoriesType accType: accessoriesTypes){
                    LineBasic lineBasic = new LineBasic();
                    lineBasic.setName(accType.getAccTypeName());
                    if(quantity.equals("总数量")) {
                        dateDay("one", companyId, accType.getAccTypeId());
                        lineBasic.setData(HighchartsData.doubleDayOne);
                    }else if(quantity.equals("可用数量")){
                        dateDay("two", companyId, accType.getAccTypeId());
                        lineBasic.setData(HighchartsData.doubleDayTwo);
                    }
                    lineBasic.setCategories(HighchartsData.strDay);
                    lineBasics.add(lineBasic);
                }
                return lineBasics;

        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }

    }

    @ResponseBody
    @RequestMapping(value="query_condition",method= RequestMethod.GET)
    public List<LineBasic> queryCondition(@Param("start")String start,@Param("end")String end,
                                          @Param("type")String type,@Param("companyId")String companyId,
                                          @Param("quantity")String quantity){
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRole1)) {
                logger.info("根据年，月，季度，周，日查询库存统计");
                List<LineBasic> lineBasics = new ArrayList<LineBasic>();
                User user = SessionGetUtil.getUser();
                if (user.getCompanyId() != null && !user.getCompanyId().equals("")) {
                    companyId = user.getCompanyId();
                }
                List<AccessoriesType> accessoriesTypes = accessoriesTypeService.queryByCompany(companyId);
                if (start != null && !start.equals("") && end != null && !end.equals("") && type != null && !type.equals("")) {
                    if (type.equals("year")) {
                        HighchartsData.setStrYear(start, end);
                        for(AccessoriesType accType: accessoriesTypes){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(accType.getAccTypeName());
                            if(quantity.equals("总数量")) {
                                dataCondition(start,end,type,"year","one",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleYearOne);
                            }else if(quantity.equals("可用数量")){
                                dataCondition(start,end,type,"year","two",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleYearTwo);
                            }
                            lineBasic.setCategories(HighchartsData.strYear);
                            lineBasics.add(lineBasic);
                        }
                    } else if (type.equals("quarter")) {
                        for(AccessoriesType accType: accessoriesTypes){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(accType.getAccTypeName());
                            if(quantity.equals("总数量")) {
                                dataCondition(start,end,type,"quarter","one",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleQuarterOne);
                            }else if(quantity.equals("可用数量")){
                                dataCondition(start,end,type,"quarter","two",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleQuarterTwo);
                            }
                            lineBasic.setCategories(HighchartsData.strQuarter);
                            lineBasics.add(lineBasic);
                        }
                    } else if (type.equals("month")) {
                        for(AccessoriesType accType: accessoriesTypes){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(accType.getAccTypeName());
                            if(quantity.equals("总数量")) {
                                dataCondition(start,end,type,"month","one",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleMonthOne);
                            }else if(quantity.equals("可用数量")){
                                dataCondition(start,end,type,"month","two",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleMonthTwo);
                            }
                            lineBasic.setCategories(HighchartsData.strMonth);
                            lineBasics.add(lineBasic);
                        }
                    } else if (type.equals("week")) {
                        HighchartsData.setStrWeek(start, end);
                        for(AccessoriesType accType: accessoriesTypes){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(accType.getAccTypeName());
                            if(quantity.equals("总数量")) {
                                dataCondition(start,end,type,"week","one",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleWeekOne);
                            }else if(quantity.equals("可用数量")){
                                dataCondition(start,end,type,"week","two",companyId,accType.getAccTypeId());;
                                lineBasic.setData(HighchartsData.doubleWeekTwo);
                            }
                            lineBasic.setCategories(HighchartsData.strWeek);
                            lineBasics.add(lineBasic);
                        }
                    } else if (type.equals("day")) {
                        for(AccessoriesType accType: accessoriesTypes){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(accType.getAccTypeName());
                            if(quantity.equals("总数量")) {
                                dataCondition(start,end,type,"day","one",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleDayOne);
                            }else if(quantity.equals("可用数量")){
                                dataCondition(start,end,type,"day","two",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleDayTwo);
                            }
                            lineBasic.setCategories(HighchartsData.strDay);
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


    /*  默认查询本月的库存
   * */
    public void dateDay(String type,String companyId,String accTypeId){
        HighchartsData.doubleDayTwo = new double[31];
        HighchartsData.doubleDayOne = new double[31];
        List<Accessories> accessories = null;
        if(type.equals("one")){
            accessories = accessoriesService.queryByConditionTotal(HighchartsData.stdayDate(),HighchartsData.lastDate(),"day",companyId,accTypeId);
        }else if(type.equals("two")){
            accessories = accessoriesService.queryByConditionIdle(HighchartsData.stdayDate(),HighchartsData.lastDate(),"day",companyId,accTypeId);
        }
        int i = 0;
        double[] doubles = new double[accessories.size()];
        String[] strs = new String[accessories.size()];
        for(Accessories io: accessories) {
            if(type.equals("one")){
                doubles[i] = io.getAccTotal();
                strs[i] = HighchartsData.dateFormat(io.getAccCreatedTime(),"day");
            }else if(type.equals("two")){
                strs[i] = HighchartsData.dateFormat(io.getAccBuyedTime(),"day");
                doubles[i] = io.getAccIdle();
            }
            i++;
        }
        for(int j = 0,len = HighchartsData.strDay.length; j <len ; j++){
            for(int k = 0; k < strs.length; k++){
                if(HighchartsData.strDay[j].equals(strs[k])){
                    if(type.equals("two")){
                        HighchartsData.doubleDayTwo[j] = doubles[k];
                    }else if(type.equals("one")){
                        HighchartsData.doubleDayOne[j] = doubles[k];
                    }

                }
            }
        }
    }

    /*
   *  按年，季度，月，周，日，查询 库存统计
   * */
    public void dataCondition(String start,String end,String type,String date,String species,String companyId,String accTypeId){
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
        List<Accessories> accessories = null;
        if(species.equals("one")){
            accessories = accessoriesService.queryByConditionTotal(start,end,type,companyId,accTypeId);
        }else if(species.equals("two")){
            accessories = accessoriesService.queryByConditionIdle(start,end,type,companyId,accTypeId);
        }
        int i = 0;
        double[] doubles = new double[accessories.size()];
        String[] strs = new String[accessories.size()];
        HighchartsData.len = 0;
        for(Accessories io: accessories) {
            if(date.equals("month")) {
                if(species.equals("one")){
                    doubles[i] = io.getAccTotal();
                    strs[i] = HighchartsData.dateFormat(io.getAccCreatedTime(),"month");
                }else if(species.equals("two")){
                    strs[i] = HighchartsData.dateFormat(io.getAccBuyedTime(),"month");
                    doubles[i] = io.getAccIdle();
                }
                HighchartsData.len = HighchartsData.strMonth.length;
            }else if(date.equals("day")){
                if(species.equals("one")){
                    doubles[i] = io.getAccTotal();
                    strs[i] = HighchartsData.dateFormat(io.getAccCreatedTime(),"day");
                }else if(species.equals("two")){
                    strs[i] = HighchartsData.dateFormat(io.getAccBuyedTime(),"day");
                    doubles[i] = io.getAccIdle();
                }
                HighchartsData.len = HighchartsData.strDay.length;
            }else if(date.equals("quarter")){
                if(species.equals("one")){
                    doubles[i] = io.getAccTotal();
                    strs[i] = HighchartsData.dateFormat(io.getAccCreatedTime(),"quarter");
                }else if(species.equals("two")){
                    strs[i] = HighchartsData.dateFormat(io.getAccBuyedTime(),"quarter");
                    doubles[i] = io.getAccIdle();
                }
                HighchartsData.len = HighchartsData.strQuarter.length;
            }else if(date.equals("year")){
                if(species.equals("one")){
                    doubles[i] = io.getAccTotal();
                    strs[i] = HighchartsData.dateFormat(io.getAccCreatedTime(),"year");
                }else if(species.equals("two")){
                    strs[i] = HighchartsData.dateFormat(io.getAccBuyedTime(),"year");
                    doubles[i] = io.getAccIdle();
                }
                HighchartsData.len = HighchartsData.strYear.length;
            }else if(date.equals("week")){
                if(species.equals("one")){
                    doubles[i] = io.getAccTotal();
                    strs[i] = "第"+String.valueOf(HighchartsData.getWeek(HighchartsData.dateFormat(io.getAccCreatedTime())))+"周";
                }else if(species.equals("two")){
                    strs[i] = "第"+String.valueOf(HighchartsData.getWeek(HighchartsData.dateFormat(io.getAccBuyedTime())))+"周";
                    doubles[i] = io.getAccIdle();
                }
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


