package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.*;
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
 * Created by JangoGuo on 2017/4/18.
 */
@Controller
@RequestMapping("/trackVisit")
public class TrackVisitController {

    private Logger logger = (Logger) LoggerFactory.getLogger(TrackVisitController.class);


    @Resource
    private TrackListService trackListService;

    @Resource
    private CheckinService checkinService;

    @Resource
    private MaintainRecordService maintainRecordService;

    @Resource
    private UserService userService;

    private String queryRole = Constants.COMPANY_ADMIN + ","+ Constants.COMPANY_RECEIVE + ","+ Constants.CAR_OWNER+ ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;;
    private String editRole = Constants.COMPANY_ADMIN + ","+ Constants.COMPANY_RECEIVE;

    @RequestMapping(value = "show_trackVisit", method = RequestMethod.GET)
    public String messageSend() {
        if (!SessionGetUtil.isUser()) {
            logger.info("登陆已失效，请重新登入");
            return "index/notLogin";
        }if (!CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("无权访问，想要访问请联系管理员!");
            return "error/notPermission";
        }
        logger.info("显示追踪访问页面");
        return "customer/track_visit";
    }

    @ResponseBody
    @RequestMapping(value="query_pager",method= RequestMethod.GET)
    public Pager4EasyUI<TrackList> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        logger.info("分页查询所有追踪访问");
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole) ) {
            logger.info("登陆已失效，请重新登入");
            return null;
        }
        Pager pager = new Pager();
        User LoginUser = SessionGetUtil.getUser();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(trackListService.count(LoginUser));
        List<TrackList> trackListList = trackListService.queryByPager(pager,LoginUser);
        return new Pager4EasyUI<TrackList>(pager.getTotalRecords(), trackListList);
    }

    @ResponseBody
    @RequestMapping(value="add_track", method=RequestMethod.POST)
    public ControllerResult trackAdd(TrackList trackList,HttpSession session) {
        logger.info("添加回访");
        if (!SessionGetUtil.isUser()) {
            logger.info("登陆已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        if (!CheckRoleUtil.checkRoles(editRole)) {
            logger.info("添加失败");
            return ControllerResult.getFailResult("添加失败，没有该权限操作");
        }
        try {
            User user = SessionGetUtil.getUser();
            trackList.setTrackUser(user.getUserId());
            trackList.setCompanyId(user.getCompanyId());
            trackListService.insert(trackList);
            Checkin checkin = checkinService.queryByTrackStatus(trackList.getUserId(),user);
            maintainRecordService.updateTrackStatus("Y",checkin.getCheckinId());
            return ControllerResult.getSuccessResult("添加成功");
        } catch (Exception e) {
            logger.info("添加失败，出现了一个错误");
            return ControllerResult.getFailResult("添加失败，出现了一个错误");
        }

    }
}

































