package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
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
import javax.naming.ldap.Control;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Levc on 2017/4/17.
 */
@Controller
@RequestMapping("accessoriesSale")
public class AccessoriesSaleController {

    private Logger logger = (Logger) LoggerFactory.getLogger(AccessoriesSaleController.class);

    @Resource
    private AccessoriesSaleService accessoriesSaleService;

    @Resource
    private AccessoriesService accessoriesService;

    @Resource
    private IncomingTypeService incomingTypeService;

    @Resource
    private IncomingOutgoingService incomingOutgoingService;

    /**
     * 可以查看的角色
     * 董事长、库管、销售员、超级管理员、普通管理员
     */
    private String queryRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_SALES + ","
            + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.COMPANY_REPERTORY + "," + Constants.SYSTEM_ORDINARY_ADMIN;

    /**
     * 可以操作的角色
     * 董事长、销售员、超级管理员
     */
    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.COMPANY_SALES;

    /**
     * 显示配件采购管理
     *
     * @return String
     */
    @RequestMapping("showAccessoriesSaleHome")
    private String showAccessoriesSaleHome() {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(queryRole)) {
                logger.info("显示采购主页");
                return "accessories/accessories_sale";
            }
            return "error/notPermission";
        }
        logger.info("Session已失效");
        return "index/notLogin";
    }

    /**
     * 分页查询采购信息
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "pager", method = RequestMethod.GET)
    private Pager4EasyUI<AccessoriesSale> queryByPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询采购信息");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(accessoriesSaleService.count(user));
                    List<AccessoriesSale> accessoriesSales = accessoriesSaleService.queryByPager(pager, user);
                    return new Pager4EasyUI<>(pager.getTotalRecords(), accessoriesSales);
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

    /**
     * 更新收入类型状态
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateStatus", method = RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id") String id, @Param("status") String status) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("更新收入类型状态");
                    if ("Y".equals(status)) {
                        accessoriesSaleService.active(id);
                    } else if ("N".equals(status)) {
                        accessoriesSaleService.inactive(id);
                    }
                    return ControllerResult.getSuccessResult("更新成功");
                }
                return ControllerResult.getFailResult("出现了一个错误");
            } catch (Exception e) {
                logger.info("出现异常" + e.getStackTrace());
                return ControllerResult.getFailResult("出现了一个错误");
            }
        } else {
            logger.info("session失效重新登入");
            return ControllerResult.getFailResult("登入失效，重新登入");
        }
    }

    /**
     * 冻结
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public ControllerResult remove(@Param("id") String id) {

        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {

                    accessoriesSaleService.inactive(id);
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

    /**
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "enable", method = RequestMethod.GET)
    public ControllerResult enable(@Param("id") String id) {

        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    accessoriesSaleService.active(id);
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

    /**
     *
     * @param accessoriesSale
     * @param lastCount
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addSale", method = RequestMethod.POST)
    public ControllerResult addSale(AccessoriesSale accessoriesSale, @Param("lastCount") String lastCount) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    User user = SessionGetUtil.getUser();

                    accessoriesSale.setAccSaleId(UUIDUtil.uuid());
                    accessoriesSale.setCompanyId(user.getCompanyId());

                    accessoriesSaleService.insert(accessoriesSale);

                    int lCount = Integer.valueOf(lastCount);

                    accessoriesService.updateIdle(accessoriesSale.getAccId(), lCount, user);

                    IncomingType incomingType = incomingTypeService.queryByName(Constants.ACC_IN);
                    IncomingOutgoing incomingOutgoing = new IncomingOutgoing();
                    incomingOutgoing.setCompanyId(user.getCompanyId());
                    incomingOutgoing.setInOutCreatedUser(user.getUserId());
                    incomingOutgoing.setInOutMoney(accessoriesSale.getAccSaleMoney());
                    incomingOutgoing.setInTypeId(incomingType.getInTypeId());
                    incomingOutgoingService.insert(incomingOutgoing);

                    return ControllerResult.getSuccessResult("添加成功");

                }
                return ControllerResult.getFailResult("没有此权限访问");
            } catch (Exception e) {
                logger.info("出现异常" + e.getStackTrace());
                return ControllerResult.getFailResult("大于库存数量");
            }
        } else {
            logger.info("session失效重新登入");
            return ControllerResult.getFailResult("登入失效，重新登入");
        }
    }

    /**
     *
     * @param userId
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "isReAdd", method = RequestMethod.GET)
    public ControllerResult isRerAdd(@Param("userId") String userId, @Param("userName") String userName) {

        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    User user = SessionGetUtil.getUser();
                    int ass = accessoriesSaleService.queryByUserIdIsSameResult(userId, userName, user);
                    System.out.println(ass);
                    if (ass != 0) {
                        return ControllerResult.getSuccessResult("可用");
                    } else {
                        return ControllerResult.getFailResult("重复用户");
                    }
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

    /**
     * 分页查询只销售信息
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "onlySale", method = RequestMethod.GET)
    private Pager4EasyUI<AccessoriesSale> onlySale(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {

        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    User user = SessionGetUtil.getUser();
                    logger.info("分页查询只销售信息");
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(accessoriesSaleService.onlySaleCount(user));
                    List<AccessoriesSale> accessoriesSales = accessoriesSaleService.queryOnlySale(pager, user);
                    return new Pager4EasyUI<>(pager.getTotalRecords(), accessoriesSales);
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

    /**
     *
     * @param pageNumber
     * @param pageSize
     * @param accName
     * @param userName
     * @param buyTimeStart
     * @param buyTimeEnd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public Pager4EasyUI<AccessoriesSale> search(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("accName") String accName, @Param("userName") String userName, @Param("buyTimeStart") String buyTimeStart, @Param("buyTimeEnd") String buyTimeEnd) {

        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(accessoriesSaleService.bySaleTimeScopeCount(accName, userName, buyTimeStart, buyTimeEnd, user));
                    List<AccessoriesSale> accessoriesSales = accessoriesSaleService.queryBySaleTimeScopeByAccNamePager(pager, accName, userName, buyTimeStart, buyTimeEnd, user);
                    return new Pager4EasyUI<>(pager.getTotalRecords(), accessoriesSales);
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

    /**
     *
     * @param accessoriesSale
     * @param lastCount
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public ControllerResult update(AccessoriesSale accessoriesSale, @Param("lastCount") String lastCount) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    User user = SessionGetUtil.getUser();
                    Accessories acc = accessoriesSale.getAccessories();

                    int iLastCount = Integer.valueOf(lastCount);

                    accessoriesSale.setCompanyId(user.getCompanyId());
                    accessoriesSale.setAccId(acc.getAccId());

                    accessoriesSaleService.update(accessoriesSale);
                    accessoriesService.updateIdle(accessoriesSale.getAccId(), iLastCount, user);
                }
            } catch (Exception e) {
                return ControllerResult.getFailResult("出现了一个错误");
            }
            return ControllerResult.getSuccessResult("更新成功");
        } else {
            logger.info("session失效重新登入");
            return ControllerResult.getFailResult("登入失效，重新登入");
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
