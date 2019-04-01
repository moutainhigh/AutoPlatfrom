package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.message.IndustrySMS;
import com.gs.common.util.Base64Util;
import com.gs.common.util.EncryptUtil;
import com.gs.common.util.GetCodeUtil;
import com.gs.service.*;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by JanGo on 2017/5/10.
 */
@Controller
@RequestMapping("customerClientWeb")
public class CustomerClientWebController {

    private Logger logger = (Logger) LoggerFactory.getLogger(CustomerClientWebController.class);


    @Resource
    private MaintainRecordService maintainRecordService;  //保养

    @Resource
    private AppointmentService appointmentService;  //预约

    @Resource
    private UserService userService;  //用户

    @RequestMapping("/index")
    public String supplierInfo() {
            logger.info("进入公司前台显示");
            return "index/index";

    }

    @RequestMapping("/features")
    public String webFeatures() {
        logger.info("进入功能特性页面");
        return "index/features";

    }

    @RequestMapping("/contact")
    public String webContact() {
        logger.info("进入联系我们页面");
        return "index/contact";

    }

    @RequestMapping("/tour")
    public String webTour() {
        logger.info("进入平台特性");
        return "index/tour";

    }

    @RequestMapping("/pricing")
    public String webPricing() {
        logger.info("进入平台收费");
        return "index/pricing";

    }

    @RequestMapping("/customerCar")
    public String webCustomerCar() {
        logger.info("进入用户进度查询");
        return "index/customerCar";

    }

    @RequestMapping("/appointment")
    public String webAppointment() {
        logger.info("进入用户预约");
        return "index/appointment";

    }

    @ResponseBody
    @RequestMapping(value = "addCustomer", method = RequestMethod.POST)
    public ControllerResult appointmentAddCustomer(Appointment appointment) {
            logger.info("车主预约");

            appointment.setSpeedStatus(Constants.APPOINTMENT);
            appointmentService.insert(appointment);
            return ControllerResult.getSuccessResult("成功预约");

    }


    @RequestMapping(value = "userPage", method = RequestMethod.GET)
    public ModelAndView queryPagerByCondition(String phone) {
        ModelAndView md = new ModelAndView("index/iframeTable");
        if (phone != null || !phone.equals("")) {
            List<MaintainRecord> maintainRecords  = maintainRecordService.queryCustomerCar(phone);
            md.addObject("records",maintainRecords);
            return md;
        }
        return md;

    }

    private String phone;

    @ResponseBody
    @RequestMapping("sendCode")
    public ControllerResult sendCode(@Param("userPhone") String userPhone, HttpSession session) {
        logger.info("获取短信验证码");
        String to = userPhone;
        String code = GetCodeUtil.getCode(6,0);
        phone = userPhone;
        session.setAttribute(userPhone,code);
        String smsContent = "【创意科技】您的验证码为" + code + "，请于30分钟内正确输入，如非本人操作，请忽略此短信。";
        IndustrySMS is = new IndustrySMS(to, smsContent);
        is.execute();
        return ControllerResult.getSuccessResult("验证码发送成功，请注意查收");
    }


    @ResponseBody
    @RequestMapping(value = "code", method = RequestMethod.GET)
    public String getCode(HttpSession session){
        logger.info("返回验证码");
        String code = (String)session.getAttribute(phone);
        return Base64Util.getBase64(code);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
