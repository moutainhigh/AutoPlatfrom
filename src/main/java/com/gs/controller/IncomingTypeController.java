package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.IncomingType;
import com.gs.bean.Salary;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.IncomingTypeService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiao-kang on 2017/4/16.
 */
@Controller
@RequestMapping("/incomingType")
public class IncomingTypeController {

    private Logger logger = (Logger) LoggerFactory.getLogger(IncomingTypeController.class);


    @Resource
    private IncomingTypeService incomingTypeService;


    // 可以查看的角色：董事长、财务员、超级管理员、普通管理员
    private String queryRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_ACCOUNTING + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;

    // 可以修改的角色：董事长、财务员
    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_ACCOUNTING;

    @RequestMapping(value = "show_incomingType", method = RequestMethod.GET)
    public String incomingType() {
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRole)) {
                logger.info("显示收入类型页面");
                return "financeManage/incoming_type";
            }
            return "error/notPermission";
        }else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    @ResponseBody
    @RequestMapping(value="query_pager",method= RequestMethod.GET)
    public Pager4EasyUI<IncomingType> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRole)) {
                logger.info("分页查询所有收入类型");
                Pager pager = new Pager();
                User user = SessionGetUtil.getUser();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setTotalRecords(incomingTypeService.count(user));
                List<IncomingType> incomingTypes = incomingTypeService.queryByPager(pager,user);
                return new Pager4EasyUI<IncomingType>(pager.getTotalRecords(), incomingTypes);
            }
            return null;
        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value="add_incomingType", method=RequestMethod.POST)
    public ControllerResult incomingTypeAdd(IncomingType incomingType){
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(editRole)) {
                logger.info("添加收入类型");
                User user = SessionGetUtil.getUser();
                incomingType.setCompanyId(user.getCompanyId());
                incomingTypeService.insert(incomingType);
                return ControllerResult.getSuccessResult("添加收入类型成功");
            }
            return ControllerResult.getFailResult("添加收入类型失败，没有该权限");
        } else{
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value="update_incomingType", method=RequestMethod.POST)
    public ControllerResult incomingUpdate(IncomingType incomingType){
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(editRole)) {
                logger.info("更新收入类型");
                User user = SessionGetUtil.getUser();
                incomingType.setCompanyId(user.getCompanyId());
                incomingTypeService.update(incomingType);
                return ControllerResult.getSuccessResult("更新收入类型成功");
            }
            return ControllerResult.getFailResult("更新收入类型失败，没有该权限");
        }else{
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value="update_status", method=RequestMethod.GET)
    public ControllerResult updateStatus(@Param("id") String id, @Param("status")String status){
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(editRole)) {
                logger.info("更新收入类型状态");
                if (status.equals("Y")) {
                    incomingTypeService.active(id);
                } else if (status.equals("N")) {
                    incomingTypeService.inactive(id);
                }

                return ControllerResult.getSuccessResult("更新收入类型状态成功");
            }
            return ControllerResult.getFailResult("更新收入类型状态失败，没有该权限");
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    @ResponseBody
    @RequestMapping(value="query_status", method=RequestMethod.GET)
    public Pager4EasyUI<IncomingType> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,@Param("status")String status){
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRole)) {
                logger.info("根据收入类型状态查询");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                User user = SessionGetUtil.getUser();
                List<IncomingType> incomingTypes = null;
                if (status.equals("Y")) {
                    incomingTypes = incomingTypeService.queryPagerStatus(status, pager,user);
                    pager.setTotalRecords(incomingTypeService.countStatus(status,user));
                } else if (status.equals("N")) {
                    incomingTypes = incomingTypeService.queryPagerStatus(status, pager,user);
                    pager.setTotalRecords(incomingTypeService.countStatus(status,user));
                } else if (status.equals("ALL")) {
                    pager.setTotalRecords(incomingTypeService.count(user));
                    incomingTypes = incomingTypeService.queryByPager(pager,user);
                }
                return new Pager4EasyUI<IncomingType>(pager.getTotalRecords(), incomingTypes);
            }
            return null;
        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value="query_condition",method= RequestMethod.GET)
    public Pager4EasyUI<IncomingType> queryPagerCondition(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize
                                                        ,@Param("inTypeName")String inTypeName,@Param("companyId")String companyId){
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRole)) {
                logger.info("根据条件分页查询所有收入类型");
                Pager pager = new Pager();
                User user = SessionGetUtil.getUser();
                if(user.getCompanyId() != null && !user.getCompanyId().equals("")){
                    companyId = user.getCompanyId();
                }
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setTotalRecords(incomingTypeService.countCondition(companyId,inTypeName));
                List<IncomingType> incomingTypes = incomingTypeService.queryByPagerCondition(companyId,inTypeName,pager);
                return new Pager4EasyUI<IncomingType>(pager.getTotalRecords(), incomingTypes);
            }
            return null;
        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "inType_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryUserAll() {
        try {
            logger.info("查询所有收入类型");
            User user = SessionGetUtil.getUser();
            List<IncomingType> incomingTypes = incomingTypeService.queryAll(user);
            List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
            for (IncomingType in : incomingTypes) {
                ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                comboBox4EasyUI.setId(in.getInTypeId());
                comboBox4EasyUI.setText(in.getInTypeName());
                comboBox4EasyUIs.add(comboBox4EasyUI);
            }
            return comboBox4EasyUIs;
        }catch (Exception e){
            return null;
        }
    }

}
