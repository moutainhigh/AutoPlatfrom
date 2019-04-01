package com.gs.controller;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Xiao-Qiang on 2017/4/17.
 */
@Controller
@RequestMapping("process")
public class ProcessController {

    private Logger logger = (Logger) LoggerFactory.getLogger(ProcessController.class);

    @RequestMapping(value = "info", method = RequestMethod.GET)
    private String showProcessInfo() {
        logger.info("显示流程信息");
        return "system/process";
    }
}
