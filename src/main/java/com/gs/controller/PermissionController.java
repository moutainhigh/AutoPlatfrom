package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.bean.*;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.ModuleService;
import com.gs.service.PermissionService;
import com.gs.service.RolePermissionService;
import com.gs.service.RoleService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Xiao-Qiang on 2017/4/17.
 */
@Controller
@RequestMapping("permission")
public class PermissionController {

    private Logger logger = (Logger) LoggerFactory.getLogger(PermissionController.class);

    @Resource
    private RoleService roleService;
    @Resource
    private ModuleService moduleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RolePermissionService rolePermissionService;

    private String queryRole = Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;
    private String editRole = Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public ModelAndView showPermissionInfo() {
        ModelAndView mav = new ModelAndView();
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            mav.setViewName("index/notLogin");
            return mav;
        }
        if (!CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("无权访问，想要访问请联系管理员!");
            mav.setViewName("error/notPermission");
            return mav;
        }
        User user = SessionGetUtil.getUser();
        logger.info("显示权限信息");
        logger.info("查询所有角色");
        logger.info("查询所有模块");
        mav.setViewName("system/permission");
        List<Role> roles = roleService.queryAll(user);
        mav.addObject("roles", roles);
        List<Module> modules = moduleService.queryAll(user);
        mav.addObject("modules", modules);
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "query_pager", method = RequestMethod.GET)
    public Pager4EasyUI<Permission> queryPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        User user = SessionGetUtil.getUser();
        logger.info("分页查询所有权限");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(permissionService.count(user));
        List<Permission> permissions = permissionService.queryByPager(pager, user);
        return new Pager4EasyUI<Permission>(pager.getTotalRecords(), permissions);
    }

    @ResponseBody
    @RequestMapping(value = "module_pager", method = RequestMethod.GET)
    public Pager4EasyUI<Permission> queryByModulePager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("moduleId") String moduleId) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        logger.info("根据模块来分页查询权限");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(permissionService.countModule(moduleId));
        List<Permission> permissions = permissionService.queryByModulePager(moduleId, pager);
        return new Pager4EasyUI<Permission>(pager.getTotalRecords(), permissions);
    }

    @ResponseBody
    @RequestMapping(value = "status_pager", method = RequestMethod.GET)
    public Pager4EasyUI<Permission> queryByStatusPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("moduleId") String status) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        if (status.equals("Y")) {
            logger.info("分页查询可用的权限");
        } else {
            logger.info("分页查询不可用的权限");
        }
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(permissionService.countStatus(status));
        List<Permission> permissions = permissionService.queryByStatusPager(status, pager);
        return new Pager4EasyUI<Permission>(pager.getTotalRecords(), permissions);
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
            logger.info("更新权限状态");
            if (status.equals("Y")) {
                permissionService.active(id);
            } else if (status.equals("N")) {
                permissionService.inactive(id);
            }
            return ControllerResult.getSuccessResult("更新成功");
        } catch (Exception e) {
            logger.info("操作失败，出现了一个错误");
            return ControllerResult.getFailResult("操作失败，出现了一个错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "add_permission", method = RequestMethod.POST)
    public ControllerResult addPermission(Permission permission) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole)) {
                logger.info("添加失败");
                return ControllerResult.getFailResult("添加失败，没有该权限操作");
            }
            logger.info("添加权限信息");
            permissionService.insert(permission);
            return ControllerResult.getSuccessResult("添加成功");
        } catch (Exception e) {
            logger.info("添加失败，出现了一个错误");
            return ControllerResult.getFailResult("添加失败，出现了一个错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "update_permission", method = RequestMethod.POST)
    public ControllerResult updatePermission(Permission permission) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole)) {
                logger.info("修改失败");
                return ControllerResult.getFailResult("修改失败，没有该权限操作");
            }
            logger.info("修改权限信息");
            System.out.println("" + permission.getPermissionName() +
                    ", " + permission.getPermissionZHName() +
                    ", " + permission.getModuleId() +
                    ", " + permission.getPermissionDes()
            );
            permissionService.update(permission);
            return ControllerResult.getSuccessResult("修改成功");
        } catch (Exception e) {
            logger.info("修改失败，出现了一个错误");
            return ControllerResult.getFailResult("修改失败，出现了一个错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "roleIdOrModuleId_permission", method = RequestMethod.GET)
    public List<PermissionInfo> queryByRoleIdOrModuleId(@Param("roleId") String roleId, @Param("moduleId") String moduleId) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        logger.info("根据角色和模块查询拥有的权限");
        List<Permission> permissions = permissionService.queryByModuleId(moduleId);
        List<String> str = rolePermissionService.queryByRoleIdOrMeduleId(roleId, moduleId);
        List<PermissionInfo> pis = new ArrayList<PermissionInfo>();
        for (Permission p : permissions) {
            int i = 0;
            PermissionInfo pi = new PermissionInfo();
            pi.setPermissionId(p.getPermissionId());
            pi.setPermissionName(p.getPermissionZHName());
            pi.setPermissionDes(p.getPermissionDes());
            for (String s : str) {
                i++;
                if (p.getPermissionZHName().equals(s)) {
                    pi.setStatus(1);
                    break;
                } else if (i == str.size()) {
                    pi.setStatus(0);
                }
            }
            pis.add(pi);
        }
        return pis;
    }

    @ResponseBody
    @RequestMapping(value = "addByRole_permission", method = RequestMethod.GET)
    public ControllerResult addPermission(@Param("permissionIds") String[] permissionIds, @Param("roleId") String roleId) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole)) {
                logger.info("添加失败");
                return ControllerResult.getFailResult("添加失败，没有该权限操作");
            }
            if (permissionIds.length == 1) {
                logger.info("添加单个权限");
            } else if (permissionIds.length > 1) {
                logger.info("添加所有权限");
            }
            List<RolePermission> rps = new ArrayList<RolePermission>();
            for (int i = 0; i < permissionIds.length; i++) {
                RolePermission rp = new RolePermission();
                rp.setRoleId(roleId);
                rp.setPermissionId(permissionIds[i]);
                rps.add(rp);
            }
            rolePermissionService.addByRoleIdAndPermissionId(rps);
            return ControllerResult.getSuccessResult("成功添加");
        } catch (Exception e) {
            logger.info("添加失败，出现了一个错误");
            return ControllerResult.getFailResult("添加失败，出现了一个错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "delByRole_permission", method = RequestMethod.GET)
    public ControllerResult delPermission(@Param("permissionIds") String[] permissionIds, @Param("roleId") String roleId) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole)) {
                logger.info("删除失败");
                return ControllerResult.getFailResult("删除失败，没有该权限操作");
            }
            if (permissionIds.length == 1) {
                logger.info("删除单个权限");
            } else if (permissionIds.length > 1) {
                logger.info("删除所有权限");
            }
            rolePermissionService.delByRoleIdAndPermissionId(permissionIds, roleId);
            return ControllerResult.getSuccessResult("成功移除");
        } catch (Exception e) {
            logger.info("删除失败，出现了一个错误");
            return ControllerResult.getFailResult("删除失败，出现了一个错误");
        }
    }
}
