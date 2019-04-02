package com.gs.controller;

import com.gs.common.Constants;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ch.qos.logback.classic.Logger;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author xiao-kang
 * @date 2017/4/22
 * 报表统计
 */
@Controller
@RequestMapping("/reportStatistics")
public class ReportStatisticsController {

    private Logger logger = (Logger) LoggerFactory.getLogger(ReportStatisticsController.class);

    /**
     * 可以查看的角色：董事长、财务员、超级管理员、普通管理员
     */
    private String queryRoleFinance = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_ACCOUNTING + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;

    /**
     * 可以查看的角色：董事长、采购员、超级管理员、普通管理员、销售员、库管
     */
    private String queryRoleAcc = Constants.COMPANY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.COMPANY_BUYER+ ","
            + Constants.COMPANY_SALES + "," + Constants.COMPANY_REPERTORY;

    @RequestMapping(value = "finance_page", method = RequestMethod.GET)
    public String showInOutPage() {
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRoleFinance)) {
                logger.info("显示财务统计页面");
                return "reportStatistics/finance_statistics";
            }
            return "error/notPermission";
        } else{
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    /**
     * 显示员工工单统计页面
     * @return
     */
    @RequestMapping(value = "staff_page", method = RequestMethod.GET)
    public String showStaff() {
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRoleFinance)) {
                logger.info("显示员工工单统计页面");
                return "reportStatistics/staff_statistics";
            }
            return "error/notPermission";
        }else{
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    /**
     * 显示下单统计页面
     * @return
     */
    @RequestMapping(value = "order_page", method = RequestMethod.GET)
    public String showOrder() {
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRoleAcc)) {
                logger.info("显示下单统计页面");
                return "supply/order_statistics";
            }
            return "error/notPermission";
        }else{
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    /**
     * 显示支付统计页面
     * @return
     */
    @RequestMapping(value = "pay_page", method = RequestMethod.GET)
    public String showPay() {
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRoleAcc)) {
                logger.info("显示支付统计页面");
                return "supply/pay_statistics";
            }
            return "error/notPermission";
        }else{
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    /**
     * 显示消费统计页面
     * @return
     */
    @RequestMapping(value = "consumption_page", method = RequestMethod.GET)
    public String showConsumption() {
        if(SessionGetUtil.isUser()) {
            //可以查看的角色：车主
            String queryRoleCustomer = Constants.CAR_OWNER;
            if(CheckRoleUtil.checkRoles(queryRoleCustomer)) {
                logger.info("显示消费统计页面");
                return "customer/consumption_statistics";
            }
            return "error/notPermission";
        }else{
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    /**
     * 显示维修保养统计页面
     * @return
     */
    @RequestMapping(value = "maintenance_page", method = RequestMethod.GET)
    public String showMaintenance() {
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRoleFinance)) {
                logger.info("显示维修保养统计页面");
                return "reportStatistics/maintenance_statistics";
            }
            return "error/notPermission";
        }else{
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    /**
     * 显示维修保养项目统计页面
     * @return
     */
    @RequestMapping(value = "maintenanceItems_page", method = RequestMethod.GET)
    public String showMaintenanceItems() {
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRoleFinance)) {
                logger.info("显示维修保养项目统计页面");
                return "reportStatistics/maintenance_items_statistics";
            }
            return "error/notPermission";
        }else{
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    /**
     * 显示库存统计页面
     * @return
     */
    @RequestMapping(value = "stock_page", method = RequestMethod.GET)
    public String showStock() {
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRoleAcc)) {
                logger.info("显示库存统计页面");
                return "reportStatistics/stock_statistics";
            }
            return "error/notPermission";
        }else{
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    /**
     * 显示配件使用统计页面
     * @return
     */
    @RequestMapping(value = "accessories_page", method = RequestMethod.GET)
    public String showAccessories() {
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRoleAcc)) {
                logger.info("显示配件使用统计页面");
                return "reportStatistics/accessories _usage_statistics";
            }
            return "error/notPermission";
        }else{
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }
}
