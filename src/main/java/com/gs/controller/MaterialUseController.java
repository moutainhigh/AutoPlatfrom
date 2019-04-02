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

    /**
     * 可以查看的角色：董事长、财务员、超级管理员、普通管理员
     */
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

    /**
     * 显示领料信息
     * @return String
     */
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

    /**
     * 分页查询当前维修记录领料明细
     * @param pageNumber
     * @param pageSize
     * @param recordId
     * @return
     */
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
        return new Pager4EasyUI<>(pager.getTotalRecords(), materialUseInfos);
    }

    /**
     * 更新领料审核状态
     * @param id
     * @param status
     * @return
     */
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
            List<Accessories> accessories = new ArrayList<>();
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

    /**
     * 退料数量验证
     * @param accCount
     * @param materialUseId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryIs_CountThan", method = RequestMethod.GET)
    public String queryIsCountThan(@Param("accCount")String accCount, @Param("materialUseId")String materialUseId) {
        try {
            int accCount2 = Integer.valueOf(accCount);
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<>();
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

    /**
     * 申请领料
     * @param recordId
     * @param accIds
     * @param accCounts
     * @return
     */
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
            List<MaterialUseInfo> materialUseInfos = new ArrayList<>();
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

    /**
     * 默认查询本月配件使用统计
     * @param companyId
     * @param quantity
     * @return
     */
    @ResponseBody
    @RequestMapping(value="query_default",method= RequestMethod.GET)
    public List<LineBasic> queryAll(@Param("companyId")String companyId, @Param("quantity")String quantity){
        if(SessionGetUtil.isUser()) {

                logger.info("默认查询本月配件使用统计");
                List<LineBasic> lineBasics = new ArrayList<>();
                User user = SessionGetUtil.getUser();
                if(user.getCompanyId() != null && !"".equals(user.getCompanyId())){
                    companyId = user.getCompanyId();
                }
                List<AccessoriesType> accessoriesTypes = accessoriesTypeService.queryByCompany(companyId);
                for(AccessoriesType accType: accessoriesTypes){
                    LineBasic lineBasic = new LineBasic();
                    lineBasic.setName(accType.getAccTypeName());
                    if("领料数量".equals(quantity)) {
                        dateDay("one", companyId, accType.getAccTypeId());
                        lineBasic.setData(HighchartsData.doubleDayOne);
                    }else if("退料数量".equals(quantity)){
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

    /**
     * 根据年，月，季度，周，日查询配件使用统计
     * @param start
     * @param end
     * @param type
     * @param companyId
     * @param quantity
     * @return
     */
    @ResponseBody
    @RequestMapping(value="query_condition",method= RequestMethod.GET)
    public List<LineBasic> queryCondition(@Param("start")String start,@Param("end")String end,
                                          @Param("type")String type,@Param("companyId")String companyId,
                                          @Param("quantity")String quantity){
        if(SessionGetUtil.isUser()) {

                logger.info("根据年，月，季度，周，日查询配件使用统计");
                List<LineBasic> lineBasics = new ArrayList<>();
                User user = SessionGetUtil.getUser();
                if (user.getCompanyId() != null && !"".equals(user.getCompanyId())) {
                    companyId = user.getCompanyId();
                }
                List<AccessoriesType> accessoriesTypes = accessoriesTypeService.queryByCompany(companyId);
                if (start != null && !"".equals(start) && end != null && !"".equals(end) && type != null && !"".equals(type)) {
                    if ("year".equals(type)) {
                        HighchartsData.setStrYear(start, end);
                        for(AccessoriesType accType: accessoriesTypes){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(accType.getAccTypeName());
                            if("领料数量".equals(quantity)) {
                                dataCondition(start,end,type,"year","one",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleYearOne);
                            }else if("退料数量".equals(quantity)){
                                dataCondition(start,end,type,"year","two",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleYearTwo);
                            }
                            lineBasic.setCategories(HighchartsData.strYear);
                            lineBasics.add(lineBasic);
                        }
                    } else if ("quarter".equals(type)) {
                        for(AccessoriesType accType: accessoriesTypes){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(accType.getAccTypeName());
                            if("领料数量".equals(quantity)) {
                                dataCondition(start,end,type,"quarter","one",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleQuarterOne);
                            }else if("退料数量".equals(quantity)){
                                dataCondition(start,end,type,"quarter","two",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleQuarterTwo);
                            }
                            lineBasic.setCategories(HighchartsData.strQuarter);
                            lineBasics.add(lineBasic);
                        }
                    } else if ("month".equals(type)) {
                        for(AccessoriesType accType: accessoriesTypes){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(accType.getAccTypeName());
                            if("领料数量".equals(quantity)) {
                                dataCondition(start,end,type,"month","one",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleMonthOne);
                            }else if("退料数量".equals(quantity)){
                                dataCondition(start,end,type,"month","two",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleMonthTwo);
                            }
                            lineBasic.setCategories(HighchartsData.strMonth);
                            lineBasics.add(lineBasic);
                        }
                    } else if ("week".equals(type)) {
                        HighchartsData.setStrWeek(start, end);
                        for(AccessoriesType accType: accessoriesTypes){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(accType.getAccTypeName());
                            if("领料数量".equals(quantity)) {
                                dataCondition(start,end,type,"week","one",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleWeekOne);
                            }else if("退料数量".equals(quantity)){
                                dataCondition(start,end,type,"week","two",companyId,accType.getAccTypeId());;
                                lineBasic.setData(HighchartsData.doubleWeekTwo);
                            }
                            lineBasic.setCategories(HighchartsData.strWeek);
                            lineBasics.add(lineBasic);
                        }
                    } else if ("day".equals(type)) {
                        for(AccessoriesType accType: accessoriesTypes){
                            LineBasic lineBasic = new LineBasic();
                            lineBasic.setName(accType.getAccTypeName());
                            if("领料数量".equals(quantity)) {
                                dataCondition(start,end,type,"day","one",companyId,accType.getAccTypeId());
                                lineBasic.setData(HighchartsData.doubleDayOne);
                            }else if("退料数量".equals(quantity)){
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

    /**
     * 默认查询本月的配件使用统计
     * @param type
     * @param companyId
     * @param accTypeId
     */
    public void dateDay(String type,String companyId,String accTypeId){
        HighchartsData.doubleDayTwo = new double[31];
        HighchartsData.doubleDayOne = new double[31];
        List<MaterialUse> materialUses = null;
        List<MaterialReturn> materialReturns = null;
        double[] doubles = null;
        String[] strs = null;
        if("one".equals(type)){
            materialUses = materialUseService.queryByConditionUse(HighchartsData.stdayDate(),HighchartsData.lastDate(),"day",companyId,accTypeId);
            doubles =  new double[materialUses.size()];
            strs = new String[materialUses.size()];
        }else if("two".equals(type)){
            materialReturns = materialReturnService.queryByConditionReturn(HighchartsData.stdayDate(),HighchartsData.lastDate(),"day",companyId,accTypeId);
            doubles =  new double[materialReturns.size()];
            strs = new String[materialReturns.size()];
        }

        if("one".equals(type)){
            int i = 0;
            if (materialUses != null) {
                for(MaterialUse io: materialUses) {
                    doubles[i] = io.getAccCount();
                    strs[i] = HighchartsData.dateFormat(io.getMuCreatedTime(), "day");
                    i++;
                    }
            }
        }else if("two".equals(type)){
            int i = 0;
            if (materialReturns != null) {
                for(MaterialReturn io: materialReturns) {
                    doubles[i] = io.getAccCount();
                    strs[i] = HighchartsData.dateFormat(io.getMrCreatedDate(), "day");
                    i++;
                }
            }
        }
        for(int j = 0,len = HighchartsData.strDay.length; j <len ; j++){
            if (strs != null) {
                for(int k = 0; k < strs.length; k++){
                    if(HighchartsData.strDay[j].equals(strs[k])){
                        if("two".equals(type)){
                            HighchartsData.doubleDayTwo[j] = doubles[k];
                        }else if("one".equals(type)){
                            HighchartsData.doubleDayOne[j] = doubles[k];
                        }

                    }
                }
            }
        }
    }

    /**
     * 按年，季度，月，周，日，查询 配件使用统计
     * @param start
     * @param end
     * @param type
     * @param date
     * @param species
     * @param companyId
     * @param accTypeId
     */
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
        if("one".equals(species)){
            materialUses = materialUseService.queryByConditionUse(start,end,type,companyId,accTypeId);
            doubles =  new double[materialUses.size()];
            strs = new String[materialUses.size()];
        }else if("two".equals(species)){
            materialReturns = materialReturnService.queryByConditionReturn(start,end,type,companyId,accTypeId);
            doubles =  new double[materialReturns.size()];
            strs = new String[materialReturns.size()];
        }
        if("one".equals(species)){
            int i = 0;
            HighchartsData.len = 0;
            if (materialUses != null) {
                for(MaterialUse io: materialUses) {
                    if("month".equals(date)) {
                        doubles[i] = io.getAccCount();
                        strs[i] = HighchartsData.dateFormat(io.getMuCreatedTime(),"month");
                        HighchartsData.len = HighchartsData.strMonth.length;
                    }else if("day".equals(date)){
                        doubles[i] = io.getAccCount();
                        strs[i] = HighchartsData.dateFormat(io.getMuCreatedTime(),"day");
                        HighchartsData.len = HighchartsData.strDay.length;
                    }else if("quarter".equals(date)){
                        doubles[i] = io.getAccCount();
                        strs[i] = HighchartsData.dateFormat(io.getMuCreatedTime(),"quarter");
                        HighchartsData.len = HighchartsData.strQuarter.length;
                    }else if("year".equals(date)){
                        doubles[i] = io.getAccCount();
                        strs[i] = HighchartsData.dateFormat(io.getMuCreatedTime(),"year");
                        HighchartsData.len = HighchartsData.strYear.length;
                    }else if("week".equals(date)){
                        doubles[i] = io.getAccCount();
                        strs[i] = "第"+String.valueOf(HighchartsData.getWeek(HighchartsData.dateFormat(io.getMuCreatedTime())))+"周";
                        HighchartsData.len = HighchartsData.strWeek.length;
                    }
                    i++;
                }
            }
        }else if("two".equals(species)){
            int i = 0;
            HighchartsData.len = 0;
            if (materialReturns != null) {
                for(MaterialReturn io: materialReturns) {
                    if("month".equals(date)) {
                        doubles[i] = io.getAccCount();
                        strs[i] = HighchartsData.dateFormat(io.getMrCreatedDate(),"month");
                        HighchartsData.len = HighchartsData.strMonth.length;
                    }else if("day".equals(date)){
                        doubles[i] = io.getAccCount();
                        strs[i] = HighchartsData.dateFormat(io.getMrCreatedDate(),"day");
                        HighchartsData.len = HighchartsData.strDay.length;
                    }else if("quarter".equals(date)){
                        doubles[i] = io.getAccCount();
                        strs[i] = HighchartsData.dateFormat(io.getMrCreatedDate(),"quarter");
                        HighchartsData.len = HighchartsData.strQuarter.length;
                    }else if("year".equals(date)){
                        doubles[i] = io.getAccCount();
                        strs[i] = HighchartsData.dateFormat(io.getMrCreatedDate(),"year");
                        HighchartsData.len = HighchartsData.strYear.length;
                    }else if("week".equals(date)){
                        doubles[i] = io.getAccCount();
                        strs[i] = "第"+String.valueOf(HighchartsData.getWeek(HighchartsData.dateFormat(io.getMrCreatedDate())))+"周";
                        HighchartsData.len = HighchartsData.strWeek.length;
                    }
                    i++;
                }
            }
        }

        if("quarter".equals(date)) {
            HighchartsData.getQuarter(strs,doubles,species);
        }else if("month".equals(date)){
            HighchartsData.getMonth(strs,doubles,species);
        }else if("day".equals(date)){
            HighchartsData.getDay(strs,doubles,species);
        }else if("year".equals(date)){
            HighchartsData. getYear(strs,doubles,species);
        }else if("week".equals(date)){
            HighchartsData.getWeek(strs,doubles,species);
        }
    }
}
