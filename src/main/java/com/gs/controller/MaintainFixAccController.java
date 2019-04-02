package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.MaintainFixAcc;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.dao.MaintainFixAccDAO;
import com.gs.service.MaintainFixAccService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author root
 * @date 2017/5/2
 */
@Controller()
@RequestMapping("/maintainFixAcc")
public class MaintainFixAccController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MaintainFixAccController.class);

    @Resource
    private MaintainFixAccService maintainFixAccService;

    private String queryRole  = Constants.COMPANY_ADMIN + ","
            + Constants.SYSTEM_SUPER_ADMIN + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + ","
            + Constants.COMPANY_HUMAN_MANAGER + ","
            + Constants.COMPANY_ACCOUNTING + ","
            + Constants.COMPANY_EMP + ","
            + Constants.COMPANY_SALES + ","
            + Constants.COMPANY_ARTIFICER + ","
            + Constants.COMPANY_RECEIVE;

    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_ARTIFICER;

    /**
     * 可以查看的角色：董事长、财务员、超级管理员、普通管理员
     */
    private String queryRole1 = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_ACCOUNTING + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;

    /**
     * 添加项目基础配件
     * @param maintainFixAccList
     * @return
     */
    @RequestMapping(value = "insertList", method = RequestMethod.POST)
    public ControllerResult insertList(List<MaintainFixAcc> maintainFixAccList) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            logger.info("添加项目基础配件");
            maintainFixAccService.batchInsert(maintainFixAccList);
            for (MaintainFixAcc acc : maintainFixAccList) {
                System.out.println(acc);
            }
            return ControllerResult.getSuccessResult("添加成功");
        } catch (Exception e) {
            logger.info("添加失败，出现了一个错误");
            return ControllerResult.getFailResult("添加失败，出现了一个错误");
        }
    }

    /**
     * 添加配件成功
     * @param AccessoriesId
     * @param maintenanceltemId
     * @param count
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "insert", method = RequestMethod.GET)
    public ControllerResult insertAcc(@Param("AccessoriesId")String AccessoriesId,@Param("maintenanceltemId")String maintenanceltemId,@Param("count")String count){
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try{
            MaintainFixAcc maintainFixAcc = new MaintainFixAcc();
            maintainFixAcc.setAccCount(Integer.valueOf(count));
            maintainFixAcc.setAccId(AccessoriesId);
            maintainFixAcc.setMaintainId(maintenanceltemId);
            maintainFixAccService.insert(maintainFixAcc);
            logger.info("添加成功");
            return ControllerResult.getSuccessResult("添加配件成功");
        }catch(Exception e){
            logger.info("添加失败，出现了一个错误");
            return ControllerResult.getFailResult("添加失败，出现了一个错误");
        }
    }

    /**
     * 查询项目下所有配件
     * @param id
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "accPager", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainFixAcc> searchRepair(@Param("id")String id, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser()|| !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        Pager pager = new Pager();
        logger.info("查询项目下所有配件");
        User user = SessionGetUtil.getUser();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(maintainFixAccService.queryAllByCount(id,user));
        List<MaintainFixAcc> maintainFixList = maintainFixAccService.queryAllByPager(id,user,pager);
        return new Pager4EasyUI<>(pager.getTotalRecords(), maintainFixList);
    }

}
