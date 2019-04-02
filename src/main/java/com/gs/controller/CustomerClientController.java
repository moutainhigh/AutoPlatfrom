package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.AccessoriesType;
import com.gs.bean.Appointment;
import com.gs.bean.User;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Asa
 * @date 2017/5/10
 */
@Controller
@RequestMapping("customerClient")
public class CustomerClientController {

    private Logger logger = (Logger) LoggerFactory.getLogger(CustomerClientController.class);

    /**
     * 进入车主后台
     * @return
     */
    @RequestMapping("/home")
    public String supplierInfo() {
            logger.info("进入车主后台");
            return "customerClient/home";
    }

    /**
     * 我的维修保养进度
     * @return
     */
    @RequestMapping(value = "progress", method = RequestMethod.GET)
    public String progress(){
        logger.info("我的维修保养进度");
        return "customerClient/progress";
    }

    /**
     * 我的维修保养记录
     * @return
     */
    @RequestMapping(value = "record", method = RequestMethod.GET)
    public String record(){
        logger.info("我的维修保养记录");
        return "customerClient/record";
    }

    /**
     * 我要预约
     * @param session
     * @return
     */
    @RequestMapping(value = "app", method = RequestMethod.GET)
    public String app(HttpSession session){
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
        User user = (User) session.getAttribute("user");
        if (user.getUserName() != null && !"".equals(user.getUserName()) && user.getUserPhone() != null && !"".equals(user.getUserPhone())) {
            logger.info("我要预约");
            return "customerClient/app";
        } else {
            logger.info("个人资料不完善");
            return "error/notData";
        }

    }
}
