package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Role;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.message.IndustrySMS;
import com.gs.common.util.*;
import com.gs.service.*;
import com.gs.email.Mail;
import com.gs.email.MailSender;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Star on 2017/5/19.
 */
@Controller
@RequestMapping("pwd")
public class EditPwdController {

    private Logger logger = (Logger) LoggerFactory.getLogger(PeopleInfoController.class);

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private VilidateService vilidateService;

    @RequestMapping(value = "personal_pwd", method = RequestMethod.GET)
    private String personalPwd() {
        if (SessionGetUtil.isUser()) {
            logger.info(" 个人密码修改页面");
            return "index/editPwd";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    @ResponseBody
    @RequestMapping(value = "selfPwd", method = RequestMethod.GET)
    public ControllerResult querySelf(@Param("oldPwd") String oldPwd, @Param("rePwd") String rePwd) {
        if (SessionGetUtil.isUser()) {
            try {
                logger.info("修改密码");
                User user = SessionGetUtil.getUser();
                String encryOldPwd = EncryptUtil.md5Encrypt(oldPwd);
                String encryNewPwd = EncryptUtil.md5Encrypt(rePwd);
                if (!encryOldPwd.equals(user.getUserPwd())) {
                    return ControllerResult.getFailResult("请输入正确的旧密码");
                } else if (encryNewPwd.equals(user.getUserPwd())) {
                    return ControllerResult.getFailResult("新密码和旧密码一致");
                }
                user.setUserPwd(encryNewPwd);
                userService.updatePwd(user);
                return ControllerResult.getSuccessResult("密码修改成功，下次登录请使用新密码登录");

            } catch (Exception e) {
                logger.info("添加信息失败，出现了异常");
                return ControllerResult.getFailResult("添加信息失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登录信息已失效，请重新登录");
        }
    }
    private String phone;
    @ResponseBody
    @RequestMapping("sendCode")
    public ControllerResult sendCode(@Param("number") String number, HttpSession session) {
        logger.info("获取验证码");
        Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        Matcher m = p.matcher(number);
        boolean email = m.matches();
        String code = GetCodeUtil.getCode(6,0);
        phone = number;
        session.setAttribute(number,code);
        if(email){
            Mail mail = new Mail();
            mail.setRecipients(number);
            mail.setSubject("修改密码提醒");
            mail.setType(Mail.HTML);
            Multipart multipart = new MimeMultipart();
            BodyPart part1 = new MimeBodyPart();
            try {
                part1.setContent("<p>【创意科技】您的验证码为" + code + "，请于30分钟内正确输入，如非本人操作，请忽略此邮件。</p>", mail.getType());
                multipart.addBodyPart(part1);
                mail.setMultipart(multipart);
            } catch (MessagingException e) {
            }
            MailSender mailSender = new MailSender();
            mailSender.sendEmailByType(Constants.MAIL_TYPE, mail, Constants.MAIL_SENDER, Constants.MAIL_PASSWORD);
        }else{
            String to = number;
            String smsContent = "【创意科技】您的验证码为" + code + "，请于30分钟内正确输入，如非本人操作，请忽略此短信。";
            IndustrySMS is = new IndustrySMS(to, smsContent);
            is.execute();
        }
        return ControllerResult.getSuccessResult("验证码发送成功，请注意查收");
    }

    @ResponseBody
    @RequestMapping("checkPhone")
    public String checkPhone(@Param("number") String number) {
        logger.info("判断是否存在该用户");
        Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        Matcher m = p.matcher(number);
        boolean email = m.matches();
        if(email){
            if(vilidateService.queryDataIsExistUserEmail(number) >= 1){
                return "true";
            }else{
                return "false";
            }
        }else{
            if(vilidateService.queryDataIsExistUserPhone(number) >= 1){
                return "true";
            }else{
                return "false";
            }
        }
    }
    @ResponseBody
    @RequestMapping(value = "editPwd", method = RequestMethod.GET)
    public ControllerResult editPwd(@Param("number")String number,@Param("pwd")String pwd,HttpSession session){
        logger.info("根据手机或邮箱修改密码");
        Pattern p = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
        Matcher m = p.matcher(number);
        boolean email = m.matches();
        User user = new User();
        String pwd1 =  EncryptUtil.md5Encrypt(pwd);
        if(email){
            user.setUserEmail(number);
        }else{
            user.setUserPhone(number);
        }
        user.setUserPwd(pwd1);
        userService.updatePwdPhone(user);
        session.removeAttribute(number);
        return ControllerResult.getSuccessResult("密码修改成功");
    }


    @ResponseBody
    @RequestMapping(value = "code", method = RequestMethod.GET)
    public String getCode(HttpSession session){
        logger.info("返回找回密码验证码");
        String code = (String)session.getAttribute(phone);
        return Base64Util.getBase64(code);
    }

    private String phone1;
    @ResponseBody
    @RequestMapping("sendCode1")
    public ControllerResult sendCode1(@Param("number") String number, HttpSession session) {
        logger.info("手机号码动态登入，获取验证码");
        String code = GetCodeUtil.getCode(6,0);
        phone1 = number;
        session.setAttribute(number,code);
        String to = number;
        String smsContent = "【创意科技】您的验证码为" + code + "，请于30分钟内正确输入，如非本人操作，请忽略此短信。";
        IndustrySMS is = new IndustrySMS(to, smsContent);
        is.execute();
        return ControllerResult.getSuccessResult("验证码发送成功，请注意查收");
    }

    @ResponseBody
    @RequestMapping(value = "code1", method = RequestMethod.GET)
    public String getCode1(HttpSession session){
        logger.info("返回动态登入验证码");
        String code = (String)session.getAttribute(phone1);
        return Base64Util.getBase64(code);
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ControllerResult login(String phone) {
        logger.info("用户" + phone + "使用手机动态登入");
        Subject subject = SecurityUtils.getSubject();
        User user = userService.queryByPhone(phone);
        if (user != null) {
            if (user.getUserStatus().equals("Y")) {
                Role role = roleService.queryByUserId(user.getUserId());
                if (!role.getRoleName().equals(Constants.CAR_OWNER)) {
                    userService.updateLoginTime(user.getUserId());
                    user.setUserLoginedTime(new Date());
                    subject.login(new UsernamePasswordToken(user.getUserId(), user.getUserPwd()));
                    Session session = subject.getSession();
                    session.setAttribute("user", user);
                    return ControllerResult.getSuccessResult("adminHome");
                } else {
                    userService.updateLoginTime(user.getUserId());
                    user.setUserLoginedTime(new Date());
                    subject.login(new UsernamePasswordToken(user.getUserId(), user.getUserPwd()));
                    Session session = subject.getSession();
                    session.setAttribute("user", user);
                    return ControllerResult.getSuccessResult("customerHome");
                }
            } else {
                return ControllerResult.getFailResult("登录失败,此账号已被冻结!");
            }
        } else {
            return ControllerResult.getFailResult("登录失败,该手机号不存在!");
        }
    }
}
