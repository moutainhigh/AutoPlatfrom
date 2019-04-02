package com.gs.controller;

import com.gs.bean.UserRole;
import com.gs.common.bean.ControllerResult;
import com.gs.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Xiao-Qiang on 2017/5/3.
 */
@Controller
@RequestMapping("userRole")
public class UserRoleController {

    private Logger logger = (Logger) LoggerFactory.getLogger(UserRoleController.class);

    @Resource
    private UserRoleService userRoleService;

    /**
     * 添加用户角色
     * @param userRole
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add_userRole", method = RequestMethod.POST)
    public ControllerResult addModule(UserRole userRole) {
        logger.info("添加用户角色");
        userRoleService.insert(userRole);
        return ControllerResult.getSuccessResult("添加成功");
    }
}
