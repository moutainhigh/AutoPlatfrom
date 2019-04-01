package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Supply;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.DateUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.SupplyService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Asa on 2017/4/17.
 */
@Controller
@RequestMapping("supply")
public class SupplyController {
        private Logger logger = (Logger) LoggerFactory.getLogger(SupplyTypeController.class);

        @Resource
        private SupplyService supplyService;

        private String queryRole = Constants.COMPANY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.SYSTEM_ORDINARY_ADMIN;

        private String editRole = Constants.COMPANY_ADMIN;

        @RequestMapping("/info")
        public String supplierInfo() {
            if (SessionGetUtil.isUser()) {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("进入供应商信息页");
                    return "supply/supply_info";
                }
                return "error/notPermission";
            } else {
                logger.info("Session已失效，请重新登入");
                return "index/notLogin";
            }
        }

        @ResponseBody
        @RequestMapping("queryByPager")
        public Pager4EasyUI<Supply> queryByPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, @Param("status")String status) {
            if (SessionGetUtil.isUser()) {
//                try {
                    if (CheckRoleUtil.checkRoles(queryRole)) {
                        logger.info("分页查询供应商分类");
                        User user = SessionGetUtil.getUser();
                        Pager pager = new Pager();
                        pager.setPageNo(Integer.valueOf(pageNumber));
                        pager.setPageSize(Integer.valueOf(pageSize));
                        List<Supply> supplys = new ArrayList<Supply>();
                        if (status.equals("ALL")) {
                            pager.setTotalRecords(supplyService.count(user));
                            supplys = supplyService.queryByPager(pager, user);
                        } else {
                            pager.setTotalRecords(supplyService.countByStatus(status, user));
                            supplys = supplyService.queryPagerByStatus(pager, status, user);
                        }
                        return new Pager4EasyUI<Supply>(pager.getTotalRecords(), supplys);
                    }
                    return null;
//                } catch (Exception e) {
//                    logger.info("查询失败，出现了异常");
//                    return null;
//                }
            } else {
                logger.info("Session已失效，请重新登入");
                return null;
            }
        }

    @ResponseBody
    @RequestMapping(value = "conditionPager", method = RequestMethod.GET)
    public Pager4EasyUI<Supply> queryPagerByCondition(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,
                                                          @Param("supplyName")String supplyName, @Param("supplyPricipal")String supplyPricipal,
                                                          @Param("supplyTypeId")String supplyTypeId, @Param("companyId")String companyId) {
        if (SessionGetUtil.isUser()) {
//            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("根据条件分页查询供应商分类");
                    User user = SessionGetUtil.getUser();
                    Supply supply = new Supply();
                    supply.setSupplyName(supplyName);
                    supply.setSupplyPricipal(supplyPricipal);
                    supply.setSupplyTypeId(supplyTypeId);
                    supply.setCompanyId(companyId);
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<Supply> supplys = new ArrayList<Supply>();
                    pager.setTotalRecords(supplyService.countByCondition(supply, user));
                    supplys = supplyService.queryPagerByCondition(pager, supply, user);
                    return new Pager4EasyUI<Supply>(pager.getTotalRecords(), supplys);
                }
                return null;
//            } catch (Exception e) {
//                logger.info("查询失败，出现了异常");
//                return null;
//            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

        @ResponseBody
        @RequestMapping("add")
        public ControllerResult add(Supply supply) {
            if (SessionGetUtil.isUser()) {
                try {
                    if (CheckRoleUtil.checkRoles(editRole)) {
                        logger.info("添加供应商");
                        User loginUser = SessionGetUtil.getUser();
                        supply.setCompanyId(loginUser.getCompanyId());
                        supplyService.insert(supply);
                        return ControllerResult.getSuccessResult("添加成功");
                    }
                    return ControllerResult.getFailResult("添加供应商失败，没有该权限操作");
                }catch (Exception e) {
                    logger.info("添加供应商失败，出现了一个错误");
                    return ControllerResult.getFailResult("添加供应商失败，出现了一个错误");
                }
            } else {
                logger.info("Session已失效，请重新登入");
                return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
            }
        }

        @ResponseBody
        @RequestMapping("edit")
        public ControllerResult update(Supply supply) {
            if (SessionGetUtil.isUser()) {
                try {
                    if (CheckRoleUtil.checkRoles(editRole)) {
                        logger.info("修改供应商分类");
                        User loginUser = SessionGetUtil.getUser();
                        supply.setCompanyId(loginUser.getCompanyId());
                        supplyService.update(supply);
                        return ControllerResult.getSuccessResult("修改供应商成功");
                    }
                    return ControllerResult.getFailResult("修改供应商失败，没有该权限操作");
                }catch (Exception e) {
                    logger.info("修改供应商失败，出现了一个错误");
                    return ControllerResult.getFailResult("添加供应商失败，出现了一个错误");
                }
            } else {
                logger.info("Session已失效，请重新登入");
                return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
            }
        }

        @ResponseBody
        @RequestMapping(value = "updateStatus", method = RequestMethod.GET)
        public ControllerResult updateStatus(@Param("id")String id, @Param("status")String status) {
            if (SessionGetUtil.isUser()) {
                try{
                    if (CheckRoleUtil.checkRoles(editRole)) {
                        logger.info("更新供应商状态");
                        if (status.equals("Y")) {
                            supplyService.active(id);
                        } else if (status.equals("N")) {
                            supplyService.inactive(id);
                        }
                        return ControllerResult.getSuccessResult("更新成功");
                    }
                    return ControllerResult.getSuccessResult("更新供应商状态成功");
                } catch (Exception e) {
                    logger.info("更新供应商状态失败，出现了一个错误");
                    return ControllerResult.getFailResult("更新供应商状态失败，出现了一个错误");
                }
            } else {
                logger.info("Session已失效，请重新登入");
                return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
            }
        }

    @ResponseBody
    @RequestMapping(value = "queryAll", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryAll() {
        if (SessionGetUtil.isUser()) {
            try {
                logger.info("查询供应商");
                User user = SessionGetUtil.getUser();
                List<Supply> supplyList = supplyService.queryAll(user);
                List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
                for (Supply supplys : supplyList) {
                    ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                    comboBox4EasyUI.setId(supplys.getSupplyId());
                    comboBox4EasyUI.setText(supplys.getSupplyName());
                    comboBox4EasyUIs.add(comboBox4EasyUI);
                }
                return comboBox4EasyUIs;
            } catch (Exception e) {
                logger.info("更查询失败，出现了异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

}
