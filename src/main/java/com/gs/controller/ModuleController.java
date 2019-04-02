package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.bean.Module;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.ModuleService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Xiao-Qiang on 2017/4/17.
 */
@Controller
@RequestMapping("module")
public class ModuleController {

    private Logger logger = (Logger) LoggerFactory.getLogger(ModuleController.class);

    @Resource
    private ModuleService moduleService;

    private String queryRole = Constants.SYSTEM_SUPER_ADMIN + "," + Constants.SYSTEM_ORDINARY_ADMIN;
    private String editRole = Constants.SYSTEM_SUPER_ADMIN + "," + Constants.SYSTEM_ORDINARY_ADMIN;

    /**
     * 显示模块信息
     * @return
     */
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String showModuleInfo() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
        if (!CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("无权访问，想要访问请联系管理员!");
            return "error/notPermission";
        }
        logger.info("显示模块信息");
        return "system/module";
    }

    /**
     * 分页查询所有模块
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "query_pager", method = RequestMethod.GET)
    public Pager4EasyUI<Module> queryPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        logger.info("分页查询所有模块");
        User user = SessionGetUtil.getUser();
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(moduleService.count(user));
        List<Module> modules = moduleService.queryByPager(pager, user);
        return new Pager4EasyUI<>(pager.getTotalRecords(), modules);
    }

    /**
     * 查询所有模块
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "query_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryAll() {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        try {
            logger.info("查询所有模块");
            User user = SessionGetUtil.getUser();
            List<Module> modules = moduleService.queryAll(user);
            List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<>();
            for (Module m : modules) {
                ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                comboBox4EasyUI.setId(m.getModuleId());
                comboBox4EasyUI.setText(m.getModuleName());
                comboBox4EasyUIs.add(comboBox4EasyUI);
            }
            return comboBox4EasyUIs;
        } catch (Exception e) {
            logger.info("发生异常，获取中断！");
            return null;
        }
    }

    /**
     * 添加模块
     * @param module
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add_module", method = RequestMethod.POST)
    public ControllerResult addModule(Module module) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole)) {
                logger.info("添加失败");
                return ControllerResult.getFailResult("添加失败，没有该权限操作");
            }
            logger.info("添加模块");
            module.setModuleStatus("Y");
            moduleService.insert(module);
            return ControllerResult.getSuccessResult("添加成功");
        } catch (Exception e) {
            logger.info("添加失败，出现了一个错误");
            return ControllerResult.getFailResult("添加失败，出现了一个错误");
        }
    }

    /**
     * 更新模块
     * @param module
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update_module", method = RequestMethod.POST)
    public ControllerResult updateModule(Module module) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole)) {
                logger.info("更新失败");
                return ControllerResult.getFailResult("更新失败，没有该权限操作");
            }
            logger.info("更新模块");
            module.setModuleStatus("Y");
            moduleService.update(module);
            return ControllerResult.getSuccessResult("修改成功");
        } catch (Exception e) {
            logger.info("更新失败，出现了一个错误");
            return ControllerResult.getFailResult("更新失败，出现了一个错误");
        }
    }

    /**
     * 更新模块状态
     * @param id
     * @param status
     * @return ControllerResult
     */
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
            logger.info("更新模块状态");
            if ("Y".equals(status)) {
                moduleService.active(id);
            } else if ("N".equals(status)) {
                moduleService.inactive(id);
            }
            return ControllerResult.getSuccessResult("更新成功");
        } catch (Exception e) {
            logger.info("操作失败，出现了一个错误");
            return ControllerResult.getFailResult("操作失败，出现了一个错误");
        }
    }

    /**
     * 通过状态查询模块
     * @param status
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryByStatus_module", method = RequestMethod.GET)
    public Pager4EasyUI<Module> queryByStatusModule(@Param("status") String status, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        if ("Y".equals(status)) {
            logger.info("分页查询可用的模块");
        } else {
            logger.info("分页查询不可用的模块");
        }
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(moduleService.countByStatus(status));
        List<Module> modules = moduleService.queryByStatusPager(status, pager);
        return new Pager4EasyUI<>(pager.getTotalRecords(), modules);
    }
}
