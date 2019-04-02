package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.common.Constants;
import com.gs.common.bean.*;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.EncryptUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.common.util.UUIDUtil;
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

import javax.annotation.Resource;

import com.gs.service.AppointmentService;
import com.gs.bean.Appointment;
import com.gs.common.bean.Pager4EasyUI;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.Request;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("appointment")
public class AppointmentController {

    private Logger logger = (Logger) LoggerFactory.getLogger(AppointmentController.class);

    @Resource
    private AppointmentService appointmentService;

    @Resource
    private UserService userService;

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private RoleService roleService;
    /**
     * 可擦看： 董事长、接待员、超级管理员、普通管理员,车主
     */
    private String queryRole = Constants.CAR_OWNER + "," + Constants.COMPANY_RECEIVE + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.COMPANY_ADMIN;
    /**
     * 可操作：董事长、接待员，车主
     */
    private String editRole = Constants.COMPANY_RECEIVE + "," + Constants.COMPANY_ADMIN;

    /**
     * 跳转预约管理页面
     * @return
     */
    @RequestMapping(value = "appointment", method = RequestMethod.GET)
    public String appointment() {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(queryRole)) {
                logger.info("预约管理");
                return "maintenanceAppointment/appointment";
            }
            return "error/notPermission";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    /**
     * 查看个人预约
     * @return
     */
    @RequestMapping(value = "my_app", method = RequestMethod.GET)
    private ModelAndView carOwerAppointment() {
        ModelAndView mav = new ModelAndView();
        User user = SessionGetUtil.getUser();
        if (!SessionGetUtil.isUser()) {
            mav.setViewName("index/notLogin");
            return mav;
        }
        logger.info("车主用户查看我的预约");
        mav.setViewName("customerClient/appointment");
        mav.addObject("apps", appointmentService.queryMyName(user));
        return mav;
    }

    /**
     * 分页查询预约
     * @param pageNumber
     * @param pageSize
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "query_pager", method = RequestMethod.GET)
    public Pager4EasyUI<Appointment> queryPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("status") String status) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询预约");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<Appointment> appointments = new ArrayList<>();
                    if ("ALL".equals(status)) {
                        pager.setTotalRecords(appointmentService.count(user));
                        appointments = appointmentService.queryByPager(pager, user);
                    } else if ("P".equals(status)) {
                        pager.setTotalRecords(appointmentService.count(user));
                        appointments = appointmentService.querySpeedStatus(pager, user);
                    } else {
                        pager.setTotalRecords(appointmentService.countByStatus(status, user));
                        appointments = appointmentService.queryPagerByStatus(pager, status, user);
                    }
                    return new Pager4EasyUI<>(pager.getTotalRecords(), appointments);
                }
                return null;
            } catch (Exception e) {
                logger.info("查询失败，出现了异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    /**
     * 根据条件分页查询预约记录
     * @param pageNumber
     * @param pageSize
     * @param userName
     * @param userPhone
     * @param carPlate
     * @param maintainOrFix
     * @param companyId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "appointment_pager", method = RequestMethod.GET)
    public Pager4EasyUI<Appointment> queryPagerByAppointment(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize,
                                                             @Param("userName") String userName, @Param("userPhone") String userPhone,
                                                             @Param("carPlate") String carPlate, @Param("maintainOrFix") String maintainOrFix,
                                                             @Param("companyId") String companyId) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("根据条件分页查询预约记录");
                    User user = SessionGetUtil.getUser();
                    Appointment appointment = new Appointment();
                    appointment.setUserName(userName);
                    appointment.setUserPhone(userPhone);
                    appointment.setCarPlate(carPlate);
                    appointment.setMaintainOrFix(maintainOrFix);
                    appointment.setCompanyId(companyId);
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<Appointment> appointments = new ArrayList<>();
                    pager.setTotalRecords(appointmentService.countByCondition(appointment, user));
                    appointments = appointmentService.queryPagerByCondition(pager, appointment, user);

                    return new Pager4EasyUI<>(pager.getTotalRecords(), appointments);
                }
                return null;
            } catch (Exception e) {
                logger.info("查询失败，出现了异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    /**
     * 添加预约
     * @param appointment
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult appointmentAdd(Appointment appointment) {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(editRole)) {
                try {
                    User loginUser = SessionGetUtil.getUser();
                    if (appointmentService.queryByPhone(appointment.getUserPhone(), loginUser) == 0) {
                        logger.info("添加预约");
                        appointment.setSpeedStatus(Constants.APPOINTMENT);
                        appointment.setCompanyId(loginUser.getCompanyId());

                        appointmentService.insert(appointment);
                        return ControllerResult.getSuccessResult("添加预约信息成功");
                    }
                    return ControllerResult.getFailResult("添加预约记录失败，已经存在该预约信息，请不要重复添加");
                } catch (Exception e) {
                    logger.info("添加预约记入失败，出现了一个错误");
                    return ControllerResult.getFailResult("添加预约记录失败，出现了一个错误");
                }
            }
            return ControllerResult.getFailResult("添加预约记入失败");
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    /**
     * 车主预约
     * @param appointment
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addCustomer", method = RequestMethod.POST)
    public ControllerResult appointmentAddCustomer(Appointment appointment) {
        if (SessionGetUtil.isUser()) {

            User loginUser = SessionGetUtil.getUser();
            logger.info("车主预约");

            appointment.setSpeedStatus(Constants.APPOINTMENT);
            appointment.setUserId(loginUser.getUserId());
            appointmentService.insert(appointment);
            return ControllerResult.getSuccessResult("成功预约");


        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    /**
     * 更新预约
     * @param appointment
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ControllerResult appointmentUpdate(Appointment appointment) {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(editRole)) {
                logger.info("更新预约");
                appointmentService.update(appointment);
                return ControllerResult.getSuccessResult("更新成功");
            }
            return ControllerResult.getFailResult("添加预约记录失败，你没有权限");
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    /**
     * 更新预约状态
     * @param appointmentId
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update_status", method = RequestMethod.GET)
    public ControllerResult updateAppointmentStatus(String appointmentId, String status) {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(editRole)) {
                logger.info("更新预约状态");
                if ("Y".equals(status)) {
                    appointmentService.inactive(appointmentId);
                } else {
                    appointmentService.active(appointmentId);
                }
                return ControllerResult.getSuccessResult("更新成功");
            }
            return ControllerResult.getFailResult("添加预约记录失败，你没有权限");
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    /**
     * 查询所有预约信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "appointment_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryMaintenanceAppointmentAll() {
        if (SessionGetUtil.isUser()) {
            logger.info("查询所有预约信息");
            User user = SessionGetUtil.getUser();
            List<Appointment> appointments = appointmentService.queryAll(user);
            List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<>();
            for (Appointment appointment : appointments) {
                ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                comboBox4EasyUI.setId(appointment.getAppointmentId());
                comboBox4EasyUI.setText(appointment.getUserName());
                comboBox4EasyUIs.add(comboBox4EasyUI);
            }
            return comboBox4EasyUIs;
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}