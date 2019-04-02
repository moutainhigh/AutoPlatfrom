package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.common.Constants;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.AccessoriesTypeService;
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
 * Created by GOD on 2017/4/17.
 */
@Controller
@RequestMapping("accessoriesType")
public class AccessoriesTypeController {

    private Logger logger = (Logger) LoggerFactory.getLogger(AccessoriesTypeController.class);

    @Resource
    private AccessoriesTypeService accessoriesTypeService;

    private String queryRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_REPERTORY + ","
            + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.SYSTEM_ORDINARY_ADMIN;

    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_REPERTORY;

    /**
     * 显示配件分类
     * @return
     */
    @RequestMapping(value = "type", method = RequestMethod.GET)
    private String type() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
        if (!CheckRoleUtil.checkRoles(queryRole)){
            logger.info("无权访问，想要访问请联系管理员!");
            return "error/notPermission";
        }
        logger.info("显示配件分类");
        return "accessories/accessories_type";
    }

    /**
     * 分页查询
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "pager", method = RequestMethod.GET)
    public Pager4EasyUI<AccessoriesType> queryPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效，请重新登入");
            return null;
        }
        logger.info("分页查询");
        User user = SessionGetUtil.getUser();
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(accessoriesTypeService.count(user));
        List<AccessoriesType> accessoriesTypes = accessoriesTypeService.queryByPager(pager, user);
        return new Pager4EasyUI<AccessoriesType>(pager.getTotalRecords(), accessoriesTypes);
    }

    /**
     * 添加配件类型
     * @param accessoriesType
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult addAccType(AccessoriesType accessoriesType) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole)){
                logger.info("添加失败");
                return ControllerResult.getFailResult("添加失败，没有该权限操作");
            }
            logger.info("添加");
            User loginUser = SessionGetUtil.getUser();
            accessoriesType.setCompanyId(loginUser.getCompanyId());
            accessoriesType.setAccTypeId(UUIDUtil.uuid());
            System.out.println(accessoriesType);
            accessoriesType.setAccTypeStatus("Y");
            accessoriesTypeService.insert(accessoriesType);
            return ControllerResult.getSuccessResult("添加成功");
        } catch (Exception e) {
            logger.info("添加失败，出现了一个错误");
            return ControllerResult.getFailResult("添加失败，出现了一个错误");
        }
    }

    /**
     * 添加配件类型
     * @param accessoriesType
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ControllerResult updateAccType(AccessoriesType accessoriesType) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole)) {
                logger.info("更新失败");
                return ControllerResult.getFailResult("更新失败,没有该权限的操作");
            }
            logger.info("更新");
            User loginUser = SessionGetUtil.getUser();
            accessoriesType.setCompanyId(loginUser.getCompanyId());
            accessoriesType.setAccTypeStatus("Y");
            accessoriesTypeService.update(accessoriesType);
            return ControllerResult.getSuccessResult("修改成功");

        } catch (Exception e) {
            logger.info("修改失败，出现了一个错误");
            return ControllerResult.getFailResult("修改失败，出现了一个错误");
        }
    }

    /**
     * 更新状态
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update_status", method = RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id") String id, @Param("status") String status) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole)){
                logger.info("更新状态失败");
                return ControllerResult.getFailResult("更新状态失败,没有该权限的操作");
            }
            logger.info("更新状态");
            if ("Y".equals(status)) {
                accessoriesTypeService.active(id);
            } else if ("N".equals(status)) {
                accessoriesTypeService.inactive(id);
            }
            return ControllerResult.getSuccessResult("更新成功");
        } catch (Exception e) {
            logger.info("操作失败，出现了一个错误");
            return ControllerResult.getFailResult("操作失败，出现了一个错误");
        }
    }

    /**
     * 查询配件分类
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "accessoriesType_All", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryUserAll() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return null;
        }
        logger.info("查询配件分类");
        User user = SessionGetUtil.getUser();
        List<AccessoriesType> accessoriesTypeList = accessoriesTypeService.queryAll(user);
        List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<>();
        for (AccessoriesType accessoriesTypes : accessoriesTypeList) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(accessoriesTypes.getAccTypeId());
            comboBox4EasyUI.setText(accessoriesTypes.getAccTypeName());
            comboBox4EasyUIs.add(comboBox4EasyUI);
        }
        return comboBox4EasyUIs;
    }

    /**
     * 分页查询可用的配件分类
     * @param status
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryByStatus_AccType", method = RequestMethod.GET)
    public Pager4EasyUI<AccessoriesType> queryByStatusAccType(@Param("status") String status, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效，请重新登入");
            return null;
        }
        if ("Y".equals(status)) {
            logger.info("分页查询可用的配件分类");
        } else {
            logger.info("分页查询不可用的配件分类");
        }
        User user = SessionGetUtil.getUser();
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(accessoriesTypeService.countByStatus(status, user));
        List<AccessoriesType> accessoriesTypes = accessoriesTypeService.queryByStatusPager(status, pager, user);
        return new Pager4EasyUI<>(pager.getTotalRecords(), accessoriesTypes);
    }

    /**
     * 条件查询配件分类
     * @param pageNumber
     * @param pageSize
     * @param accTypeName
     * @param companyId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryByCondition", method = RequestMethod.GET)
    public Pager4EasyUI<AccessoriesType> queryByCondition(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize,
                                                          @Param("accTypeName") String accTypeName, @Param("companyId") String companyId) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效，请重新登入");
            return null;
        }
        logger.info("条件查询配件分类");
        User user = SessionGetUtil.getUser();
        AccessoriesType accessoriesType = new AccessoriesType();
        accessoriesType.setAccTypeName(accTypeName);
        accessoriesType.setCompanyId(companyId);
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        List<AccessoriesType> accessoriesTypes = new ArrayList<>();
        pager.setTotalRecords(accessoriesTypeService.countByCondition(accessoriesType, user));
        accessoriesTypes = accessoriesTypeService.queryByCondition(pager, accessoriesType, user);

        return new Pager4EasyUI<>(pager.getTotalRecords(), accessoriesTypes);
    }
}