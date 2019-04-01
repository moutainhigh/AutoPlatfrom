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
import com.gs.common.message.IndustrySMS;
import com.gs.common.util.*;
import com.gs.dao.CompanyDAO;
import com.gs.service.*;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/4/1.
 */
@Controller
@RequestMapping("/company")
public class CompanyController {

    private Logger logger = (Logger) LoggerFactory.getLogger(CompanyController.class);

    @Resource
    private CompanyService companyService;

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private AppointmentService appointmentService;

    @Resource
    private CheckinService checkinService;

    @Resource
    private ComplaintService complaintService;

    @Resource
    private MaintainRemindService maintainRemindService;

    @Resource
    private IntentionCompanyService intentionCompanyService;

    private String CompanyQueryRole = Constants.SYSTEM_SUPER_ADMIN + "," + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.COMPANY_ADMIN;
    private String CompanyEditRole = Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.COMPANY_ADMIN;
    private String carCommonRole = Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.COMPANY_ADMIN + "," + Constants.COMPANY_RECEIVE + "," + Constants.COMPANY_ARTIFICER;
    @RequestMapping(value = "home", method = RequestMethod.GET)
    private ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        User user = SessionGetUtil.getUser();
        int count = 5;
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            mav.setViewName("index/notLogin");
            return mav;
        }
        mav.setViewName("company/home");
        logger.info("访问公司的主页");
        String admin = Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;
        if (CheckRoleUtil.checkRoles(admin)) {
            mav.addObject("companys", companyService.queryByTop(count));
            mav.addObject("intentions", intentionCompanyService.queryByTop(count));
        } else {
            mav.addObject("apps", appointmentService.queryPagerByTop(count, user));
            mav.addObject("checkins", checkinService.queryByTop(count, user));
            mav.addObject("complaints", complaintService.queryByTop(count, user));
            mav.addObject("reminds", maintainRemindService.queryByTop(count, user));
            mav.addObject("company", companyService.queryById(user.getCompanyId()));
        }
        return mav;
    }

    @RequestMapping(value = "info", method = RequestMethod.GET)
    private String showCompanyInfo() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }else{
            logger.info("访问公司基本信息页面");
            return "company/company_info";
        }

    }

    @RequestMapping(value = "brand", method = RequestMethod.GET)
    private String showCarBrand() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效");
            return "index/notLogin";
        }else{
            if(CheckRoleUtil.checkRoles(carCommonRole)){
                logger.info("访问汽车品牌页面");
                return "company/car_brand";
            }
            return "error/notPermission";
        }

    }

    @RequestMapping(value = "color", method = RequestMethod.GET)
    private String showCarColor() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效");
            return "index/notLogin";
        }else{
            if(CheckRoleUtil.checkRoles(carCommonRole)){
                logger.info("访问汽车颜色页面");
                return "company/car_colour";
            }else{
                return "error/notPermission";
            }
        }

    }

    @RequestMapping(value = "model", method = RequestMethod.GET)
    private String showCarModel() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效");
            return "index/notLogin";
        }else{
            if(CheckRoleUtil.checkRoles(carCommonRole)){
                logger.info("访问汽车车型页面");
                return "company/car_model";
            }else{
                return "error/notPermission";
            }
        }

    }

    @RequestMapping(value = "plate", method = RequestMethod.GET)
    private String showCarPlate() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效");
            return "index/notLogin";
        }else{
            if(CheckRoleUtil.checkRoles(carCommonRole)){
                logger.info("访问车牌页面");
                return "company/car_plate";
            } else{
                return "error/notPermission";
            }
        }

    }

    @RequestMapping(value = "maintainItem", method = RequestMethod.GET)
    private String showMaintainItem() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }else{
            if(CheckRoleUtil.checkRoles(carCommonRole)){
                logger.info("访问维修项目页面");
                return "company/maintain_item";
            }else{
                return "error/notPermission";
            }
        }

    }

    @RequestMapping(value = "maintenanceItem", method = RequestMethod.GET)
    private String showMaintainFix() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }else{
            if(CheckRoleUtil.checkRoles(carCommonRole)){
                logger.info("访问保养项目页面");
                return "company/maintenance_item";
            }else{
                return "error/notPermission";
            }
        }

    }

    @ResponseBody
    @RequestMapping(value = "InsertCompany", method = RequestMethod.POST)
    public ControllerResult InsetCompany(Company company,MultipartFile file,HttpSession session) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }else{
            try {
                if(CheckRoleUtil.checkRoles(CompanyEditRole)){
                    logger.info("添加公司");
                    company.setCompanyLogo("/img/logo.jpg");
                    String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
                    String filePath = FileUtil.uploadPath(session, "\\" + fileName);
                    String logo = "/uploads/" + fileName;
                    if (!file.isEmpty()) {
                        file.transferTo(new File(filePath));
                        company.setCompanyImg(logo);
                    }
                    String companyId = UUIDUtil.uuid();
                    company.setCompanyId(companyId);
                    User user = new User();
                    String userId = UUIDUtil.uuid();
                    user.setUserId(userId);
                    user.setUserStatus("Y");
                    user.setUserIcon("/img/default.png");
                    user.setUserPhone(company.getCompanyPricipalPhone());
                    user.setCompanyId(companyId);
                    user.setUserName(company.getCompanyPricipal());
                    user.setUserAddress(company.getCompanyAddress());
                    user.setUserGender("N");
                    String pwd = getCharAndNumr(8);
                    user.setUserPwd(EncryptUtil.md5Encrypt(pwd));
                    Role role = roleService.queryByName(Constants.COMPANY_ADMIN);
                    UserRole userRole = new UserRole();
                    userRole.setUserId(userId);
                    userRole.setRoleId(role.getRoleId());
                    String to = user.getUserPhone();
                    String smsContent = "【创意科技】尊敬的" + user.getUserName() + "用户，您已经成功入驻我们的平台，您可以使用您的手机号进行登入我们的系统了哦，登入密码为:" + pwd;
                    IndustrySMS is = new IndustrySMS(to, smsContent);
                    is.execute();
                    userService.insert(user);
                    userRoleService.insert(userRole);
                    companyService.insert(company);
                    return ControllerResult.getSuccessResult("添加公司成功");
                }else{
                    return ControllerResult.getFailResult("添加公司失败,您没有权限操作");
                }
            } catch (Exception e) {
                logger.info("添加失败，出现了一个错误");
                return ControllerResult.getFailResult("添加失败，出现了一个错误");
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "uploadCompany", method = RequestMethod.POST)
    public ControllerResult upload(Company company,MultipartFile file,HttpSession session,MultipartFile file1) {
        System.out.println(file1.getOriginalFilename());
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        if (CheckRoleUtil.checkRoles(CompanyEditRole)) {
//            try {
                logger.info("更新公司成功");
                    String fileName = UUIDUtil.uuid() + file.getOriginalFilename();
                     System.out.println(fileName);
                    String filePath = FileUtil.uploadPath(session, "\\" + fileName);
                    String logo = "/uploads/" + fileName;
                    if (!file.isEmpty()) {
                        try {
                            file.transferTo(new File(filePath));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        company.setCompanyLogo(logo);
                    }
                    String fileImg = UUIDUtil.uuid() + file1.getOriginalFilename();
                    String filImgPath = FileUtil.uploadPath(session, "\\" + fileImg);
                    String img = "/uploads/" + fileImg;
                    if (!file1.isEmpty()) {
                        try {
                            file1.transferTo(new File(filImgPath));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        company.setCompanyImg(img);
                    }
                    System.out.println(company);
                    companyService.update(company);
                    return ControllerResult.getSuccessResult("更新公司成功");
//            } catch (Exception e) {
//                logger.info("更新失败，出现了一个错误");
//                return ControllerResult.getFailResult("更新失败，出现了一个错误");
//            }
        }else{
            return ControllerResult.getFailResult("您没有权限修改");
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<Company> queryByPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        User user = SessionGetUtil.getUser();
        Pager pager = new Pager();
        logger.info("分页查询所有公司");
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(companyService.count(user));
        List<Company> companyList = companyService.queryByPager(pager,user);
        return new Pager4EasyUI<Company>(pager.getTotalRecords(), companyList);
    }

    @ResponseBody
    @RequestMapping(value = "companyStatusModify", method = RequestMethod.GET)
    public ControllerResult companyStatusModify(@Param("id") String id, @Param("status") String status) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if(CheckRoleUtil.checkRoles(CompanyQueryRole)){
                if (status.equals("Y")) {
                    logger.info("公司冻结");
                    companyService.inactive(id);
                } else if (status.equals("N")) {
                    logger.info("公司激活");
                    companyService.active(id);
                }
                return ControllerResult.getSuccessResult("操作成功");
            }else{
                return ControllerResult.getFailResult("操作失败,你没有权限操作");
            }

        } catch (Exception e) {
            logger.info("操作失败，出现了一个错误");
            return ControllerResult.getFailResult("操作失败，出现了一个错误");
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ResponseBody
    @RequestMapping(value = "company_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryUserAll() {
        try{
            User user = SessionGetUtil.getUser();
            logger.info("查询所有公司");
            List<Company> companyList = companyService.queryAll(user);
            List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
            for (Company companys : companyList) {
                ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                comboBox4EasyUI.setId(companys.getCompanyId());
                comboBox4EasyUI.setText(companys.getCompanyName());
                comboBox4EasyUIs.add(comboBox4EasyUI);
            }
            return comboBox4EasyUIs;
        }catch(Exception e){
            logger.info("查询失败,出现了一个错误");
            return null;
        }

    }

    @ResponseBody
    @RequestMapping(value = "queryStatusPager", method = RequestMethod.GET)
    public Pager4EasyUI<Company> companyStatus(@Param("status") String status, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        Pager pager = new Pager();
        User user = SessionGetUtil.getUser();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(companyService.statusCount(status,user));
        List<Company> companys = companyService.queryByStatusPager(status, pager,user);
        return new Pager4EasyUI<Company>(pager.getTotalRecords(), companys);
    }

    @ResponseBody
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public Pager4EasyUI<Company> companySearch(@Param("companyName")String companyName, @Param("userName")String userName, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser()) {
            logger.info("session已失效或者权限不足");
            return null;
        }
        Pager pager = new Pager();
        User user = SessionGetUtil.getUser();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(companyService.searchCount(companyName, userName));
        List<Company> companyList = companyService.searchByPager(companyName,userName,pager);
        return new Pager4EasyUI<Company>(pager.getTotalRecords(), companyList);
    }

    public static String getCharAndNumr(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            // 字符串
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 取得大写字母还是小写字母
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

}


