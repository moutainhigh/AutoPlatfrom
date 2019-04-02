package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.IncomingOutgoing;
import com.gs.bean.OutgoingType;
import com.gs.bean.Salary;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.*;
import com.gs.service.IncomingOutgoingService;
import com.gs.service.OutgoingTypeService;
import com.gs.service.SalaryService;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 工资管理
 * Created by Star on 2017/4/17.
 */

@Controller
@RequestMapping("/salary")
public class SalaryController {

    private Logger logger = (Logger) LoggerFactory.getLogger(SalaryController.class);

    @Resource
    private SalaryService salaryService;

    @Resource
    private UserService userService;

    @Resource
    private OutgoingTypeService outgoingTypeService;

    @Resource
    private IncomingOutgoingService incomingOutgoingService;


    /**
     * 可以查看的角色：董事长、财务员、超级管理员、普通管理员
     */
    private String queryRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_ACCOUNTING + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;

    /**
     * 可以修改的角色：董事长、财务员
     */
    private String editRole = Constants.COMPANY_ADMIN + "," + Constants.COMPANY_ACCOUNTING;

    /**
     * 工资管理页面
     * @return
     */
    @RequestMapping(value = "show_salary", method = RequestMethod.GET)
    public String salary() {
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRole)) {
                logger.info("工资管理页面");
                return "financeManage/salary";
            }
            return "error/notPermission";
        }else {
            logger.info("Session已失效，请重新登入");
            return "index/notLogin";
        }
    }

    /**
     * 分页查询所有工资
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value="query_pager",method= RequestMethod.GET)
    public Pager4EasyUI<Salary> queryPager(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        if (SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRole)) {
                logger.info("分页查询所有工资");
                Pager pager = new Pager();
                User user = SessionGetUtil.getUser();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setTotalRecords(salaryService.count(user));
                List<Salary> salaries = salaryService.queryByPager(pager,user);
                return new Pager4EasyUI<Salary>(pager.getTotalRecords(), salaries);
            }
            return null;
        } else {
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    /**
     * 添加工资
     * @param salary
     * @return
     */
    @ResponseBody
    @RequestMapping(value="add_salary", method=RequestMethod.POST)
    public ControllerResult incomingTypeAdd(Salary salary){
        if (SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(editRole)) {
                logger.info("添加工资");
                User sessionUser = SessionGetUtil.getUser();
                User user = userService.queryById(salary.getUserId());
                OutgoingType outgoingType = outgoingTypeService.queryByName(Constants.SALARY_OUT);
                salary.setTotalSalary(user.getUserSalary() + salary.getPrizeSalary() - salary.getMinusSalary());
                salaryService.insert(salary);

                IncomingOutgoing incomingOutgoing = new IncomingOutgoing();
                incomingOutgoing.setOutTypeId(outgoingType.getOutTypeId());
                incomingOutgoing.setInOutCreatedUser(sessionUser.getUserId());
                incomingOutgoing.setInOutMoney(salary.getTotalSalary());
                incomingOutgoing.setCompanyId(sessionUser.getCompanyId());
                incomingOutgoingService.insert(incomingOutgoing);
                return ControllerResult.getSuccessResult("添加工资成功");
            }
            return ControllerResult.getFailResult("添加工资失败，没有该权限");
        } else {
                logger.info("Session已失效，请重新登入");
                return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    /**
     * 更新工资
     * @param salary
     * @return
     */
    @ResponseBody
    @RequestMapping(value="update_salary", method=RequestMethod.POST)
    public ControllerResult incomingUpdate(Salary salary){
        if (SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(editRole)) {
                logger.info("更新工资");
                User user = userService.queryById(salary.getUserId());
                salary.setTotalSalary(user.getUserSalary() + salary.getPrizeSalary() - salary.getMinusSalary());
                salaryService.update(salary);
                return ControllerResult.getSuccessResult("更新工资成功");
            }
            return ControllerResult.getFailResult("更新工资失败，没有该权限");
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    /**
     * 根据条件分页查询所有工资
     * @param pageNumber
     * @param pageSize
     * @param userName
     * @param salaryRange
     * @return
     */
    @ResponseBody
    @RequestMapping(value="query_search",method= RequestMethod.GET)
    public Pager4EasyUI<Salary> queryPagerSearch(@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize,
                                                 @Param("userName")String userName,@Param("salaryRange")String salaryRange){
        if (SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRole)) {
                logger.info("根据条件分页查询所有工资");
                Pager pager = new Pager();
                User sessionUser = SessionGetUtil.getUser();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                Salary salary = new Salary();
                User user = new User();
                if (userName != null && !userName.equals("")) {
                    user.setUserName(userName);
                } else {
                    user.setUserName("null");
                }
                salary.setUser(user);
                salary.setSalaryRange(salaryRange);
                pager.setTotalRecords(salaryService.countSearch(salary,sessionUser));
                List<Salary> salarys = salaryService.queryByPagerSearch(pager, salary,sessionUser);
                return new Pager4EasyUI<Salary>(pager.getTotalRecords(), salarys);
            }
            return null;
        } else{
            logger.info("Session已失效，请重新登入");
            return null;
        }
    }

    /**
     * 导出工资
     * @param response
     */
    @RequestMapping(value = "export",method=RequestMethod.GET)
    public void exportExcel(HttpServletResponse response) {
        if(SessionGetUtil.isUser()) {
            if(CheckRoleUtil.checkRoles(queryRole)) {
                try {
                    logger.info("导出工资");
                    // 查询工资信息
                    User user = SessionGetUtil.getUser();
                    List<Salary> salarylist = salaryService.queryAll(user);
                    String title = "员工工资信息";
                    String[] rowsName = new String[]{"工资编号", "员工名称", "奖金", "罚款", "总工资", "工资描述", "发放时间"};
                    List<Object[]> dataList = new ArrayList<>();
                    Object[] objs = null;
                    for (Salary salary : salarylist) {
                        objs = new Object[rowsName.length];
                        objs[0] = salary.getSalaryId();
                        objs[1] = salary.getUser().getUserName();
                        objs[2] = salary.getPrizeSalary();
                        objs[3] = salary.getMinusSalary();
                        objs[4] = salary.getTotalSalary();
                        objs[5] = salary.getSalaryDes();
                        objs[6] = DateFormatUtil.defaultFormat(salary.getSalaryTime());
                        dataList.add(objs);
                    }
                    ExcelExport ex = new ExcelExport(title, rowsName, dataList, response);
                    if(salarylist.size() > 0) {
                        ex.exportData();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else{
            logger.info("Session已失效，请重新登入");
        }
    }

    /**
     * 导入工资
     * @param fileSalary
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value="readExcel",method=RequestMethod.POST)
    public ControllerResult readExcel(MultipartFile fileSalary) throws IOException{
        if(SessionGetUtil.isUser()) {
            //判断文件是否为空
            if(CheckRoleUtil.checkRoles(editRole)) {
                logger.info("导入工资");
                String name = fileSalary.getOriginalFilename();
                long size = fileSalary.getSize();
                if (name == null || ExcelUtil.EMPTY.equals(name) && size == 0) {
                    return ControllerResult.getFailResult("导入工资失败");
                }
                //读取Excel数据到List中
                List<ArrayList<String>> list = null;
                try {
                    list = new ExcelRead().readExcel(fileSalary);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Salary salary = null;
                User sessionUser = SessionGetUtil.getUser();
                //list中存的就是excel中的数据，可以根据excel中每一列的值转换成你所需要的值（从0开始），如：
                List<Salary> salaries = new ArrayList<>();
                List<IncomingOutgoing> incomingOutgoings = new ArrayList<>();
                OutgoingType outgoingType = outgoingTypeService.queryByName(Constants.SALARY_OUT);

                for (ArrayList<String> arr : list) {
                    salary = new Salary();
                    User user = userService.queryByPhone(arr.get(1));
                    try {
                        salary.setUserId(user.getUserId());
                        salary.setPrizeSalary(Double.valueOf(arr.get(2)));
                        salary.setMinusSalary(Double.valueOf(arr.get(3)));
                        salary.setTotalSalary(Double.valueOf(arr.get(4)));
                        salary.setSalaryDes(arr.get(5));
                        salary.setSalaryTime(dateFormat(arr.get(6)));
                    } catch (NullPointerException e) {
                        return ControllerResult.getFailResult("导入工资失败");
                    }
                    IncomingOutgoing incomingOutgoing = new IncomingOutgoing();
                    incomingOutgoing.setOutTypeId(outgoingType.getOutTypeId());
                    incomingOutgoing.setInOutCreatedUser(sessionUser.getUserId());
                    incomingOutgoing.setInOutMoney(salary.getTotalSalary());
                    incomingOutgoing.setCompanyId(sessionUser.getCompanyId());
                    incomingOutgoings.add(incomingOutgoing);
                    salaries.add(salary);
                }
                if (salaryService.saveBatchInsert(salaries)) {
                    incomingOutgoingService.addInsert(incomingOutgoings);
                    return ControllerResult.getSuccessResult("导入工资成功");
                } else {
                    return ControllerResult.getFailResult("导入工资失败,没有该权限");
                }
            }
            return ControllerResult.getFailResult("导入失败");
        } else{
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }
    public Date dateFormat(String str){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
