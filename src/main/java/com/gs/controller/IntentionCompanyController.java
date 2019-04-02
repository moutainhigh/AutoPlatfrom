package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Checkin;
import com.gs.bean.IntentionCompany;
import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.message.IndustrySMS;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.IntentionCompanyService;
import com.gs.email.Mail;
import com.gs.email.MailSender;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * @author Administrator
 * @date 2017-05-17
 */
@Controller
@RequestMapping("intention")
public class IntentionCompanyController {

    private Logger logger = (Logger) LoggerFactory.getLogger(IntentionCompanyController.class);

    @Resource
    private IntentionCompanyService intentionCompanyService;

    /**
     * 可以看的角色：超级管理员、普通管理员
     */
    private String queryRole = Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;

    /**
     * 访问意向公司页面
     * @return
     */
    @RequestMapping(value = "intention_page", method = RequestMethod.GET)
    public String checkinPage() {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(queryRole)) {
                logger.info("访问意向公司页面");
                return "company/intention_company";
            }
            return "error/notPermission";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }

    }

    /**
     * 分页查询意向公司记录
     * @param pageNumber
     * @param pageSize
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "intention_pager", method = RequestMethod.GET)
    public Pager4EasyUI<IntentionCompany> intentionPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("status") String status) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("分页查询意向公司记录");
                    User user = SessionGetUtil.getUser();
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<IntentionCompany> isc = new ArrayList<>();
                    if ("ALL".equals(status)) {
                        pager.setTotalRecords(intentionCompanyService.count(user));
                        isc = intentionCompanyService.queryByPager(pager, user);
                    } else {
                        pager.setTotalRecords(intentionCompanyService.countByStatus(status));
                        isc = intentionCompanyService.queryPagerByStatus(pager, status);
                    }
                    return new Pager4EasyUI<>(pager.getTotalRecords(), isc);
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
     * 根据条件分页查询意向公司记录
     * @param pageNumber
     * @param pageSize
     * @param name
     * @param phone
     * @param email
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "condition_pager", method = RequestMethod.GET)
    public Pager4EasyUI<IntentionCompany> queryPagerByCondition(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize,
                                                                @Param("name") String name, @Param("phone") String phone, @Param("email") String email) {
        if (SessionGetUtil.isUser()) {
            try {
                if (CheckRoleUtil.checkRoles(queryRole)) {
                    logger.info("根据条件分页查询意向公司记录");
                    IntentionCompany ic = new IntentionCompany();
                    ic.setName(name);
                    ic.setPhone(phone);
                    ic.setEmail(email);
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    List<IntentionCompany> ics = new ArrayList<>();
                    pager.setTotalRecords(intentionCompanyService.countByCondition(ic));
                    ics = intentionCompanyService.queryPagerByCondition(pager, ic);

                    return new Pager4EasyUI<>(pager.getTotalRecords(), ics);
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
     * 添加意向公司
     * @param ic
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult addIntention(IntentionCompany ic) {
        logger.info("添加意向公司");
        ic.setIntentionStatus("Y");
        // 手机和邮箱都不为空
        if (ic.getEmail() != null && !"".equals(ic.getEmail()) && ic.getPhone() != null && !"".equals(ic.getPhone())) {
            String to = ic.getPhone();
            String smsContent = "【创意科技】尊敬的" + ic.getName() + "先生/女士,恭喜你成功提交信息,客服将 在一个工作日内与您取得联系,请您保持手机畅通。";
            IndustrySMS is = new IndustrySMS(to, smsContent);
            is.execute();

            Mail mail = new Mail();
            mail.setRecipients(ic.getEmail());
            mail.setSubject("入驻提醒");
            mail.setType(Mail.HTML);
            Multipart multipart = new MimeMultipart();
            BodyPart part1 = new MimeBodyPart();
            try {
                part1.setContent("<p>【创意科技】尊敬的" + ic.getName() + "先生/女士,恭喜你成功提交信息,客服将 在一个工作日内与您取得联系,请您保持手机畅通。</p>", mail.getType());
                multipart.addBodyPart(part1);
                mail.setMultipart(multipart);
            } catch (MessagingException e) {
            }
            MailSender mailSender = new MailSender();
            mailSender.sendEmailByType(Constants.MAIL_TYPE, mail, Constants.MAIL_SENDER, Constants.MAIL_PASSWORD);
        } else { // 其中一个为空
            // 邮箱不为空
            if (ic.getEmail() != null && !"".equals(ic.getEmail())) {
                Mail mail = new Mail();
                mail.setRecipients(ic.getEmail());
                mail.setSubject("入驻提醒");
                mail.setType(Mail.HTML);
                Multipart multipart = new MimeMultipart();
                BodyPart part1 = new MimeBodyPart();
                try {
                    part1.setContent("<p>【创意科技】尊敬的" + ic.getName() + "先生/女士,恭喜你成功提交信息,客服将 在一个工作日内与您取得联系,请您保持手机畅通。</p>", mail.getType());
                    multipart.addBodyPart(part1);
                    mail.setMultipart(multipart);
                } catch (MessagingException e) {
                }
                MailSender mailSender = new MailSender();
                mailSender.sendEmailByType(Constants.MAIL_TYPE, mail, Constants.MAIL_SENDER, Constants.MAIL_PASSWORD);

            } else if (ic.getPhone() != null && !"".equals(ic.getPhone())) { // 手机号不为空
                String to = ic.getPhone();
                String smsContent = "【创意科技】尊敬的" + ic.getName() + "先生/女士,恭喜你成功提交信息,客服将 在一个工作日内与您取得联系,请您保持手机畅通。";
                IndustrySMS is = new IndustrySMS(to, smsContent);
                is.execute();
            } else {
                return ControllerResult.getFailResult("申请失败，请输入手机号或邮箱");
            }
        }
        intentionCompanyService.insert(ic);
        return ControllerResult.getSuccessResult("申请成功，我们的管理员会即使和您联系，请保持手机开机哦");
    }

    /**
     * 更新意向公司记录的状态
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update_status", method = RequestMethod.GET)
    public ControllerResult updateIntentionStatus(String id, String status) {
        if (SessionGetUtil.isUser()) {
            try {
                logger.info("更新意向公司记录的状态");
                if ("Y".equals(status)) {
                    intentionCompanyService.inactive(id);
                } else {
                    intentionCompanyService.active(id);
                }
                return ControllerResult.getSuccessResult("更新成功");
            } catch (Exception e) {
                logger.info("更新意向公司记录状态失败，出现了一个错误");
                return ControllerResult.getFailResult("更新意向公司记录状态失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
