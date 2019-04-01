package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Company;
import com.gs.bean.Role;
import com.gs.bean.User;
import com.gs.bean.UserRole;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.util.*;
import com.gs.service.CompanyService;
import com.gs.service.RoleService;
import com.gs.service.UserRoleService;
import com.gs.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("message")
/**
 * Created by Star on 2017/5/18.
 */
public class MessageController {
    private Logger logger = (Logger) LoggerFactory.getLogger(PeopleInfoController.class);

    @Resource
    private UserService userService;

    @RequestMapping(value = "personal_message", method = RequestMethod.GET)
    private String personalMessage() {
        if (SessionGetUtil.isUser()) {
                logger.info(" 个人基本信息页面");
                return "index/message";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryBy_self", method = RequestMethod.POST)
    public ControllerResult querySelf(String uIcon, String address, User user, MultipartFile file, HttpSession session) throws IOException {
        if (SessionGetUtil.isUser()) {
            try {
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
                userService.updateMessage(user);
                user.setUserAddress(address);
                return ControllerResult.getSuccessResult("修改信息成功");
            } catch (Exception e) {
                logger.info("修改信息失败，出现了异常");
                return ControllerResult.getFailResult("修改信息失败，出现了一个错误");
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登录信息已失效，请重新登录");
        }
    }
}

