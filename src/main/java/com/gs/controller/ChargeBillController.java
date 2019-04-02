package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.common.Constants;
import com.gs.common.bean.*;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.ExcelExport;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.*;
import org.apache.ibatis.annotations.Param;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-04-30. 收费单据控制器
 */
@Controller
@RequestMapping("bill")
public class ChargeBillController {
    private Logger logger = (Logger) LoggerFactory.getLogger(ChargeBillController.class);

    @Resource
    private ChargeBillService chargeBillService;

    @Resource
    private MaintainRecordService maintainRecordService;

    @Resource
    private CheckinService checkinService;

    @Resource
    private MaintainRemindService maintainRemindService;

    @Resource
    private IncomingTypeService incomingTypeService;

    @Resource
    private IncomingOutgoingService incomingOutgoingService;

    /**
     * 可以查看的角色：董事长、接待员、超级管理员、普通管理员
     */
    private String queryRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_RECEIVE + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.CAR_OWNER;

    /**
     * 可以操作的角色：董事长、接待员
     */
    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_RECEIVE;

    /**
     * 可以查看对账单的角色：董事长、财务、超级管理员、普通管理员
     */
    private String queryRole1 = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_ACCOUNTING + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;

    /**
     * 车主本人才可以查看消费统计
     */
    private String queryRoleCustomer = Constants.CAR_OWNER;

    /**
     * 访问收费单据页面
     * @return
     */
    @RequestMapping(value = "bill_page", method = RequestMethod.GET)
    public String chargeBillPage() {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(queryRole)) {
                logger.info("访问收费单据页面");
                return "settlementCar/charge_document";
            }
            return "error/notPermission";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    /**
     * 访问对账单页面
     * @return
     */
    @RequestMapping(value = "statement_page", method = RequestMethod.GET)
    public String statementPage() {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(queryRole1)) {
                logger.info("访问对账单页面");
                return "financeManage/account_statement";
            }
            return "error/notPermission";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    /**
     * 车主用户查看我的收费单据
     * @return ModelAndView
     */
    @RequestMapping(value = "my_bill", method = RequestMethod.GET)
    private ModelAndView carOwerChargeBill() {
        ModelAndView mav = new ModelAndView();
        User user = SessionGetUtil.getUser();
        if (!SessionGetUtil.isUser()) {
            mav.setViewName("index/notLogin");
            return mav;
        }
        logger.info("车主用户查看我的收费单据");
        mav.setViewName("customerClient/chargeBill");
        mav.addObject("bills", chargeBillService.queryMyName(user));
        return mav;
    }

    /**
     * 分页查询指定状态的收费单据数据
     * @param pageNumber
     * @param pageSize
     * @param status
     * @return Pager4EasyUI<ChargeBill>
     */
    @ResponseBody
    @RequestMapping(value="pager",method= RequestMethod.GET)
    public Pager4EasyUI<ChargeBill> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, @Param("status") String status){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole) || CheckRoleUtil.checkRoles(queryRole1)) {
                    logger.info("分页查询指定状态的收费单据数据");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<ChargeBill> chargeBillList = new ArrayList<>();
                    if ("ALL".equals(status)) {
                        pager.setTotalRecords(chargeBillService.count(user));
                        chargeBillList = chargeBillService.queryByPager(pager, user);
                    } else {
                        pager.setTotalRecords(chargeBillService.countByStatus(status, user));
                        chargeBillList = chargeBillService.queryPagerByStatus(pager, status, user);
                    }
                    return new Pager4EasyUI<ChargeBill>(pager.getTotalRecords(), chargeBillList);
                }
                return null;
            } catch (Exception e) {
                logger.info("分页查询指定状态的收费单据数据失败，出现了一个异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    /**
     * 根据条件分页查询收费单据记录
     * @param pageNumber
     * @param pageSize
     * @param userName
     * @param userPhone
     * @param paymentMethod
     * @param companyId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "condition_pager", method = RequestMethod.GET)
    public Pager4EasyUI<ChargeBill> queryPagerByCondition(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,
                                                       @Param("userName")String userName, @Param("userPhone")String userPhone,
                                                       @Param("paymentMethod")String paymentMethod, @Param("companyId")String companyId) {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(queryRole) || CheckRoleUtil.checkRoles(queryRole1)) {
                logger.info("根据条件分页查询收费单据记录");
                User user = SessionGetUtil.getUser();
                ChargeBill chargeBill = new ChargeBill();
                chargeBill.setPaymentMethod(paymentMethod);
                MaintainRecord record = new MaintainRecord();
                Checkin checkin = new Checkin();
                checkin.setUserName(userName);
                checkin.setUserPhone(userPhone);
                record.setCheckin(checkin);
                chargeBill.setRecord(record);
                chargeBill.setCompanyId(companyId);

                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                List<ChargeBill> chargeBills = new ArrayList<>();
                pager.setTotalRecords(chargeBillService.countByCondition(chargeBill, user));
                chargeBills = chargeBillService.queryPagerByCondition(pager, chargeBill, user);

                return new Pager4EasyUI<>(pager.getTotalRecords(), chargeBills);
            }
            return null;
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    /**
     * 更新收费单据记录的状态
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update_status", method = RequestMethod.GET)
    public ControllerResult updateChargeBillStatus(String id, String status) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("更新收费单据记录的状态");
                    if ("Y".equals(status)) {
                        chargeBillService.inactive(id);
                    } else {
                        chargeBillService.active(id);
                    }
                    return ControllerResult.getSuccessResult("更新收费单据记录成功");
                }
                return ControllerResult.getFailResult("更新收费单据记录失败，没有权限操作");
            } catch (Exception e) {
                logger.info("更新收费单据记录失败，出现了一个错误");
                return ControllerResult.getFailResult("更新收费单据记录失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    /**
     * 结算提车，生成收费单据，生成维修保养提醒记录
     * @param chargeBill
     * @param userId
     * @param carMileage
     * @param maintainOrFix
     * @param checkinId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult addChargeBill(@Param("chargeBill") ChargeBill chargeBill, @Param("userId") String userId, @Param("carMileage") String carMileage,
                                          @Param("maintainOrFix") String maintainOrFix,@Param("checkinId")String checkinId) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("结算提车，生成收费单据，生成维修保养提醒记录");
                    User loginUser = SessionGetUtil.getUser();

                    if ("保养".equals(maintainOrFix)) {
                        Calendar calendar = Calendar.getInstance();
                        Date lastMaintainTime = new Date();
                        calendar.setTime(lastMaintainTime);
                        calendar.add(Calendar.MONTH, 6);
                        MaintainRemind maintainRemind = new MaintainRemind();
                        maintainRemind.setLastMaintainTime(lastMaintainTime);
                        maintainRemind.setCompanyId(loginUser.getCompanyId());
                        maintainRemind.setRemindTime(calendar.getTime());
                        maintainRemind.setUserId(userId);
                        maintainRemind.setCheckinId(checkinId);
                        maintainRemind.setLastMaintainMileage(carMileage);
                        maintainRemindService.insert(maintainRemind);
                    }
                    IncomingType incomingType = incomingTypeService.queryByName(Constants.MAINTENANCE_IN);
                    IncomingOutgoing incomingOutgoing = new IncomingOutgoing();
                    incomingOutgoing.setInTypeId(incomingType.getInTypeId());
                    incomingOutgoing.setInOutCreatedUser(loginUser.getUserId());
                    incomingOutgoing.setInOutMoney(chargeBill.getActualPayment());
                    incomingOutgoing.setCompanyId(loginUser.getCompanyId());

                    chargeBill.setCompanyId(loginUser.getCompanyId());

                    incomingOutgoingService.insert(incomingOutgoing);
                    maintainRecordService.updatePickupTime(chargeBill.getRecordId());
                    chargeBillService.insert(chargeBill);
                    maintainRecordService.updateSpeedStatusById(Constants.COMPLETED, chargeBill.getRecordId());
                    checkinService.inactive(chargeBill.getRecord().getCheckinId());
                    return ControllerResult.getSuccessResult("已经成功结算，收费单据已经自动生成");
                }
                return ControllerResult.getFailResult("结算提车失败，没有权限操作");
            } catch (Exception e) {
                logger.info("结算提车失败，出现了一个错误");
                return ControllerResult.getFailResult("结算提车失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    /**
     * 修改收费单据
     * @param chargeBill
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ControllerResult editChargeBill(ChargeBill chargeBill) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("修改收费单据");
                    chargeBillService.update(chargeBill);
                    return ControllerResult.getSuccessResult("修改收费单据成功");
                }
                return ControllerResult.getFailResult("修改收费单据失败，没有权限操作");
            } catch (Exception e) {
                logger.info("修改收费单据失败，出现了一个错误");
                return ControllerResult.getFailResult("修改收费单据失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    /**
     * 收费单据导出
     * @param request
     * @param response
     */
    @RequestMapping(value="export_excel", method = RequestMethod.GET)
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (CheckRoleUtil.checkRoles(queryRole)) {
                logger.info("收费单据导出");
                User user = SessionGetUtil.getUser();
                List<ChargeBill> chargeBills = chargeBillService.queryAll(user);
                String title = "收费单据";
                String[] rowsName = new String[]{"收费单据编号", "车主姓名", "车主手机", "汽车品牌",
                        "汽车车型", "汽车颜色", "汽车车牌", "车牌号码", "维修保养记录提车时间",
                        "维修保养记录描述", "付款方式", "总金额", "实际付款", "收费时间", "收费单据创建时间",
                        "收费单据描述", "收费单据状态"};
                List<Object[]> dataList = new ArrayList<>();
                for (ChargeBill c : chargeBills) {
                    Object[] objs = new Object[rowsName.length];
                    objs[0] = c.getChargeBillId();
                    objs[1] = c.getRecord().getCheckin().getUserName();
                    objs[2] = c.getRecord().getCheckin().getUserPhone();
                    objs[3] = c.getRecord().getCheckin().getBrand().getBrandName();
                    objs[4] = c.getRecord().getCheckin().getModel().getModelName();
                    objs[5] = c.getRecord().getCheckin().getColor().getColorName();
                    objs[6] = c.getRecord().getCheckin().getPlate().getPlateName();
                    objs[7] = c.getRecord().getCheckin().getCarPlate();
                    objs[8] = c.getRecord().getPickupTime();
                    objs[9] = c.getRecord().getRecordDes();
                    objs[10] = c.getPaymentMethod();
                    objs[11] = c.getChargeBillMoney();
                    objs[12] = c.getActualPayment();
                    objs[13] = c.getChargeTime();
                    objs[14] = java.sql.Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getChargeCreatedTime()));
                    objs[15] = c.getChargeBillDes();
                    objs[16] = c.getChargeBillStatus();
                    dataList.add(objs);
                }
                ExcelExport ex = new ExcelExport(title, rowsName, dataList, response);
                ex.exportData();
            }
        } catch (Exception e) {
            logger.info("导出失败,出现异常!");
        }
    }

    /**
     * 默认查询本月车主用户消费统计，报表显示
     * @return List<LineBasic>
     */
    @ResponseBody
    @RequestMapping(value="query_default",method= RequestMethod.GET)
    public List<LineBasic> queryAll(){
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRoleCustomer)) {
                logger.info("默认查询本月车主用户消费统计，报表显示");
                List<LineBasic> lineBasics = new ArrayList<>();
                User user = SessionGetUtil.getUser();
                LineBasic lineBasic = new LineBasic();
                LineBasic lineBasic1 = new LineBasic();
                lineBasic.setName("保养");
                dateDay("one", user.getUserId());
                lineBasic.setData(HighchartsData.doubleDayOne);
                lineBasic1.setName("维修");
                dateDay("two", user.getUserId());
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
     * 根据年，月，季度，周，日查询所有车主用户消费统计，报表显示
     * @param start
     * @param end
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value="query_condition",method= RequestMethod.GET)
    public List<LineBasic> queryCondition(@Param("start")String start,@Param("end")String end,
                                          @Param("type")String type){
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRoleCustomer)) {
                logger.info("根据年，月，季度，周，日查询所有车主用户消费统计，报表显示");
                List<LineBasic> lineBasics = new ArrayList<>();
                LineBasic lineBasic = new LineBasic();
                LineBasic lineBasic1 = new LineBasic();
                lineBasic.setName("保养");
                lineBasic1.setName("维修");
                User user = SessionGetUtil.getUser();
                if (start != null && !"".equals(start) && end != null && !"".equals(end) && type != null && !"".equals(type)) {
                    if ("year".equals(type)) {
                        HighchartsData.setStrYear(start, end);
                        dataCondition(start, end, "保养", type, "year", "one", user.getUserId());
                        lineBasic.setData(HighchartsData.doubleYearOne);
                        dataCondition(start, end, "维修", type, "year", "two", user.getUserId());
                        lineBasic1.setData(HighchartsData.doubleYearTwo);
                        lineBasic.setCategories(HighchartsData.strYear);
                        lineBasic1.setCategories(HighchartsData.strYear);
                    } else if ("quarter".equals(type)) {
                        dataCondition(start, end, "保养", type, "quarter", "one", user.getUserId());
                        lineBasic.setData(HighchartsData.doubleQuarterOne);
                        dataCondition(start, end, "维修", type, "quarter", "two", user.getUserId());
                        lineBasic1.setData(HighchartsData.doubleQuarterTwo);
                        lineBasic.setCategories(HighchartsData.strQuarter);
                        lineBasic1.setCategories(HighchartsData.strQuarter);
                    } else if ("month".equals(type)) {
                        dataCondition(start, end, "保养", type, "month", "one", user.getUserId());
                        lineBasic.setData(HighchartsData.doubleMonthOne);
                        dataCondition(start, end, "维修", type, "month", "two", user.getUserId());
                        lineBasic1.setData(HighchartsData.doubleMonthTwo);
                        lineBasic.setCategories(HighchartsData.strMonth);
                        lineBasic1.setCategories(HighchartsData.strMonth);
                    } else if ("week".equals(type)) {
                        HighchartsData.setStrWeek(start, end);
                        dataCondition(start, end, "保养", type, "week", "one", user.getUserId());
                        lineBasic.setData(HighchartsData.doubleWeekOne);
                        dataCondition(start, end, "维修", type, "week", "two", user.getUserId());
                        lineBasic1.setData(HighchartsData.doubleWeekTwo);
                        lineBasic.setCategories(HighchartsData.strWeek);
                        lineBasic1.setCategories(HighchartsData.strWeek);
                    } else if ("day".equals(type)) {
                        dataCondition(start, end, "保养", type, "day", "one", user.getUserId());
                        lineBasic.setData(HighchartsData.doubleDayOne);
                        dataCondition(start, end, "维修", type, "day", "two", user.getUserId());
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
     * 默认查询本月的车主消费
     * @param type
     * @param userId
     */
    public void dateDay(String type,String userId){
        HighchartsData.doubleDayTwo = new double[31];
        HighchartsData.doubleDayOne = new double[31];
        List<ChargeBill> chargeBills = null;
        if("one".equals(type)){
            chargeBills = chargeBillService.queryByDefault("保养",userId);
        }else if("two".equals(type)){
            chargeBills = chargeBillService.queryByDefault("维修",userId);
        }
        int i = 0;
        double[] doubles = new double[0];
        if (chargeBills != null) {
            doubles = new double[chargeBills.size()];
        }
        String[] strs = new String[0];
        if (chargeBills != null) {
            strs = new String[chargeBills.size()];
        }
        if (chargeBills != null) {
            for(ChargeBill io: chargeBills) {
                doubles[i] = io.getActualPayment();
                strs[i] = HighchartsData.dateFormat(io.getChargeCreatedTime(),"day");
                i++;
            }
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
     * 按年，季度，月，周，日，查询 车主消费
     * @param start
     * @param end
     * @param maintainOrFix
     * @param type
     * @param date
     * @param species
     * @param userId
     */
    public void dataCondition(String start,String end,String maintainOrFix,String type,String date,String species,String userId){
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
        List<ChargeBill> chargeBills = chargeBillService.queryByCondition(start,end,maintainOrFix,type,userId);;
        int i = 0;
        double[] doubles = new double[chargeBills.size()];
        String[] strs = new String[chargeBills.size()];
        HighchartsData.len = 0;
        for(ChargeBill io: chargeBills) {
            doubles[i] = io.getActualPayment();
            if("month".equals(date)) {
                strs[i] = HighchartsData.dateFormat(io.getChargeCreatedTime(), "month");
                HighchartsData.len = HighchartsData.strMonth.length;
            }else if("day".equals(date)){
                strs[i] = HighchartsData.dateFormat(io.getChargeCreatedTime(), "day");
                HighchartsData.len = HighchartsData.strDay.length;
            }else if("quarter".equals(date)){
                strs[i] = HighchartsData.dateFormat(io.getChargeCreatedTime(), "quarter");
                HighchartsData.len = HighchartsData.strQuarter.length;
            }else if("year".equals(date)){
                strs[i] = HighchartsData.dateFormat(io.getChargeCreatedTime(),"year");
                HighchartsData.len = HighchartsData.strYear.length;
            }else if("week".equals(date)){
                strs[i] = "第"+String.valueOf(HighchartsData.getWeek(HighchartsData.dateFormat(io.getChargeCreatedTime())))+"周";
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


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
