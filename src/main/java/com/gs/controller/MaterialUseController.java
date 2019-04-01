package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.bean.*;
import com.gs.bean.info.MaterialReturnInfo;
import com.gs.bean.info.MaterialUseInfo;
import com.gs.common.Constants;
import com.gs.common.bean.*;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Xiao-Qiang on 2017/4/17.
 */
@Controller
@RequestMapping("materialUse")
public class MaterialUseController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MaterialUseController.class);

    private String queryRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_REPERTORY + "," +
            Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN + "," + Constants.COMPANY_ARTIFICER;

    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_REPERTORY;

    private String editRole1 = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_REPERTORY + "," + Constants.COMPANY_ARTIFICER;


    // 可以查看的角色：董事长、财务员、超级管理员、普通管理员
    private String queryRole1 = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_ACCOUNTING + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;


    @Resource
    private MaintainRecordService mrs;

    @Resource
    private MaterialUseInfoService muis;

    @Resource
    private AccessoriesService as;

    @Resource
    private AccessoriesTypeService accessoriesTypeService;

    @Resource
    private MaterialUseService materialUseService;

    @Resource
    private MaterialReturnService materialReturnService;

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String showMaterialUseInfo() {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
        if (!CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("无权访问，想要访问请联系管理员!");
            return "error/notPermission";
        }
        logger.info("显示领料信息");
        return "dispatchingPicking/material_use";
    }

    @ResponseBody
    @RequestMapping(value = "query_pager", method = RequestMethod.GET)
    public Pager4EasyUI<MaterialUseInfo> queryBySpeedStatus(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, @Param("recordId") String recordId) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或权限不足，无法查看！");
            return null;
        }
        User user = SessionGetUtil.getUser();
        logger.info("分页查询当前维修记录领料明细");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(muis.countBySpeedStatus(recordId, user));
        List<MaterialUseInfo> materialUseInfos = muis.queryBySpeedStatus(pager, recordId, user);
        return new Pager4EasyUI<MaterialUseInfo>(pager.getTotalRecords(), materialUseInfos);
    }

    @ResponseBody
    @RequestMapping(value = "updatePickingStatusById", method = RequestMethod.GET)
    public ControllerResult updatePickingStatus(@Param("id") String id, @Param("status") String status) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole)) {
                logger.info("更新失败");
                return ControllerResult.getFailResult("更新失败，没有该权限操作");
            }
            logger.info("更新领料审核状态");
            User user = SessionGetUtil.getUser();
            mrs.updatePickingStatusById(status, id);
            List<MaterialUseInfo> materialUseInfoList = muis.queryAll(id, user);
            List<Accessories> accessories = new ArrayList<Accessories>();
            for (MaterialUseInfo mui : materialUseInfoList) {
                Accessories a = new Accessories();
                a.setAccId(mui.getAccId());
                Accessories a2 = as.queryByIdTotalAndIdle(a.getAccId());
                a.setAccTotal(a2.getAccTotal() - mui.getAccCount());
                a.setAccIdle(a2.getAccIdle() - mui.getAccCount());
                accessories.add(a);
            }
            as.updateTotalAndIdle(accessories);
            return ControllerResult.getSuccessResult("更新成功!");
        } catch (Exception e) {
            logger.info("更新失败，出现了一个错误");
            return ControllerResult.getFailResult("更新失败，出现了一个错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryIs_CountThan", method = RequestMethod.GET)
    public String queryIsCountThan(@Param("accCount")String accCount, @Param("materialUseId")String materialUseId) {
        try {
            int accCount2 = Integer.valueOf(accCount);
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            MaterialUseInfo mui = muis.queryByIdAccCount(materialUseId);
            int accCount1 = mui.getAccCount();
            if (accCount2 > accCount1) {
                    result = false;
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
        } catch (Exception e) {
            logger.info("退料数量验证失败，出现了异常");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "add_material", method = RequestMethod.GET)
    public ControllerResult addByRecordIdMu(@Param("recordId") String recordId, @Param("accIds") String[] accIds, @Param("accCounts") int[] accCounts) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
        try {
            if (!CheckRoleUtil.checkRoles(editRole1)) {
                logger.info("申请失败");
                return ControllerResult.getFailResult("申请失败，没有该权限操作");
            }
            logger.info("申请领料");
            List<MaterialUseInfo> materialUseInfos = new ArrayList<MaterialUseInfo>();
            for (int i = 0, length = accIds.length; i < length; i++) {
                MaterialUseInfo mui = new MaterialUseInfo();
                mui.setRecordId(recordId);
                mui.setAccId(accIds[i]);
                mui.setAccCount(accCounts[i]);
                materialUseInfos.add(mui);
            }
            muis.addByRecordIdMu(materialUseInfos);
            mrs.updatePickingStatusById(Constants.NOT_AUDITED, recordId);
            return ControllerResult.getSuccessResult("申请成功");
        } catch (Exception e) {
            logger.info("申请失败，出现了一个错误");
            return ControllerResult.getFailResult("申请失败，出现了一个错误");
        }
    }

    @ResponseBody
    @RequestMapping(value="query_default",method= RequestMethod.GET)
    public List<LineBasic> queryAll(@Param("companyId")String companyId, @Param("quantity")String quantity){
        if(SessionGetUtil.isUser()) {

                logger.info("默认查询本月配件使用统计");
                List<LineBasic> lineBasics = new ArrayList<LineBasic>();
                User user = SessionGetUtil.getUser();
                if(user.getCompanyId() != null && !user.getCompanyId().equals("")){
                    companyId = user.getCompanyId();
                }
                List<AccessoriesType> accessoriesTypes = accessoriesTypeService.queryByCompany(companyId);
                for(AccessoriesType accType: accessoriesTypes){
                    LineBasic lineBasic = new LineBasic();
                    lineBasic.setName(accType.getAccTypeName());
                    if(quantity.equals("领料数量")) {
                        dateDay("one", companyId, accType.getAccTypeId());
                        lineBasic.setData(HighchartsData.doubleDayOne);
                    }else if(quantity.equals("退料数量")){
                        dateDay("two", companyId, accType.getAccTypeId());
                        lineBasic.setData(HighchartsData.doubleDayTwo);
                    }
                    lineBasic.setCategories(HighchartsData.strDay);
                    lineBasics.add(lineBasic);
                }
                return lineBasics;

        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }

    }

    @ResponseBody
    @RequestMapping(value="query_condition",method= RequestMethod.GET)
    public List<LineBasic> queryCondition(@Param("start")String start,@Param("end")String end,
                                          @Param("type")String type,@Param("companyId")String companyId,
                                          @Param("quantity")String quantity){
        if(SessionGetUtil.isUser()) {

                logger.info("根据年，月，季度，周，日查询配件使用统计");
                List<LineBasic> lineBasics = new ArrayList<LineBasic>();
                User user = SessionGetUtil.getUser();
                if (user.getCompanyId() != null && !user.getCompanyId().equals("")) {
                    companyId = user.getCompanyId();
                }
                List<AccessoriesType> accessoriesTypes = accessoriesTypeService.queryByCompany(companyId);
                if (start != null && !start.equals("") && end != null && !end.equals("") && type != null && !type.equals("")) {
                    if (type.equals("year")) {
                        HighchartsData.setStrYear(start, end);
                        for(AccessoriesType accType: accessoriesTypes){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(accType.getAccTypeName());
                            if(quantity.equals("领料数量")) {
                                dataCondition(start,end,type,"year","one",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleYearOne);
                            }else if(quantity.equals("退料数量")){
                                dataCondition(start,end,type,"year","two",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleYearTwo);
                            }
                            lineBasic.setCategories(HighchartsData.strYear);
                            lineBasics.add(lineBasic);
                        }
                    } else if (type.equals("quarter")) {
                        for(AccessoriesType accType: accessoriesTypes){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(accType.getAccTypeName());
                            if(quantity.equals("领料数量")) {
                                dataCondition(start,end,type,"quarter","one",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleQuarterOne);
                            }else if(quantity.equals("退料数量")){
                                dataCondition(start,end,type,"quarter","two",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleQuarterTwo);
                            }
                            lineBasic.setCategories(HighchartsData.strQuarter);
                            lineBasics.add(lineBasic);
                        }
                    } else if (type.equals("month")) {
                        for(AccessoriesType accType: accessoriesTypes){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(accType.getAccTypeName());
                            if(quantity.equals("领料数量")) {
                                dataCondition(start,end,type,"month","one",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleMonthOne);
                            }else if(quantity.equals("退料数量")){
                                dataCondition(start,end,type,"month","two",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleMonthTwo);
                            }
                            lineBasic.setCategories(HighchartsData.strMonth);
                            lineBasics.add(lineBasic);
                        }
                    } else if (type.equals("week")) {
                        HighchartsData.setStrWeek(start, end);
                        for(AccessoriesType accType: accessoriesTypes){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(accType.getAccTypeName());
                            if(quantity.equals("领料数量")) {
                                dataCondition(start,end,type,"week","one",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleWeekOne);
                            }else if(quantity.equals("退料数量")){
                                dataCondition(start,end,type,"week","two",companyId,accType.getAccTypeId());;
                                lineBasic.setData(HighchartsData.doubleWeekTwo);
                            }
                            lineBasic.setCategories(HighchartsData.strWeek);
                            lineBasics.add(lineBasic);
                        }
                    } else if (type.equals("day")) {
                        for(AccessoriesType accType: accessoriesTypes){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(accType.getAccTypeName());
                            if(quantity.equals("领料数量")) {
                                dataCondition(start,end,type,"day","one",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleDayOne);
                            }else if(quantity.equals("退料数量")){
                                dataCondition(start,end,type,"day","two",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleDayTwo);
                            }
                            lineBasic.setCategories(HighchartsData.strDay);
                            lineBasics.add(lineBasic);
                        }
                    }
                }
                return lineBasics;

        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    /*  默认查询本月的配件使用统计
  * */
    public void dateDay(String type,String companyId,String accTypeId){
        HighchartsData.doubleDayTwo = new double[31];
        HighchartsData.doubleDayOne = new double[31];
        List<MaterialUse> materialUses = null;
        List<MaterialReturn> materialReturns = null;
        double[] doubles = null;
        String[] strs = null;
        if(type.equals("one")){
            materialUses = materialUseService.queryByConditionUse(HighchartsData.stdayDate(),HighchartsData.lastDate(),"day",companyId,accTypeId);
            doubles =  new double[materialUses.size()];
            strs = new String[materialUses.size()];
        }else if(type.equals("two")){
            materialReturns = materialReturnService.queryByConditionReturn(HighchartsData.stdayDate(),HighchartsData.lastDate(),"day",companyId,accTypeId);
            doubles =  new double[materialReturns.size()];
            strs = new String[materialReturns.size()];
        }

        if(type.equals("one")){
            int i = 0;
            for(MaterialUse io: materialUses) {
                doubles[i] = io.getAccCount();
                strs[i] = HighchartsData.dateFormat(io.getMuCreatedTime(), "day");
                i++;
                }
        }else if(type.equals("two")){
            int i = 0;
            for(MaterialReturn io: materialReturns) {
                doubles[i] = io.getAccCount();
                strs[i] = HighchartsData.dateFormat(io.getMrCreatedDate(), "day");
                i++;
            }
        }
        for(int j = 0,len = HighchartsData.strDay.length; j <len ; j++){
            for(int k = 0; k < strs.length; k++){
                if(HighchartsData.strDay[j].equals(strs[k])){
                    if(type.equals("two")){
                        HighchartsData.doubleDayTwo[j] = doubles[k];
                    }else if(type.equals("one")){
                        HighchartsData.doubleDayOne[j] = doubles[k];
                    }

                }
            }
        }
    }

    /*
  *  按年，季度，月，周，日，查询 配件使用统计
  * */
    public void dataCondition(String start,String end,String type,String date,String species,String companyId,String accTypeId){
        HighchartsData.doubleDayTwo = new double[31];
        HighchartsData.doubleDayOne = new double[31];
        HighchartsData. doubleMonthTwo = new double[12];
        HighchartsData.doubleMonthOne = new double[12];
        HighchartsData.doubleQuarterTwo = new double[4];
        HighchartsData.doubleQuarterOne = new double[4];
        HighchartsData.doubleYearTwo = new double[HighchartsData.yearLen];
        HighchartsData.doubleYearOne = new double[HighchartsData.yearLen];
        HighchartsData.doubleWeekTwo = new double[HighchartsData.weekLen];
        HighchartsData.doubleWeekOne = new double[HighchartsData.weekLen];
        List<MaterialUse> materialUses = null;
        List<MaterialReturn> materialReturns = null;
        double[] doubles = null;
        String[] strs = null;
        if(species.equals("one")){
            materialUses = materialUseService.queryByConditionUse(start,end,type,companyId,accTypeId);
            doubles =  new double[materialUses.size()];
            strs = new String[materialUses.size()];
        }else if(species.equals("two")){
            materialReturns = materialReturnService.queryByConditionReturn(start,end,type,companyId,accTypeId);
            doubles =  new double[materialReturns.size()];
            strs = new String[materialReturns.size()];
        }
        if(species.equals("one")){
            int i = 0;
            HighchartsData.len = 0;
            for(MaterialUse io: materialUses) {
                if(date.equals("month")) {
                    doubles[i] = io.getAccCount();
                    strs[i] = HighchartsData.dateFormat(io.getMuCreatedTime(),"month");
                    HighchartsData.len = HighchartsData.strMonth.length;
                }else if(date.equals("day")){
                    doubles[i] = io.getAccCount();
                    strs[i] = HighchartsData.dateFormat(io.getMuCreatedTime(),"day");
                    HighchartsData.len = HighchartsData.strDay.length;
                }else if(date.equals("quarter")){
                    doubles[i] = io.getAccCount();
                    strs[i] = HighchartsData.dateFormat(io.getMuCreatedTime(),"quarter");
                    HighchartsData.len = HighchartsData.strQuarter.length;
                }else if(date.equals("year")){
                    doubles[i] = io.getAccCount();
                    strs[i] = HighchartsData.dateFormat(io.getMuCreatedTime(),"year");
                    HighchartsData.len = HighchartsData.strYear.length;
                }else if(date.equals("week")){
                    doubles[i] = io.getAccCount();
                    strs[i] = "第"+String.valueOf(HighchartsData.getWeek(HighchartsData.dateFormat(io.getMuCreatedTime())))+"周";
                    HighchartsData.len = HighchartsData.strWeek.length;
                }
                i++;
            }
        }else if(species.equals("two")){
            int i = 0;
            HighchartsData.len = 0;
            for(MaterialReturn io: materialReturns) {
                if(date.equals("month")) {
                    doubles[i] = io.getAccCount();
                    strs[i] = HighchartsData.dateFormat(io.getMrCreatedDate(),"month");
                    HighchartsData.len = HighchartsData.strMonth.length;
                }else if(date.equals("day")){
                    doubles[i] = io.getAccCount();
                    strs[i] = HighchartsData.dateFormat(io.getMrCreatedDate(),"day");
                    HighchartsData.len = HighchartsData.strDay.length;
                }else if(date.equals("quarter")){
                    doubles[i] = io.getAccCount();
                    strs[i] = HighchartsData.dateFormat(io.getMrCreatedDate(),"quarter");
                    HighchartsData.len = HighchartsData.strQuarter.length;
                }else if(date.equals("year")){
                    doubles[i] = io.getAccCount();
                    strs[i] = HighchartsData.dateFormat(io.getMrCreatedDate(),"year");
                    HighchartsData.len = HighchartsData.strYear.length;
                }else if(date.equals("week")){
                    doubles[i] = io.getAccCount();
                    strs[i] = "第"+String.valueOf(HighchartsData.getWeek(HighchartsData.dateFormat(io.getMrCreatedDate())))+"周";
                    HighchartsData.len = HighchartsData.strWeek.length;
                }
                i++;
            }
        }

        if(date.equals("quarter")) {
            HighchartsData.getQuarter(strs,doubles,species);
        }else if(date.equals("month")){
            HighchartsData.getMonth(strs,doubles,species);
        }else if(date.equals("day")){
            HighchartsData.getDay(strs,doubles,species);
        }else if(date.equals("year")){
            HighchartsData. getYear(strs,doubles,species);
        }else if(date.equals("week")){
            HighchartsData.getWeek(strs,doubles,species);
        }
    }
}
