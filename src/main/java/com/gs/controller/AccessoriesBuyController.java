package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.common.Constants;
import com.gs.common.bean.*;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.common.util.UUIDUtil;
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

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Levc on 2017/4/17.
 */
@Controller
@RequestMapping("accessoriesBuy")
public class AccessoriesBuyController {

    private Logger logger = (Logger) LoggerFactory.getLogger(AccessoriesBuyController.class);

    @Resource
    private AccessoriesBuyService accessoriesBuyService;

    @Resource
    private AccessoriesService accessoriesService;

    @Resource
    private OutgoingTypeService outgoingTypeService;

    @Resource
    private IncomingOutgoingService incomingOutgoingService;

    /**
     * 可以看的角色
     * 董事长、库管、采购员、超级管理员
     */
    private String queryRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_REPERTORY + ","
            + Constants.COMPANY_BUYER + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.SYSTEM_ORDINARY_ADMIN;

    /**
     * 可以操作的角色
     * 董事长、采购员、超级管理员
     */
    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_BUYER + "," + Constants.SYSTEM_SUPER_ADMIN;

    /**
     * 控制是否插入库存
     */
    private boolean isInsertAcc = false;

    private Accessories accessories;

    /**
     * 显示配件采购管理
     *
     * @return String
     */
    @RequestMapping("showAccessoriesBuyHome")
    private String showAccessoriesBuyHome() {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(queryRole)) {
                logger.info("显示采购主页");
                return "accessories/accessories_buy";
            }
            return "error/notPermission";
        }
        logger.info("Session已失效，请重新登入");
        return "index/notLogin";
    }

    @ResponseBody
    @RequestMapping(value = "isAccAdd", method = RequestMethod.POST)
    private ControllerResult isAccAdd(AccessoriesBuy accessoriesBuy, @Param("state") String state) {
        System.out.println(accessoriesBuy);
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("添加采购信息");
                    User user = SessionGetUtil.getUser();
                    String accId = accessoriesBuy.getAccId();

                    Accessories acc = accessoriesBuy.getAccessories();

                    AccessoriesBuy ab = accessoriesBuyService.queryById(accId);

                    if (state.equals("true")) {  // 如果为 true 库存添加
                        logger.info("库存添加");

                        if (ab == null) {
                            accessoriesBuy.setAccBuyCount(acc.getAccIdle() + accessoriesBuy.getAccBuyCount());
                            accessoriesBuy.setCompanyId(user.getCompanyId());
                            accessoriesBuy.setAccBuyStatus("Y");
                            accessoriesBuy.setAccBuyCheck("N");
                            accessoriesBuy.setAccIsBuy("N");
                            accessoriesBuyService.insert(accessoriesBuy);

                            OutgoingType outgoingType = outgoingTypeService.queryByName(Constants.ACC_OUT);
                            IncomingOutgoing incomingOutgoing = new IncomingOutgoing();
                            incomingOutgoing.setCompanyId(user.getCompanyId());
                            incomingOutgoing.setInOutCreatedUser(user.getUserId());
                            incomingOutgoing.setInOutMoney(accessoriesBuy.getAccBuyMoney());
                            incomingOutgoing.setOutTypeId(outgoingType.getOutTypeId());
                            incomingOutgoingService.insert(incomingOutgoing);

                            return ControllerResult.getSuccessResult("添加成功");

                        }

                    } else if (state.equals("false")) { // 如果为false采购添加
                        logger.info("采购添加");

                        acc.setAccId(UUIDUtil.uuid());
                        acc.setAccName(accessoriesBuy.getAccessories().getAccName());
                        acc.setAccUnit(accessoriesBuy.getAccUnit());
                        acc.setAccTotal(accessoriesBuy.getAccBuyCount());
                        acc.setAccBuyedTime(accessoriesBuy.getAccBuyTime());
                        acc.setAccPrice(accessoriesBuy.getAccBuyPrice());
                        acc.setAccIdle(accessoriesBuy.getAccBuyCount());
                        acc.setAccCommodityCode(accessoriesBuy.getAccessories().getAccCommodityCode());

                        accessoriesBuy.setAccessories(acc);
                        accessoriesBuy.setAccId(acc.getAccId());
                        accessoriesBuy.setCompanyId(user.getCompanyId());
                        accessoriesBuy.setAccBuyStatus("Y");
                        accessoriesBuy.setAccBuyCheck("N");
                        accessoriesBuy.setAccIsBuy("N");

                        acc.setCompanyId(accessoriesBuy.getCompanyId());

                        accessoriesBuyService.insert(accessoriesBuy);
                        accessoriesService.insert(acc);

                        OutgoingType outgoingType = outgoingTypeService.queryByName(Constants.ACC_OUT);
                        IncomingOutgoing incomingOutgoing = new IncomingOutgoing();
                        incomingOutgoing.setCompanyId(user.getCompanyId());
                        incomingOutgoing.setInOutCreatedUser(user.getUserId());
                        incomingOutgoing.setInOutMoney(accessoriesBuy.getAccBuyMoney());
                        incomingOutgoing.setOutTypeId(outgoingType.getOutTypeId());
                        incomingOutgoingService.insert(incomingOutgoing);

                        return ControllerResult.getSuccessResult("添加成功");
                    }
                    return ControllerResult.getSuccessResult("添加成功");
                }
                return null;
            } catch (Exception e) {
                logger.info("添加失败【145】，出现异常" + e.getStackTrace().toString());
                return null;
            }
        } else {
            logger.info("session失效重新登入");
            return ControllerResult.getFailResult("添加失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "pager", method = RequestMethod.GET)
    private Pager4EasyUI<AccessoriesBuy> queryByPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    User user = SessionGetUtil.getUser();
                    logger.info("分页查询采购信息");
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(accessoriesBuyService.count(user));
                    List<AccessoriesBuy> accessoriesBuys = accessoriesBuyService.queryByPager(pager, user);
                    return new Pager4EasyUI<AccessoriesBuy>(pager.getTotalRecords(), accessoriesBuys);
                }
                return null;
            } catch (Exception e) {
                logger.info("出现异常【164】" + e.getStackTrace().toString());
                return null;
            }
        } else {
            logger.info("session失效重新登入");
            return null;
        }


    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ResponseBody
    @RequestMapping(value = "updateStatus", method = RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id") String id, @Param("status") String status) {

        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    User user = SessionGetUtil.getUser();
                    logger.info("更新收入类型状态");
                    if (status.equals("Y")) {
                        accessoriesBuyService.active(id);
                    } else if (status.equals("N")) {
                        accessoriesBuyService.inactive(id);
                    }
                    return ControllerResult.getSuccessResult("更新成功");
                }
                return null;
            } catch (Exception e) {
                logger.info("出现异常" + e.getStackTrace());
                return null;
            }
        } else {
            logger.info("session失效重新登入");
            return null;
        }


    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ControllerResult updateAccessoriesBuyInfo(AccessoriesBuy accessoriesBuy) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    int total = accessoriesBuy.getAccBuyCount();
                    int idle = total;

                    User user = SessionGetUtil.getUser();

                    Accessories acc = accessoriesBuy.getAccessories();

                    acc.setAccTypeId(acc.getAccessoriesType().getAccTypeId());
                    acc.setSupplyId(acc.getSupply().getSupplyId());
                    acc.setCompanyId(user.getCompanyId());
                    acc.setAccStatus("Y");
                    acc.setAccUnit(accessoriesBuy.getAccUnit());
                    acc.setAccPrice(accessoriesBuy.getAccBuyPrice());
                    acc.setAccBuyedTime(accessoriesBuy.getAccBuyTime());
                    acc.setAccDes(accessoriesBuy.getAccessories().getAccDes());
                    acc.setAccTotal(total);
                    acc.setAccIdle(idle);

                    accessoriesBuyService.update(accessoriesBuy);
                    accessoriesService.update(acc);

                    return ControllerResult.getSuccessResult("更新成功");
                }
                return ControllerResult.getFailResult("没有此权限访问");
            } catch (Exception e) {
                logger.info("出现异常[248]" + e.getStackTrace());
                return ControllerResult.getFailResult("出现了一个错误");
            }
        } else {
            logger.info("session失效重新登入");
            return ControllerResult.getFailResult("登入失效，重新登入");
        }


    }

    /**
     * 冻结
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public ControllerResult remove(@Param("id") String id) {

        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {

                    accessoriesBuyService.inactive(id);
                    return ControllerResult.getSuccessResult("操作成功");
                }
                return ControllerResult.getFailResult("没有此权限访问");
            } catch (Exception e) {
                logger.info("出现异常" + e.getStackTrace());
                return ControllerResult.getFailResult("出现了一个错误");
            }
        } else {
            logger.info("session失效重新登入");
            return ControllerResult.getFailResult("登入失效，重新登入");
        }


    }

    @ResponseBody
    @RequestMapping(value = "enable", method = RequestMethod.GET)
    public ControllerResult enable(@Param("id") String id) {

        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {

                    accessoriesBuyService.active(id);
                    return ControllerResult.getSuccessResult("操作成功");
                }
                return ControllerResult.getFailResult("没有此权限访问");
            } catch (Exception e) {
                logger.info("出现异常" + e.getStackTrace());
                return ControllerResult.getFailResult("出现了一个错误");
            }
        } else {
            logger.info("session失效重新登入");
            return ControllerResult.getFailResult("登入失效，重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value = "finish", method = RequestMethod.GET)
    public ControllerResult finish(@Param("id") String id, @Param("accId") String accId) {

        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    accessoriesBuyService.updateAccIsBuy(id);
                    accessoriesService.updateAccUseTime(accId);
                    return ControllerResult.getSuccessResult("操作成功");
                }
                return ControllerResult.getFailResult("没有此权限访问");
            } catch (Exception e) {
                logger.info("出现异常" + e.getStackTrace());
                return ControllerResult.getFailResult("出现了一个错误");
            }
        } else {
            logger.info("session失效重新登入");
            return ControllerResult.getFailResult("登入失效，重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value = "passChecks", method = RequestMethod.GET)
    public ControllerResult passChecks(@Param("ids") String[] ids) {

        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    accessoriesBuyService.batchUpdateBuyCheck(ids);
                    return ControllerResult.getSuccessResult("操作成功");
                }
                return ControllerResult.getFailResult("没有此权限访问");
            } catch (Exception e) {
                logger.info("出现异常" + e.getStackTrace());
                return ControllerResult.getFailResult("出现了一个错误");
            }
        } else {
            logger.info("session失效重新登入");
            return ControllerResult.getFailResult("登入失效，重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value = "checkData", method = RequestMethod.GET)
    public ControllerResult dataPrimary(@Param("name") String name) {
        if (!name.equals("") && name != null) {
            int cnt = accessoriesBuyService.dataPrimary(name);
            if (cnt != 0) {
                return ControllerResult.getFailResult("改配件已存在");
            } else return ControllerResult.getSuccessResult("该配件名可用");
        } else return ControllerResult.getSuccessResult("该配件名可用");
    }

    @ResponseBody
    @RequestMapping(value = "onlyCheck", method = RequestMethod.GET)
    public Pager4EasyUI<AccessoriesBuy> onlyCheck(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {

        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(accessoriesBuyService.countByCheckState(user));
                    List<AccessoriesBuy> accessoriesBuys = accessoriesBuyService.queryByCheckStatePager(pager, user);
                    return new Pager4EasyUI<AccessoriesBuy>(pager.getTotalRecords(), accessoriesBuys);
                }
                return null;
            } catch (Exception e) {
                logger.info("出现异常" + e.getStackTrace());
                return null;
            }
        } else {
            logger.info("session失效重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "onlyStatus", method = RequestMethod.GET)
    public Pager4EasyUI<AccessoriesBuy> onlyStatus(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(accessoriesBuyService.countByBuyState(user));
                    List<AccessoriesBuy> accessoriesBuys = accessoriesBuyService.queryByBuyStatePager(pager, user);
                    return new Pager4EasyUI<AccessoriesBuy>(pager.getTotalRecords(), accessoriesBuys);
                }
                return null;
            } catch (Exception e) {
                logger.info("出现异常" + e.getStackTrace());
                return null;
            }
        } else {
            logger.info("session失效重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "onlyBuy", method = RequestMethod.GET)
    public Pager4EasyUI<AccessoriesBuy> onlyBuy(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(accessoriesBuyService.countAccIsBuy(user));
                    List<AccessoriesBuy> accessoriesBuys = accessoriesBuyService.accIsBuy(pager, user);
                    return new Pager4EasyUI<AccessoriesBuy>(pager.getTotalRecords(), accessoriesBuys);
                }
                return null;
            } catch (Exception e) {
                logger.info("出现异常[370]" + e.getStackTrace());
                return null;
            }
        } else {
            logger.info("session失效重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "byAccNameSearch", method = RequestMethod.GET)
    public Pager4EasyUI<AccessoriesBuy> byAccNameSearch(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("accName") String accName, @Param("buyTimeStart") String buyTimeStart, @Param("buyTimeEnd") String buyTimeEnd) {

        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(accessoriesBuyService.countByBuyTimeScope(accName, buyTimeStart, buyTimeEnd, user));
                    List<AccessoriesBuy> accessoriesBuys = accessoriesBuyService.queryByBuyTimeScopeByAccNamePager(pager, accName, buyTimeStart, buyTimeEnd, user);
                    return new Pager4EasyUI<AccessoriesBuy>(pager.getTotalRecords(), accessoriesBuys);
                }
                return null;
            } catch (Exception e) {
                logger.info("出现异常" + e.getStackTrace());
                return null;
            }
        } else {
            logger.info("session失效重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "isPassCheck", method = RequestMethod.GET)
    public ControllerResult isPassCheck(@Param("accBuyId") String accBuyId, @Param("status") String status) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    if (!status.equals("Y")) {
                        accessoriesBuyService.updateAccBuyCheck("Y", accBuyId);
                        return ControllerResult.getSuccessResult("操作成功");
                    } else {
                        accessoriesBuyService.updateAccBuyCheck("N", accBuyId);
                    }
                    return ControllerResult.getSuccessResult("操作成功");
                }
                return ControllerResult.getFailResult("没有此权限访问");
            } catch (Exception e) {
                logger.info("出现异常" + e.getStackTrace());
                return ControllerResult.getFailResult("出现了一个错误");
            }
        } else {
            logger.info("session失效重新登入");
            return ControllerResult.getFailResult("登入失效，重新登入");
        }
    }


    @ResponseBody
    @RequestMapping(value = "query_default_count", method = RequestMethod.GET)
    public List<LineBasic> queryAll(@Param("companyId") String companyId) {
        if (SessionGetUtil.isUser()) {

                logger.info("默认查询本月下单统计报表显示");
                List<LineBasic> lineBasics = new ArrayList<LineBasic>();
                LineBasic lineBasic = new LineBasic();
                User user = SessionGetUtil.getUser();
                if (user.getCompanyId() != null && !user.getCompanyId().equals("")) {
                    companyId = user.getCompanyId();
                }
                lineBasic.setName("下单");
                dateDay(companyId, "count");
                lineBasic.setData(HighchartsData.doubleDayOne);
                lineBasic.setCategories(HighchartsData.strDay);
                lineBasics.add(lineBasic);
                return lineBasics;


        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }

    }

    @ResponseBody
    @RequestMapping(value = "query_condition_count", method = RequestMethod.GET)
    public List<LineBasic> queryConditionCount(@Param("start") String start, @Param("end") String end,
                                               @Param("type") String type, @Param("companyId") String companyId) {
        if (SessionGetUtil.isUser()) {

                logger.info("根据年，月，季度，周，日查询所有下单报表显示");
                List<LineBasic> lineBasics = new ArrayList<LineBasic>();
                LineBasic lineBasic = new LineBasic();
                lineBasic.setName("下单");
                User user = SessionGetUtil.getUser();
                if (user.getCompanyId() != null && !user.getCompanyId().equals("")) {
                    companyId = user.getCompanyId();
                }
                if (start != null && !start.equals("") && end != null && !end.equals("") && type != null && !type.equals("")) {
                    if (type.equals("year")) {
                        HighchartsData.setStrYear(start, end);
                        dataCondition(start, end, "count", type, "year", "two", companyId);
                        lineBasic.setData(HighchartsData.doubleYearTwo);
                        lineBasic.setCategories(HighchartsData.strYear);
                    } else if (type.equals("quarter")) {
                        dataCondition(start, end, "count", type, "quarter", "two", companyId);
                        lineBasic.setData(HighchartsData.doubleQuarterTwo);
                        lineBasic.setCategories(HighchartsData.strQuarter);
                    } else if (type.equals("month")) {
                        dataCondition(start, end, "count", type, "month", "two", companyId);
                        lineBasic.setData(HighchartsData.doubleMonthTwo);
                        lineBasic.setCategories(HighchartsData.strMonth);
                    } else if (type.equals("week")) {
                        HighchartsData.setStrWeek(start, end);
                        dataCondition(start, end, "count", type, "week", "two", companyId);
                        lineBasic.setData(HighchartsData.doubleWeekTwo);
                        lineBasic.setCategories(HighchartsData.strWeek);
                    } else if (type.equals("day")) {
                        dataCondition(start, end, "count", type, "day", "two", companyId);
                        lineBasic.setData(HighchartsData.doubleDayTwo);
                        lineBasic.setCategories(HighchartsData.strDay);
                    }
                }
                lineBasics.add(lineBasic);
                return lineBasics;


        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "query_default_pay", method = RequestMethod.GET)
    public List<LineBasic> queryPay(@Param("companyId") String companyId) {
        if (SessionGetUtil.isUser()) {

                logger.info("默认查询本月支付统计报表显示");
                List<LineBasic> lineBasics = new ArrayList<LineBasic>();
                LineBasic lineBasic = new LineBasic();
                User user = SessionGetUtil.getUser();
                if (user.getCompanyId() != null && !user.getCompanyId().equals("")) {
                    companyId = user.getCompanyId();
                }
                lineBasic.setName("支付");
                dateDay(companyId, "pay");
                lineBasic.setData(HighchartsData.doubleDayTwo);
                lineBasic.setCategories(HighchartsData.strDay);
                lineBasics.add(lineBasic);
                return lineBasics;


        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }

    }

    @ResponseBody
    @RequestMapping(value = "query_condition_pay", method = RequestMethod.GET)
    public List<LineBasic> queryConditionPay(@Param("start") String start, @Param("end") String end,
                                             @Param("type") String type, @Param("companyId") String companyId) {
        if (SessionGetUtil.isUser()) {

                logger.info("根据年，月，季度，周，日查询所有支付报表显示");
                List<LineBasic> lineBasics = new ArrayList<LineBasic>();
                LineBasic lineBasic = new LineBasic();
                lineBasic.setName("支付");
                User user = SessionGetUtil.getUser();
                if (user.getCompanyId() != null && !user.getCompanyId().equals("")) {
                    companyId = user.getCompanyId();
                }
                if (start != null && !start.equals("") && end != null && !end.equals("") && type != null && !type.equals("")) {
                    if (type.equals("year")) {
                        HighchartsData.setStrYear(start, end);
                        dataCondition(start, end, "pay", type, "year", "one", companyId);
                        lineBasic.setData(HighchartsData.doubleYearOne);
                        lineBasic.setCategories(HighchartsData.strYear);
                    } else if (type.equals("quarter")) {
                        dataCondition(start, end, "pay", type, "quarter", "one", companyId);
                        lineBasic.setData(HighchartsData.doubleQuarterOne);
                        lineBasic.setCategories(HighchartsData.strQuarter);
                    } else if (type.equals("month")) {
                        dataCondition(start, end, "pay", type, "month", "one", companyId);
                        lineBasic.setData(HighchartsData.doubleMonthOne);
                        lineBasic.setCategories(HighchartsData.strMonth);
                    } else if (type.equals("week")) {
                        HighchartsData.setStrWeek(start, end);
                        dataCondition(start, end, "pay", type, "week", "one", companyId);
                        lineBasic.setData(HighchartsData.doubleWeekOne);
                        lineBasic.setCategories(HighchartsData.strWeek);
                    } else if (type.equals("day")) {
                        dataCondition(start, end, "pay", type, "day", "one", companyId);
                        lineBasic.setData(HighchartsData.doubleDayOne);
                        lineBasic.setCategories(HighchartsData.strDay);
                    }
                }
                lineBasics.add(lineBasic);
                return lineBasics;


        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    /*  默认查询本月的下单,支付
   * */
    public void dateDay(String companyId, String type) {
        HighchartsData.doubleDayOne = new double[31];
        List<AccessoriesBuy> accessoriesBuys = null;
        if (type.equals("count")) {
            accessoriesBuys = accessoriesBuyService.queryByDefaultCount(companyId);
        } else if (type.equals("pay")) {
            accessoriesBuys = accessoriesBuyService.queryByDefaultPay(companyId);
        }

        int i = 0;
        double[] doubles = new double[accessoriesBuys.size()];
        String[] strs = new String[accessoriesBuys.size()];
        for (AccessoriesBuy io : accessoriesBuys) {
            if (type.equals("count")) {
                doubles[i] = io.getCoont();
            } else if (type.equals("pay")) {
                doubles[i] = io.getAccBuyMoney();
            }

            strs[i] = HighchartsData.dateFormat(io.getAccBuyCreatedTime(), "day");
            i++;
        }
        for (int j = 0, len = HighchartsData.strDay.length; j < len; j++) {
            for (int k = 0; k < strs.length; k++) {
                if (HighchartsData.strDay[j].equals(strs[k])) {
                    if (type.equals("count")) {
                        HighchartsData.doubleDayOne[j] = doubles[k];
                    } else if (type.equals("pay")) {
                        HighchartsData.doubleDayTwo[j] = doubles[k];
                    }
                }
            }
        }
    }

    /*
    *  按年，季度，月，周，日，查询,下单，支付
    * */
    public void dataCondition(String start, String end, String species, String type, String date, String inOut, String companyId) {
        HighchartsData.doubleDayOne = new double[31];
        HighchartsData.doubleDayTwo = new double[31];
        HighchartsData.doubleMonthOne = new double[12];
        HighchartsData.doubleMonthTwo = new double[12];
        HighchartsData.doubleQuarterOne = new double[4];
        HighchartsData.doubleQuarterTwo = new double[4];
        HighchartsData.doubleYearOne = new double[HighchartsData.yearLen];
        HighchartsData.doubleYearTwo = new double[HighchartsData.yearLen];
        HighchartsData.doubleWeekOne = new double[HighchartsData.weekLen];
        HighchartsData.doubleWeekTwo = new double[HighchartsData.weekLen];
        List<AccessoriesBuy> accessoriesBuys = new ArrayList<AccessoriesBuy>();
        if (species.equals("count")) {
            accessoriesBuys = accessoriesBuyService.queryByConditionCount(start, end, type, companyId);
        } else if (species.equals("pay")) {
            accessoriesBuys = accessoriesBuyService.queryByConditionPay(start, end, type, companyId);
        }
        int i = 0;
        double[] doubles = new double[accessoriesBuys.size()];
        String[] strs = new String[accessoriesBuys.size()];
        HighchartsData.len = 0;
        for (AccessoriesBuy io : accessoriesBuys) {
            if (species.equals("count")) {
                doubles[i] = io.getCoont();
            } else if (species.equals("pay")) {
                doubles[i] = io.getAccBuyMoney();
            }
            if (date.equals("month")) {
                strs[i] = HighchartsData.dateFormat(io.getAccBuyCreatedTime(), "month");
                HighchartsData.len = HighchartsData.strMonth.length;
            } else if (date.equals("day")) {
                strs[i] = HighchartsData.dateFormat(io.getAccBuyCreatedTime(), "day");
                HighchartsData.len = HighchartsData.strDay.length;
            } else if (date.equals("quarter")) {
                strs[i] = HighchartsData.dateFormat(io.getAccBuyCreatedTime(), "quarter");
                HighchartsData.len = HighchartsData.strQuarter.length;
            } else if (date.equals("year")) {
                strs[i] = HighchartsData.dateFormat(io.getAccBuyCreatedTime(), "year");
                HighchartsData.len = HighchartsData.strYear.length;
            } else if (date.equals("week")) {
                strs[i] = "第" + String.valueOf(HighchartsData.getWeek(HighchartsData.dateFormat(io.getAccBuyCreatedTime()))) + "周";
                HighchartsData.len = HighchartsData.strWeek.length;
            }
            i++;
        }
        if (date.equals("quarter")) {
            HighchartsData.getQuarter(strs, doubles, inOut);
        } else if (date.equals("month")) {
            HighchartsData.getMonth(strs, doubles, inOut);
        } else if (date.equals("day")) {
            HighchartsData.getDay(strs, doubles, inOut);
        } else if (date.equals("year")) {
            HighchartsData.getYear(strs, doubles, inOut);
        } else if (date.equals("week")) {
            HighchartsData.getWeek(strs, doubles, inOut);
        }
    }
}