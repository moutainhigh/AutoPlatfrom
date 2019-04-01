package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.bean.Role;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.RoleService;
import com.gs.service.VilidateService;
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
 * 温鑫
 * 人员角色管理
 * Created by Star on 2017/4/17.
 */

@Controller
@RequestMapping("role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private VilidateService vilidateService;

    private Logger logger = (Logger) LoggerFactory.getLogger(RoleController.class);

    private String queryRole = Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;
    private String editRole = Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;

    @RequestMapping(value = "info", method = RequestMethod.GET)
    private String showRoleInfo() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
        if (!CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("无权访问，想要访问请联系管理员!");
            return "error/notPermission";
        }
        logger.info(" 人员角色管理页面");
        return "system/role";
    }

    @ResponseBody
    @RequestMapping(value = "add_role", method = RequestMethod.POST)
    public ControllerResult addRole(Role role) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole)) {
                logger.info("添加失败");
                return ControllerResult.getFailResult("添加失败，没有该权限操作");
            }
            logger.info("角色添加");
            roleService.insert(role);
            return ControllerResult.getSuccessResult("添加成功");
        } catch (Exception e) {
            logger.info("添加失败，出现了一个错误");
            return ControllerResult.getFailResult("添加失败，出现了一个错误");
        }
    }


    @ResponseBody
    @RequestMapping(value = "query_pager", method = RequestMethod.GET)
    public Pager4EasyUI<Role> queryPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        User user = SessionGetUtil.getUser();
        logger.info("分页查询所有角色");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(roleService.count(user));
        List<Role> roles = roleService.queryByPager(pager, user);
        return new Pager4EasyUI<Role>(pager.getTotalRecords(), roles);
    }

    @ResponseBody
    @RequestMapping(value = "update_role", method = RequestMethod.POST)
    public ControllerResult updateRole(Role role) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole)) {
                logger.info("修改失败");
                return ControllerResult.getFailResult("修改失败，没有该权限操作");
            }
            logger.info("角色修改");
            roleService.update(role);
            return ControllerResult.getSuccessResult(" 修改成功");
        } catch (Exception e) {
            logger.info("更新失败，出现了一个错误");
            return ControllerResult.getFailResult("更新失败，出现了一个错误");
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
                logger.info("修改状态失败");
                return ControllerResult.getFailResult("修改状态失败，没有该权限操作");
            }
            logger.info("状态修改");
            if (status.equals("Y")) {
                roleService.inactive(id);
            } else if (status.equals("N")) {
                roleService.active(id);
            }
            return ControllerResult.getSuccessResult(" 修改成功");
        } catch (Exception e) {
            logger.info("操作失败，出现了一个错误");
            return ControllerResult.getFailResult("操作失败，出现了一个错误");
        }

    }

    @ResponseBody
    @RequestMapping(value = "query_cAdminAndSOAdmin", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryCAdminAndSOAdmin() {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        try {
            logger.info("查询添加管理员时需要的下拉条件");
            List<Role> roles = roleService.queryCAdminAndSOAdmin();
            List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
            for (Role r : roles) {
                ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                comboBox4EasyUI.setId(r.getRoleId());
                comboBox4EasyUI.setText(r.getRoleDes());
                comboBox4EasyUIs.add(comboBox4EasyUI);
            }
            return comboBox4EasyUIs;
        } catch (Exception e) {
            logger.info("发生异常，获取中断！");
            return null;
        }
    }
}
