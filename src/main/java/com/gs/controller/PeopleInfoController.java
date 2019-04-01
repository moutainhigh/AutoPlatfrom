package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.bean.*;
import com.gs.common.Constants;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.*;
import com.gs.service.CompanyService;
import com.gs.service.RoleService;
import com.gs.service.UserRoleService;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 温鑫
 * 人员基本信息
 * Created by Star on 2017/4/17.
 */

@Controller
@RequestMapping("peopleManage")
public class PeopleInfoController {

    private Logger logger = (Logger) LoggerFactory.getLogger(PeopleInfoController.class);

    @Resource
    private UserService userService;

    @Resource
    private CompanyService companyService;


    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RoleService roleService;

    private String queryRole = Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.COMPANY_HUMAN_MANAGER
            + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.COMPANY_ADMIN
            + "," + Constants.COMPANY_ACCOUNTING + "," + Constants.COMPANY_BUYER
            + "," + Constants.COMPANY_REPERTORY + "," + Constants.COMPANY_ARTIFICER;

    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_HUMAN_MANAGER;

    private String editRole1 = Constants.COMPANY_ADMIN;

    @RequestMapping(value = "people_info", method = RequestMethod.GET)
    private String peopleInfo() {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(queryRole)) {
                logger.info(" 人员基本信息页面");
                return "peopleManage/people_info";
            }
            return "error/notPermission";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    @ResponseBody
    @RequestMapping(value = "peopleInfo_insert", method = RequestMethod.POST)
    public ControllerResult infoInsert(User user, Company company, UserRole userRole,HttpSession session){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("人员信息添加");
                    String peopleId = UUIDUtil.uuid();
                    user.setUserId(peopleId);
                    User user1 = (User)session.getAttribute("user");
                    user.setCompanyId(user1.getCompanyId());
                    user.setUserStatus("Y");
                    user.setUserIcon("/img/default.png");
                    Role role = roleService.queryByName("companyEmp");
                    userRole.setUserId(user.getUserId());
                    userRole.setRoleId(role.getRoleId());
                    user.setUserPwd(EncryptUtil.md5Encrypt(user.getUserPwd()));
                    userRoleService.insert(userRole);
                    userService.insert(user);
                    return ControllerResult.getSuccessResult("添加信息成功");
                }
                return ControllerResult.getFailResult("添加信息失败，没有该权限操作");
            } catch (Exception e) {
                logger.info("添加信息失败，出现了异常");
                return ControllerResult.getFailResult("添加信息失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登录信息已失效，请重新登录");
        }
    }

    @ResponseBody
    @RequestMapping(value = "peoplePhone_verification", method = RequestMethod.GET)
    public String verificationPhone(@Param("userPhone")String userPhone, @Param("editPhone") String editPhone) {
        try {
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editPhone.equals(userPhone)) {
                int count = userService.queryPhone(userPhone);
                if (count > 1 || count == 1) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("手机号验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "peopleEmail_verification", method = RequestMethod.GET)
    public String verificationEmail(@Param("userEmail")String userEmail, @Param("editEmail") String editEmail) {
        try {
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editEmail.equals(userEmail)) {
                int count = userService.queryEmail(userEmail);
                if (count > 1 || count == 1) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("邮箱验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "peopleIdentity_verification", method = RequestMethod.GET)
    public String verificationIdentity(@Param("userIdentity")String userIdentity, @Param("editIdentity")String editIdentity) {
        try {
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (!editIdentity.equals(userIdentity)) {
                int count = userService.queryIdentity(userIdentity);
                if (count > 1 || count == 1) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("身份证验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "peopleInfo_pager", method= RequestMethod.GET)
    public Pager4EasyUI<User> info_pager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询所有可用员工");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(userService.countPeopleEmp(user));
                    List<User> users = userService.queryPeoplePager(pager, user);
                    return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
                }
                return null;
            } catch (Exception e) {
                logger.info("分页查询失败，出现了异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }


    @ResponseBody
    @RequestMapping(value = "peopleInfo_pagerStatus", method= RequestMethod.GET)
    public Pager4EasyUI<User> infoStatus_pager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询所有不可用员工");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(userService.countStatusEmp(user));
                    List<User> users = userService.queryPeoplePagerStatus(pager, user);
                    return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
                }
                return null;
            } catch (Exception e) {
                logger.info("分页查询失败，出现了异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }


    @ResponseBody
    @RequestMapping(value = "peopleInfoAll", method= RequestMethod.GET)
    public Pager4EasyUI<User> infoAll_pager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, HttpSession session){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询所有员工");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(userService.countAllEmp(user));
                    List<User> users = userService.queryPeoplePagerAll(pager, user);
                    return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
                }
                return null;
            } catch (Exception e) {
                logger.info("分页查询失败，出现了异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }


    @ResponseBody
    @RequestMapping(value = "selectPeopleInfo", method= RequestMethod.GET)
    public Pager4EasyUI<User> selectInfo_pager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, @Param("userPhone")String userPhone, @Param("userName")String userName, @Param("userEmail")String userEmail, @Param("roleName")String roleName, @Param("companyName")String companyName){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页条件查询所有员工信息");
                    User user = new User();
                    user.setUserPhone(userPhone);
                    user.setUserName(userName);
                    user.setUserEmail(userEmail);
                    Role role = new Role();
                    role.setRoleId(roleName);
                    Company company = new Company();
                    company.setCompanyId(companyName);
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(userService.countSelectQueryEmp(user, role, company));
                    List<User> users = userService.selectQueryEmp(pager, user, role, company);
                    return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
                }
                return null;
            } catch (Exception e) {
                logger.info("分页查询失败，出现了异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }


    @ResponseBody
    @RequestMapping(value = "peopleInfo_update", method = RequestMethod.POST)
    public ControllerResult info_update(String uIcon, User user, MultipartFile file, HttpSession session, HttpServletRequest request, Company company) throws IOException {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("信息修改");
                    if(file != null){
                        String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
                        String filePath = FileUtil.uploadPath(session,"\\" + fileName);
                        String icon = "/uploads/"+ fileName;
                        if(!file.isEmpty()){
                            file.transferTo(new File(filePath));
                            user.setUserIcon(icon);
                        } else {
                            user.setUserIcon("/img/default.png");
                        }
                    }else{
                        user.setUserIcon(uIcon);
                    }
                    userService.update(user);
                    return ControllerResult.getSuccessResult("修改信息成功");
                }
                return ControllerResult.getFailResult("修改信息失败，没有该权限操作");
            } catch (Exception e) {
                logger.info("修改信息失败，出现了异常");
                return ControllerResult.getFailResult("修改信息失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登录信息已失效，请重新登录");
        }
    }
    @ResponseBody
    @RequestMapping(value = "peopleRole_update", method = RequestMethod.POST)
    public ControllerResult updateRole(UserRole userRole) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("角色分配");
                    userRoleService.updateByRole(userRole);
                    return ControllerResult.getSuccessResult("角色分配成功");
                }
                return ControllerResult.getFailResult("角色分配失败，没有权限操作");
            } catch (Exception e) {
                logger.info("角色分配失败，出现了一个错误");
                return ControllerResult.getFailResult("角色分配失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登录信息已失效，请重新登录");
        }
    }

    @ResponseBody
    @RequestMapping(value = "peopleInfo_status", method = RequestMethod.GET)
    public ControllerResult info_status(@Param("id")String id, @Param("status")String status){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole1)) {
                    logger.info("状态修改");
                    if(status.equals("Y")){
                        userService.inactive(id);
                    } else {
                        userService.active(id);
                    }
                    return ControllerResult.getSuccessResult("修改状态成功");
                }
                return ControllerResult.getFailResult("修改状态失败，没有权限操作");
            } catch (Exception e) {
                logger.info("修改状态失败，出现了一个错误");
                return ControllerResult.getFailResult("修改状态失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登录信息已失效，请重新登录");
        }
    }

    @ResponseBody
    @RequestMapping(value = "user_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryUserAll() {
        try {
            logger.info("查询员工");
            User user1 = SessionGetUtil.getUser();
            List<User> users = userService.queryAll(user1);
            List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
            for (User user : users) {
                ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                comboBox4EasyUI.setId(user.getUserId());
                comboBox4EasyUI.setText(user.getUserName());
                comboBox4EasyUIs.add(comboBox4EasyUI);
            }
            return comboBox4EasyUIs;
        } catch (Exception e) {
            logger.info("查询员工失败，出现了一个错误");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "user_company", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> userCompany() {
        try {
            logger.info("查询全部公司");
            User user = SessionGetUtil.getUser();
            List<Company> roles = companyService.queryAll(user);
            List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
            for (Company company : roles) {
                ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                comboBox4EasyUI.setId(company.getCompanyId());
                comboBox4EasyUI.setText(company.getCompanyName());
                comboBox4EasyUIs.add(comboBox4EasyUI);
            }
            return comboBox4EasyUIs;
        } catch (Exception e) {
            logger.info("查询全部公司失败，出现了一个错误");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "self_user", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> selfUser(HttpSession session) {
        try {
            logger.info("查询本公司员工");
            User user1 = SessionGetUtil.getUser();
            List<User> users = userService.queryByCompanyRole(user1);
            List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
            for (User user : users) {
                ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                comboBox4EasyUI.setId(user.getUserId());
                comboBox4EasyUI.setText(user.getUserName());
                comboBox4EasyUIs.add(comboBox4EasyUI);
            }
            return comboBox4EasyUIs;
        } catch (Exception e) {
            logger.info("查询本公司员工失败，出现了一个错误");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryRole_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> roleAll() {
        try {
            logger.info("查询所有公司角色");
            List<Role> roles = roleService.queryRole();
            List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
            for (Role role : roles) {
                ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                comboBox4EasyUI.setId(role.getRoleId());
                comboBox4EasyUI.setText(role.getRoleDes());
                comboBox4EasyUIs.add(comboBox4EasyUI);
            }
            return comboBox4EasyUIs;
        } catch (Exception e) {
            logger.info("查询所有公司角色失败，出现了一个错误");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "role_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryRoleAll() {
        try {
            logger.info("查询部分公司角色");
            List<Role> roles = roleService.queryByCompanyRole();
            List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
            for (Role role : roles) {
                ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                comboBox4EasyUI.setId(role.getRoleId());
                comboBox4EasyUI.setText(role.getRoleDes());
                comboBox4EasyUIs.add(comboBox4EasyUI);
            }
            return comboBox4EasyUIs;
        } catch (Exception e) {
            logger.info("查询部分公司角色失败，出现了一个错误");
            return null;
        }
    }


    @ResponseBody
    @RequestMapping(value = "companyRole_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryCompanyRoleAll() {
        try {
            logger.info("查询公司角色");
            List<Role> roles = roleService.queryByCompanyRoleAll();
            List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
            for (Role role : roles) {
                ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                comboBox4EasyUI.setId(role.getRoleId());
                comboBox4EasyUI.setText(role.getRoleDes());
                comboBox4EasyUIs.add(comboBox4EasyUI);
            }
            return comboBox4EasyUIs;
        } catch (Exception e) {
            logger.info("查询公司角色失败，出现了一个错误");
            return null;
        }
    }


    @ResponseBody
    @RequestMapping(value = "query_user", method = RequestMethod.GET)
    public Pager4EasyUI<User> queryByUser(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,HttpSession session){
        logger.info("分页查询所有员工");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        User user = (User)session.getAttribute("user");
        pager.setTotalRecords(userService.countByUser(user.getCompanyId()));
        List<User> users =  userService.queryByUser(pager, user.getCompanyId());
        return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}


