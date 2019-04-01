package com.gs.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by root on 2017/4/19.
 */
@Controller
@RequestMapping("/commonFault")
public class CommonFaultController {
    private org.slf4j.Logger logger = (org.slf4j.Logger) org.slf4j.LoggerFactory.getLogger(CommonFaultController.class);



}
