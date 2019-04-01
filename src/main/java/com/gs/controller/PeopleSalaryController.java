package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Salary;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.SalaryService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.session.Session;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 温鑫
 * 员工工资查看
 * Created by Star on 2017/5/4.
 */

@Controller
@RequestMapping("peopleManage")
public class PeopleSalaryController {

    private Logger logger = (Logger) LoggerFactory.getLogger(PeopleSalaryController.class);

    @Resource
    private SalaryService salaryService;

    private String queryRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_REPERTORY
            + "," + Constants.COMPANY_RECEIVE + "," + Constants.COMPANY_ARTIFICER
            + "," + Constants.COMPANY_SALES + "," + Constants.COMPANY_HUMAN_MANAGER
            + "," + Constants.COMPANY_ACCOUNTING + "," + Constants.COMPANY_BUYER
            + "," + Constants.COMPANY_EMP;

    @RequestMapping(value = "salary", method = RequestMethod.GET)
    public String customerInfo() {
        if (SessionGetUtil.isUser()) {
            if (CheckRoleUtil.checkRoles(queryRole)) {
                logger.info(" 员工工资查看页面");
                return "peopleManage/peopleSalary";
            }
            return "error/notPermission";
        } else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    @ResponseBody
    @RequestMapping(value="peopleSalary",method= RequestMethod.GET)
    public Pager4EasyUI<Salary> queryPager( @Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,HttpSession session){
        if (SessionGetUtil.isUser()) {
            try {
                logger.info("查询工资");
                User user = (User)session.getAttribute("user");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setTotalRecords(salaryService.countByUserId(user.getUserId()));
                List<Salary> salary = salaryService.queryByUserId(pager,user.getUserId());
                return new Pager4EasyUI<Salary>(pager.getTotalRecords(), salary);
            } catch (Exception e) {
                logger.info("分页查询失败，出现了异常");
                return null;
            }
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }
}
