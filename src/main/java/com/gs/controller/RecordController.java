package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.bean.info.SendRemind;
import com.gs.common.Constants;
import com.gs.common.bean.*;
import com.gs.common.message.IndustrySMS;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.MaintainRecordService;
import com.gs.service.UserService;
import com.gs.service.WorkInfoService;
import com.gs.thread.SendEmailThread;
import com.gs.email.Mail;
import org.activiti.engine.impl.Page;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author JangoGuo
 * @date 2017/4/18
 */
@Controller
@RequestMapping("record")
public class RecordController {

    private Logger logger = (Logger) LoggerFactory.getLogger(RecordController.class);

    @Resource
    private MaintainRecordService maintainRecordService;

    @Resource
    private UserService userService;

    @Resource
    private WorkInfoService workInfoService;

    /**
     * 可以查看的角色：董事长、接待员、普通管理员、超级管理员、技师
     */
    private String queryRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_RECEIVE + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.COMPANY_ARTIFICER;

    /**
     * 可以操作的角色：董事长、接待员、技师
     */
    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_RECEIVE + "," + Constants.COMPANY_ARTIFICER;


    /**
     * 可以查看的角色：董事长、财务员、超级管理员、普通管理员
     */
    private String queryRole1 = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_ACCOUNTING + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;

    /**
     * 可以查看的角色：董事长、技师、 库管、超级管理员、普通管理员
     */
    private String queryRole2 = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_ARTIFICER + ","
            + Constants.COMPANY_REPERTORY + "," + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;

    //
    /**
     * 可以操作的角色：董事长、库管
     */
    private String editRole1 = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_REPERTORY;

    /**
     * 可以操作的角色：董事长、库管、技师
     */
    private String editRole2 = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_REPERTORY + "," + Constants.COMPANY_ARTIFICER;

    @RequestMapping(value = "record_page", method = RequestMethod.GET)
    public String recordPage() {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(queryRole)) {
                logger.info("显示维修保养记录管理页面");
                return "maintenanceReception/mainterance_record";
            }
            return "error/notPermission";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    @ResponseBody
    @RequestMapping(value="pager",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, @Param("status") String status){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询指定状态的维修保养记录管理");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<MaintainRecord> maintainRecordList = new ArrayList<>();
                    if ("ALL".equals(status)) {
                        pager.setTotalRecords(maintainRecordService.count(user));
                        maintainRecordList = maintainRecordService.queryByPager(pager, user);
                    } else {
                        pager.setTotalRecords(maintainRecordService.countByStatus(status, user));
                        maintainRecordList = maintainRecordService.queryPagerByStatus(pager, status, user);
                    }
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecordList);
                }
                return null;
            } catch (Exception e) {
                logger.info("查询维修保养记录失败，出现了一个异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "record_queryPager", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryrecordPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        logger.info("分页查询我的维修保养记录");
        User user = SessionGetUtil.getUser();
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(maintainRecordService.count(user));
        List<MaintainRecord> maintainRecords = maintainRecordService.queryByMyName(pager, user);
        return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecords);
    }

    @ResponseBody
    @RequestMapping(value="pager_track",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPagerByTractStatus(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询回访状态的维修保养记录管理");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<MaintainRecord> maintainRecordList = new ArrayList<>();
                    pager.setTotalRecords(maintainRecordService.countByTrackStatus("N", user));
                    maintainRecordList = maintainRecordService.queryPagerByTrackStatus(pager, "N", user);
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecordList);
                }
                return null;
            } catch (Exception e) {
                logger.info("分页查询回访状态的维修保养记录管理， 出现了一个异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value="pager_message",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPagerBymessage(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        if (SessionGetUtil.isUser()) {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询维修保养过用户");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<MaintainRecord> maintainRecordList = new ArrayList<>();
                    pager.setTotalRecords(maintainRecordService.countByStatus("Y", user));
                    maintainRecordList = maintainRecordService.queryPagerByStatus(pager, "Y", user);
                    return new Pager4EasyUI<>(pager.getTotalRecords(), maintainRecordList);
                }
                return null;
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "condition_pager", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPagerByCondition(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,
                                                       @Param("userName")String userName, @Param("carPlate")String carPlate,
                                                       @Param("maintainOrFix")String maintainOrFix,
                                                       @Param("companyId")String companyId, @Param("speedStatus") String speedStatus) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("根据条件分页查询维修保养记录");
                    User user = SessionGetUtil.getUser();
                    MaintainRecord record = new MaintainRecord();
                    Checkin checkin = new Checkin();
                    checkin.setUserName(userName);
                    checkin.setCarPlate(carPlate);
                    checkin.setMaintainOrFix(maintainOrFix);
                    record.setCheckin(checkin);
                    record.setCompanyId(companyId);
                    record.setSpeedStatus(speedStatus);
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<MaintainRecord> records = new ArrayList<>();
                    pager.setTotalRecords(maintainRecordService.countByCondition(record, user));
                    records = maintainRecordService.queryPagerByCondition(pager, record, user);

                    return new Pager4EasyUI<>(pager.getTotalRecords(), records);
                }
                return null;
            } catch (Exception e) {
                logger.info("根据条件分页查询维修保养记录失败，出现了一个异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @RequestMapping(value = "reminder_page", method = RequestMethod.GET)
    public String reminderPage() {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(queryRole)) {
                logger.info("显示维已完成的修保养记录页面");
                return "settlementCar/car_reminder";
            }
            return "error/notPermission";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    @ResponseBody
    @RequestMapping(value = "pager_speedStatus",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPagerBySpeedStatus(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, @Param("speedStatus")String speedStatus){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询进度状态的维修保养记录管理");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<MaintainRecord> maintainRecordList = new ArrayList<>();
                    String[] ss = speedStatus.split(",");
                    pager.setTotalRecords(maintainRecordService.countBySpeedStatus(ss, user));
                    maintainRecordList = maintainRecordService.queryPagerBySpeedStatus(pager, ss, user);
                    return new Pager4EasyUI<>(pager.getTotalRecords(), maintainRecordList);
                }
                return null;
            } catch (Exception e) {
                logger.info("分页查询进度状态的维修保养记录管理失败，出现了一个异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    /**
     * 查看个人预约
     * @return
     */
    @RequestMapping(value = "my_reminder", method = RequestMethod.GET)
    private ModelAndView carOwerreminder() {
        ModelAndView mav = new ModelAndView();
        User user = SessionGetUtil.getUser();
        if (!SessionGetUtil.isUser()) {
            mav.setViewName("index/notLogin");
            return mav;
        }
        logger.info("车主用户查看已完成进度");
        mav.setViewName("customerClient/reminder");
        mav.addObject("rems", maintainRecordService.queryMyName(user));
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "pager_speedAndPickingStatus",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPagerBySpeedAndPickingStatus(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, @Param("speedStatus")String speedStatus){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole2)) {
                    logger.info("分页查询进度状态和领料状态的维修保养记录管理");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<MaintainRecord> maintainRecordList = new ArrayList<>();
                    String[] ss = speedStatus.split(",");
                    if (!CheckRoleUtil.checkRoles(Constants.COMPANY_ARTIFICER)) {
                        String pickingStatus = Constants.NOT_APPLY;
                        pager.setTotalRecords(maintainRecordService.countBySpeedStatusAndPickingStatus(ss, pickingStatus, user));
                        maintainRecordList = maintainRecordService.queryPagerBySpeedStatusAndPickingStatus(ss, pager, pickingStatus, user);
                    } else {
                        String pickingStatus = Constants.NOT_APPLY;
                        pager.setTotalRecords(maintainRecordService.countByUserId(user, pickingStatus, ss));
                        maintainRecordList = maintainRecordService.queryPagerByUserId(pager, user, pickingStatus, ss);
                    }
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecordList);
                }
                return null;
            } catch (Exception e) {
                logger.info("分页查询进度状态和领料状态的维修保养记录管理失败，出现了一个异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "pager_picking",method= RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryPagerByUserId(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize, @Param("speedStatus")String speedStatus){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole2)) {
                    logger.info("根据员工id分页查询维修保养中的维修保养记录");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<MaintainRecord> maintainRecordList = new ArrayList<>();
                    String[] ss = speedStatus.split(",");
                    if (!CheckRoleUtil.checkRoles(Constants.COMPANY_ARTIFICER)) {

                        pager.setTotalRecords(maintainRecordService.countBySpeedStatus(ss, user));
                        maintainRecordList = maintainRecordService.queryPagerBySpeedStatus(pager, ss, user);
                    } else {
                        pager.setTotalRecords(maintainRecordService.countByUserId(user, "", ss));
                        maintainRecordList = maintainRecordService.queryPagerByUserId(pager, user, "", ss);
                    }

                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecordList);
                }
                return null;
            } catch (Exception e) {
                logger.info("根据员工id分页查询维修保养中的维修保养记录失败，出现了一个异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "update_status", method=RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id") String id, @Param("status")String status){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("更新维修保养记录状态");
                    if ("Y".equals(status)) {
                        maintainRecordService.inactive(id);
                    } else if ("N".equals(status)) {
                        maintainRecordService.active(id);

                    }
                    return ControllerResult.getSuccessResult("更新成功");
                }
                logger.info("更新维修保养记录状态失败，没有权限操作");
                return ControllerResult.getFailResult("更新维修保养记录状态失败，没有权限操作");
            } catch (Exception e) {
                logger.info("更新维修保养记录状态失败，出现了一个错误");
                return ControllerResult.getFailResult("更新维修保养记录状态失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value = "edit", method=RequestMethod.POST)
    public ControllerResult editMainteranceRecord(MaintainRecord maintainRecord){
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("更新维修保养记录");
                    maintainRecordService.update(maintainRecord);
                    return ControllerResult.getSuccessResult("更新成功");
                }
                logger.info("更新维修保养记录失败，没有权限操作");
                return ControllerResult.getFailResult("更新维修保养记录失败，没有权限操作");
            } catch (Exception e) {
                logger.info("更新维修保养记录失败，出现了一个错误");
                return ControllerResult.getFailResult("更新维修保养记录失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value = "send_remind", method = RequestMethod.POST)
    public ControllerResult sendRemind(SendRemind sendRemind) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("发送提车提醒");
                    String[] userIds = sendRemind.getUserId().split(",");
                    String[] recordIds = sendRemind.getRecordId().split(",");
                    String[] remindMethod = sendRemind.getRemindMethod().split(",");
                    String[] carPlates = sendRemind.getCarPlate().split(",");
                    List<Mail> mails = new ArrayList<Mail>();
                    for (int i = 0, len = userIds.length; i < len; i++) {
                        logger.info("给已注册用户发送提醒");
                        User user = userService.queryById(userIds[i]);
                        if (remindMethod.length == 2) {
                            logger.info("发送短信和邮箱提醒");
                            logger.info("发送短信提醒");
                            String to = user.getUserPhone();
                            String smsContent = "尊敬的" + user.getUserName() + "车主，车牌号为" + carPlates[i] + ",您的爱车已经整装待发，如果您有时间，请来领它回家哦^_^";
                            IndustrySMS is = new IndustrySMS(to, smsContent);
                            is.execute();

                            if (user.getUserEmail() != null && !"".equals(user.getUserEmail())) {
                                Mail mail = new Mail();
                                mail.setRecipients(user.getUserEmail());
                                mail.setSubject(sendRemind.getRemindTitle());
                                mail.setType(Mail.HTML);
                                Multipart multipart = new MimeMultipart();
                                BodyPart part1 = new MimeBodyPart();
                                sendRemind.setRemindContent("<p>尊敬的" + user.getUserName() + "车主，车牌号为" + carPlates[i] + ",您的爱车已经整装待发，如果您有时间，请来领它回家哦^_^，如有问题，请联系0797-5201314</p>");
                                try {
                                    part1.setContent(sendRemind.getRemindContent(), mail.getType());
                                    multipart.addBodyPart(part1);
                                    mail.setMultipart(multipart);
                                    mails.add(mail);
                                } catch (MessagingException e) {
                                    logger.info("发送提车提醒失败，出现了一个错误");
                                    return ControllerResult.getFailResult("邮箱提醒发送失败");
                                }
                            }
                        } else {
                            if ("email".equals(remindMethod[0])) {
                                logger.info("发送邮箱提醒");
                                if (user.getUserEmail() != null && !user.getUserEmail().equals("")) {
                                    Mail mail = new Mail();
                                    mail.setRecipients(user.getUserEmail());
                                    mail.setSubject(sendRemind.getRemindTitle());
                                    mail.setType(Mail.HTML);
                                    Multipart multipart = new MimeMultipart();
                                    BodyPart part1 = new MimeBodyPart();
                                    sendRemind.setRemindContent("<p>尊敬的" + user.getUserName() + "车主，车牌号为" + carPlates[i] + ",您的爱车已经整装待发，如果您有时间，请来领它回家哦^_^，如有问题，请联系0797-5201314</p>");
                                    try {
                                        part1.setContent(sendRemind.getRemindContent(), mail.getType());
                                        multipart.addBodyPart(part1);
                                        mail.setMultipart(multipart);
                                        mails.add(mail);
                                    } catch (MessagingException e) {
                                        logger.info("发送提车提醒失败，出现了一个错误");
                                        return ControllerResult.getFailResult("邮箱提醒发送失败");
                                    }
                                }
                            } else if ("message".equals(remindMethod[0])) {
                                logger.info("发送短信提醒");
                                String to = user.getUserPhone();
                                String smsContent = "尊敬的" + user.getUserName() + "车主，车牌号为" + carPlates[i] + ",您的爱车已经整装待发，如果您有时间，请来领它回家哦^_^";
                                IndustrySMS is = new IndustrySMS(to, smsContent);
                                is.execute();
                            }
                        }
                        maintainRecordService.updateSpeedStatusById(Constants.ALREADY_REMIND, recordIds[i]);

                    }
                    for (int i = userIds.length; i < recordIds.length; i++) {
                        logger.info("给未注册用户发送短信提醒");
                        MaintainRecord record = maintainRecordService.queryById(recordIds[i]);
                        if ("message".equals(remindMethod[0])) {
                            logger.info("发送短信提醒");
                            String to = record.getCheckin().getUserPhone();
                            String smsContent = "尊敬的" + record.getCheckin().getUserName() + "车主，车牌号为" + record.getCheckin().getPlate().getPlateName() + "-" + record.getCheckin().getCarPlate() + ",您的爱车已经整装待发，如果您有时间，请来领它回家哦^_^";
                            IndustrySMS is = new IndustrySMS(to, smsContent);
                            is.execute();
                        }
                        maintainRecordService.updateSpeedStatusById(Constants.ALREADY_REMIND, recordIds[i]);
                    }
                    new Thread(new SendEmailThread(mails)).start();
                    return ControllerResult.getSuccessResult("提车提醒已成功发送");
                }
                logger.info("发送提车提醒失败，没有权限操作");
                return ControllerResult.getFailResult("发送提车提醒失败，没有权限操作");
            } catch (Exception e) {
                logger.info("发送提车提醒失败，出现了一个错误");
                return ControllerResult.getFailResult("发送提车提醒失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }

    }

    /**
     * 默认查询本月维修保养记录报表显示
     * @param companyId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="query_default",method= RequestMethod.GET)
    public List<LineBasic> queryAll(@Param("companyId")String companyId){
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRole1)) {
                logger.info("默认查询本月维修保养记录报表显示");
                List<LineBasic> lineBasics = new ArrayList<>();
                LineBasic lineBasic = new LineBasic();
                LineBasic lineBasic1 = new LineBasic();
                User user = SessionGetUtil.getUser();
                if(user.getCompanyId() != null && !"".equals(user.getCompanyId())){
                    companyId = user.getCompanyId();
                }
                lineBasic.setName("保养");
                dateDay("one", companyId);
                lineBasic.setData(HighchartsData.doubleDayOne);
                lineBasic1.setName("维修");
                dateDay("two", companyId);
                lineBasic1.setData(HighchartsData.doubleDayTwo);
                lineBasic.setCategories(HighchartsData.strDay);
                lineBasic1.setCategories(HighchartsData.strDay);
                lineBasics.add(lineBasic);
                lineBasics.add(lineBasic1);
                return lineBasics;
            }
            return null;
        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }

    }

    /**
     * 根据年，月，季度，周，日查询所有维修保养记录显示
     * @param start
     * @param end
     * @param type
     * @param companyId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="query_condition",method= RequestMethod.GET)
    public List<LineBasic> queryCondition(@Param("start")String start,@Param("end")String end,
                                          @Param("type")String type,@Param("companyId")String companyId){
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRole1)) {
                logger.info("根据年，月，季度，周，日查询所有维修保养记录显示");
                List<LineBasic> lineBasics = new ArrayList<LineBasic>();
                LineBasic lineBasic = new LineBasic();
                LineBasic lineBasic1 = new LineBasic();
                lineBasic.setName("保养");
                lineBasic1.setName("维修");
                User user = SessionGetUtil.getUser();
                if (user.getCompanyId() != null && !"".equals(user.getCompanyId())) {
                    companyId = user.getCompanyId();
                }
                if (start != null && !"".equals(start) && end != null && !"".equals(end) && type != null && !type.equals("")) {
                    if ("year".equals(type)) {
                        HighchartsData.setStrYear(start, end);
                        dataCondition(start, end, "保养", type, "year", "one", companyId);
                        lineBasic.setData(HighchartsData.doubleYearOne);
                        dataCondition(start, end, "维修", type, "year", "two", companyId);
                        lineBasic1.setData(HighchartsData.doubleYearTwo);
                        lineBasic.setCategories(HighchartsData.strYear);
                        lineBasic1.setCategories(HighchartsData.strYear);
                    } else if ("quarter".equals(type)) {
                        dataCondition(start, end, "保养", type, "quarter", "one", companyId);
                        lineBasic.setData(HighchartsData.doubleQuarterOne);
                        dataCondition(start, end, "维修", type, "quarter", "two", companyId);
                        lineBasic1.setData(HighchartsData.doubleQuarterTwo);
                        lineBasic.setCategories(HighchartsData.strQuarter);
                        lineBasic1.setCategories(HighchartsData.strQuarter);
                    } else if ("month".equals(type)) {
                        dataCondition(start, end, "保养", type, "month", "one", companyId);
                        lineBasic.setData(HighchartsData.doubleMonthOne);
                        dataCondition(start, end, "维修", type, "month", "two", companyId);
                        lineBasic1.setData(HighchartsData.doubleMonthTwo);
                        lineBasic.setCategories(HighchartsData.strMonth);
                        lineBasic1.setCategories(HighchartsData.strMonth);
                    } else if ("week".equals(type)) {
                        HighchartsData.setStrWeek(start, end);
                        dataCondition(start, end, "保养", type, "week", "one", companyId);
                        lineBasic.setData(HighchartsData.doubleWeekOne);
                        dataCondition(start, end, "维修", type, "week", "two", companyId);
                        lineBasic1.setData(HighchartsData.doubleWeekTwo);
                        lineBasic.setCategories(HighchartsData.strWeek);
                        lineBasic1.setCategories(HighchartsData.strWeek);
                    } else if ("day".equals(type)) {
                        dataCondition(start, end, "保养", type, "day", "one", companyId);
                        lineBasic.setData(HighchartsData.doubleDayOne);
                        dataCondition(start, end, "维修", type, "day", "two", companyId);
                        lineBasic1.setData(HighchartsData.doubleDayTwo);
                        lineBasic.setCategories(HighchartsData.strDay);
                        lineBasic1.setCategories(HighchartsData.strDay);
                    }
                }
                lineBasics.add(lineBasic);
                lineBasics.add(lineBasic1);
                return lineBasics;
            }
            return null;
        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }


    /**
     * 默认查询本月的工单
     * @param type
     * @param companyId
     */
    public void dateDay(String type,String companyId){
        HighchartsData.doubleDayTwo = new double[31];
        HighchartsData.doubleDayOne = new double[31];
        List<MaintainRecord> maintainRecords = null;
        if("one".equals(type)){
            maintainRecords = maintainRecordService.queryByDefault("保养",companyId);
        }else if("two".equals(type)){
            maintainRecords = maintainRecordService.queryByDefault("维修",companyId);
        }
        int i = 0;
        double[] doubles = new double[maintainRecords.size()];
        String[] strs = new String[maintainRecords.size()];
        for(MaintainRecord io: maintainRecords) {
            doubles[i] = io.getCoont();
            strs[i] = HighchartsData.dateFormat(io.getRecordCreatedTime(),"day");
            i++;
        }
        for(int j = 0,len = HighchartsData.strDay.length; j <len ; j++){
            for(int k = 0; k < strs.length; k++){
                if(HighchartsData.strDay[j].equals(strs[k])){
                    if("two".equals(type)){
                        HighchartsData.doubleDayTwo[j] = doubles[k];
                    }else if("one".equals(type)){
                        HighchartsData.doubleDayOne[j] = doubles[k];
                    }

                }
            }
        }


    }

    /**
     * 按年，季度，月，周，日，查询 工单
     * @param start
     * @param end
     * @param maintainOrFix
     * @param type
     * @param date
     * @param species
     * @param companyId
     */
    public void dataCondition(String start,String end,String maintainOrFix,String type,String date,String species,String companyId){
        HighchartsData.doubleDayTwo = new double[31];
        HighchartsData.doubleDayOne = new double[31];
        HighchartsData. doubleMonthTwo = new double[12];
        HighchartsData.doubleMonthOne = new double[12];
        HighchartsData.doubleQuarterTwo = new double[4];
        HighchartsData.doubleQuarterOne = new double[4];
        HighchartsData.doubleYearTwo = new double[HighchartsData.yearLen];
        HighchartsData.doubleYearOne = new double[HighchartsData.yearLen];
        HighchartsData.doubleWeekTwo = new double[HighchartsData.weekLen];
        HighchartsData.doubleWeekOne = new double[HighchartsData.weekLen];
        List<MaintainRecord> maintainRecords = maintainRecordService.queryByCondition(start,end,maintainOrFix,type,companyId);;
        int i = 0;
        double[] doubles = new double[maintainRecords.size()];
        String[] strs = new String[maintainRecords.size()];
        HighchartsData.len = 0;
        for(MaintainRecord io: maintainRecords) {
            doubles[i] = io.getCoont();
            if("month".equals(date)) {
                strs[i] = HighchartsData.dateFormat(io.getRecordCreatedTime(), "month");
                HighchartsData.len = HighchartsData.strMonth.length;
            }else if("day".equals(date)){
                strs[i] = HighchartsData.dateFormat(io.getRecordCreatedTime(), "day");
                HighchartsData.len = HighchartsData.strDay.length;
            }else if("quarter".equals(date)){
                strs[i] = HighchartsData.dateFormat(io.getRecordCreatedTime(), "quarter");
                HighchartsData.len = HighchartsData.strQuarter.length;
            }else if("year".equals(date)){
                strs[i] = HighchartsData.dateFormat(io.getRecordCreatedTime(),"year");
                HighchartsData.len = HighchartsData.strYear.length;
            }else if("week".equals(date)){
                strs[i] = "第"+String.valueOf(HighchartsData.getWeek(HighchartsData.dateFormat(io.getRecordCreatedTime())))+"周";
                HighchartsData.len = HighchartsData.strWeek.length;
            }
            i++;
        }
        if("quarter".equals(date)) {
            HighchartsData.getQuarter(strs,doubles,species);
        }else if("month".equals(date)){
            HighchartsData.getMonth(strs,doubles,species);
        }else if("day".equals(date)){
            HighchartsData.getDay(strs,doubles,species);
        }else if("year".equals(date)){
            HighchartsData. getYear(strs,doubles,species);
        }else if("week".equals(date)){
            HighchartsData.getWeek(strs,doubles,species);
        }
    }

    /**
     * 更新维修保养进度
     * @param recordId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "achieve_record", method = RequestMethod.GET)
    public ControllerResult achieveRecord(String recordId) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole2)) {
                logger.info("更新失败");
                return ControllerResult.getFailResult("更新失败，没有该权限操作");
            }
            logger.info("更新维修保养进度");
            maintainRecordService.updateSpeedStatusById(Constants.NOT_REMIND, recordId);
            maintainRecordService.updateEndTimeById(new Date(), recordId);
            return ControllerResult.getSuccessResult("更新进度成功!");
        } catch (Exception e) {
            logger.info("更新失败，出现了一个错误");
            return ControllerResult.getFailResult("更新失败，出现了一个错误");
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}

































