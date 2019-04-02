package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Company;
import com.gs.bean.Complaint;
import com.gs.bean.MessageSend;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.message.IndustrySMS;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.CompanyService;
import com.gs.service.ComplaintService;
import com.gs.service.MessageSendService;
import com.gs.thread.SendMessageThread;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JangoGuo on 2017/4/18.
 */
@Controller
@RequestMapping("/MessageSend")
public class MessageSendController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MessageSendController.class);


    @Resource
    private MessageSendService messageSendService;

    @Resource
    private CompanyService companyService;


    private String queryRole = Constants.COMPANY_ADMIN + ","+ Constants.COMPANY_RECEIVE+ ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;;
    private String editRole = Constants.COMPANY_ADMIN + ","+ Constants.COMPANY_RECEIVE;

    private String strPhone;

    /**
     * 显示短信发送页面
     * @return
     */
    @RequestMapping(value = "show_MessageSend", method = RequestMethod.GET)
    public String messageSend() {
        if (!SessionGetUtil.isUser()) {
            logger.info("登陆已失效，请重新登入");
            return "index/notLogin";
        }
        if (!CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("无权访问，想要访问请联系管理员!");
            return "error/notPermission";
        }
        logger.info("显示短信发送页面");
        return "customer/message_send";
    }

    /**
     * 分页查询所有短信
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value="query_pager",method= RequestMethod.GET)
    public Pager4EasyUI<MessageSend> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询所有短信");
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("登陆已失效，请重新登入");
            return null;
        }

        User loginUser = SessionGetUtil.getUser();
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(messageSendService.count(loginUser));
        List<MessageSend> complaintList = messageSendService.queryByPager(pager,loginUser);
        return new Pager4EasyUI<>(pager.getTotalRecords(), complaintList);
    }

    /**
     * 更新短信发送
     * @param idList
     * @param sendMsg
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update_messageSend", method = RequestMethod.GET)
    public ControllerResult updateMessageSend(@Param("idList")String[] idList, @Param("sendMsg")String sendMsg){
        logger.info("更新短信发送");
        if (!SessionGetUtil.isUser()) {
            logger.info("登陆已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        if (!CheckRoleUtil.checkRoles(editRole)) {
            logger.info("更新失败");
            return ControllerResult.getFailResult("更新失败，没有该权限操作");
        }
        try{
            messageSendService.batchUpdateBySendMsg(idList, sendMsg);

            return ControllerResult.getSuccessResult("更新成功");
        } catch (Exception e) {
            logger.info("更新失败，出现了一个错误");
            return ControllerResult.getFailResult("更新失败，出现了一个错误");
        }
    }

    /**
     * 一键短信发送
     * @param idList
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update_success", method = RequestMethod.GET)
    public ControllerResult updateMessageSend(String[] idList){
        logger.info("一键短信发送");
        if (!SessionGetUtil.isUser()) {
            logger.info("登陆已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        if (!CheckRoleUtil.checkRoles(editRole)) {
            logger.info("更新失败");
            return ControllerResult.getFailResult("更新失败，没有该权限操作");
        }
       try{
           System.out.println(strPhone+ "-----------------phone");
           messageSendService.batchUpdateBySuccess(idList);
           String content = "尊敬的用户，您已经成功入驻我们的平台，您可以使用您的手机号进行登入我们的系统了哦，登入密码为默认密码";
           String to = strPhone;
           IndustrySMS is = new IndustrySMS(to, content);
           is.execute();
            return ControllerResult.getSuccessResult("发送成功");
        } catch (Exception e) {
            logger.info("更新失败，出现了一个错误");
            return ControllerResult.getFailResult("更新失败，出现了一个错误");
        }
    }


    /**
     * 提供所有Id号
     * @return List<MessageSend>
     */
    @ResponseBody
    @RequestMapping(value = "queryAllId", method = RequestMethod.GET)
    public List<MessageSend> queryAllId(){
        logger.info("提供所有Id号");
        if (!SessionGetUtil.isUser()  ) {
            logger.info("登陆已失效，请重新登入");
            return null;
        }
        User loginUser = SessionGetUtil.getUser();
        List<MessageSend> mesList  = messageSendService.queryAll(loginUser);
        strPhone = "";
        for(MessageSend ms: mesList){
            if("".equals(strPhone)){
                strPhone = ms.getUser().getUserPhone();
            }else {
                strPhone +=  "," + ms.getUser().getUserPhone();
            }
        }
        return mesList;
    }

    /**
     * 添加messageId
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addMessageId", method = RequestMethod.GET)
    public ControllerResult addMessageId(String[] userId) {
        logger.info("添加messageId");
        if (!SessionGetUtil.isUser()) {
            logger.info("登陆已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        if (!CheckRoleUtil.checkRoles(editRole)) {
            logger.info("添加失败");
            return ControllerResult.getFailResult("添加失败，没有该权限操作");
        }
        try {
            List<MessageSend> msList = new ArrayList<>();
            User user = SessionGetUtil.getUser();
            for (String anUserId : userId) {
                MessageSend m = new MessageSend();
                m.setCompanyId(user.getCompanyId());
                m.setUserId(anUserId);
                msList.add(m);
            }
            messageSendService.addMessageId(msList);
            return ControllerResult.getSuccessResult("插入成功");
        } catch (Exception e) {
            logger.info("添加失败，出现了一个错误");
            return ControllerResult.getFailResult("添加失败，出现了一个错误");
        }
    }


}

































