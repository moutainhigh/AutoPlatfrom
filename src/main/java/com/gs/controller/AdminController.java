package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Role;
import com.gs.bean.User;
import com.gs.bean.UserRole;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.*;
import com.gs.service.RoleService;
import com.gs.service.UserRoleService;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Xiao-Qiang on 2017/4/17.
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    private Logger logger = (Logger) LoggerFactory.getLogger(AdminController.class);

    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleService roleService;

    private String queryRole = Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;
    private String editRole = Constants.SYSTEM_SUPER_ADMIN;

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String showAdminInfo() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
        if (!CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("无权访问，想要访问请联系管理员!");
            return "error/notPermission";
        }
        logger.info("显示管理员信息");
        return "system/admin";
    }

    @ResponseBody
    @RequestMapping(value = "query_pager", method = RequestMethod.GET)
    public Pager4EasyUI<User> queryAdminPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        logger.info("分页查询所有管理员");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(userService.countAdmin());
        List<User> users = userService.queryByAdminPager(pager);
        return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
    }

    @ResponseBody
    @RequestMapping(value = "company_pager", method = RequestMethod.GET)
    public Pager4EasyUI<User> queryCompanyAdminPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        logger.info("分页查询汽修公司管理员");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(userService.countCompanyAdmin());
        List<User> users = userService.queryByCompanyAdminPager(pager);
        return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
    }

    @ResponseBody
    @RequestMapping(value = "system_pager", method = RequestMethod.GET)
    public Pager4EasyUI<User> querySystemAdminPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        logger.info("分页查询系统管理员");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(userService.countSystemAdmin());
        List<User> users = userService.queryBySystemAdminPager(pager);
        return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
    }

    @ResponseBody
    @RequestMapping(value = "select_query", method = RequestMethod.GET)
    public Pager4EasyUI<User> selectQuery(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("userName") String userName, @Param("userPhone") String userPhone, @Param("userEmail") String userEmail) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        logger.info("条件查询管理员");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(userService.countSelectAdmin(userName, userPhone, userEmail));
        List<User> users = userService.selectQuery(pager, userName, userPhone, userEmail);
        return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
    }

    @ResponseBody
    @RequestMapping(value = "add_admin", method = RequestMethod.POST)
    public ControllerResult addAdmin(User user) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        if (!CheckRoleUtil.checkRoles(editRole)) {
            logger.info("添加失败");
            return ControllerResult.getFailResult("添加失败，没有该权限操作");
        }
        try {
            logger.info("添加管理员");
            user.setUserId(UUIDUtil.uuid());
            user.setUserPwd(EncryptUtil.md5Encrypt(user.getUserPwd()));
            user.setUserIcon("/img/default.png");
            userService.insertAdmin(user);
            Role role = roleService.queryByName("systemOrdinaryAdmin");
            UserRole ur = new UserRole();
            ur.setRoleId(role.getRoleId());
            ur.setUserId(user.getUserId());
            userRoleService.insert(ur);
            return ControllerResult.getSuccessResult("添加成功");
        } catch (Exception e) {
            logger.info("添加失败，出现了一个错误");
            return ControllerResult.getFailResult("添加失败，出现了一个错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "update_admin", method = RequestMethod.POST)
    public ControllerResult updateAdmin(@Param("uIcon") String uIcon, @Param("user") User user, @Param("file") MultipartFile file, @Param("session") HttpSession session) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        if (!CheckRoleUtil.checkRoles(editRole)) {
            logger.info("更新失败");
            return ControllerResult.getFailResult("更新失败，没有该权限操作");
        }
        try {
            logger.info("更新管理员");
            if(file != null){
                String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
                String filePath = FileUtil.uploadPath(session,"\\" + fileName);
                String icon = "/uploads/"+ fileName;
                if(!file.isEmpty()){
                    file.transferTo(new File(filePath));
                    user.setUserIcon(icon);
                } else {
                    user.setUserIcon("img/default.png");
                }
            }else{
                user.setUserIcon(uIcon);
            }
            userService.updateAdmin(user);
            return ControllerResult.getSuccessResult("更新成功");
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
        if (!CheckRoleUtil.checkRoles(editRole)) {
            logger.info("更新状态失败");
            return ControllerResult.getFailResult("更新状态失败，没有该权限操作");
        }
        try {
            if (status.equals("Y")) {
                logger.info("激活管理员状态");
                userService.active(id);
            } else if (status.equals("N")) {
                logger.info("冻结管理员状态");
                userService.inactive(id);
            }
            return ControllerResult.getSuccessResult("更新成功");
        } catch (Exception e) {
            logger.info("操作失败，出现了一个错误");
            return ControllerResult.getFailResult("操作失败，出现了一个错误");
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

/*    @ResponseBody
    @RequestMapping(value = "queryRoleByUserId", method = RequestMethod.GET)
    public String queryRoleByUserId() {
        Role role = roleService.queryByUserId(SessionGetUtil.getUser().getUserId());
        if (role.getRoleName().equals("systemSuperAdmin")) {
            return "1";
        } else {
            return "0";
        }

    }*/
}
