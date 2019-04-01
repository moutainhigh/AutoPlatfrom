package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.CarColor;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.CarColorService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by root on 2017/4/16.
 */
@Controller
@RequestMapping("/carColor")
public class CarColorController {

    private String queryRole = Constants.COMPANY_ADMIN + ","
            + Constants.SYSTEM_SUPER_ADMIN + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + ","
            + Constants.COMPANY_HUMAN_MANAGER + ","
            + Constants.COMPANY_ACCOUNTING + ","
            + Constants.COMPANY_EMP + ","
            + Constants.COMPANY_SALES;

    private String editRole = Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;

    private Logger logger = (Logger) LoggerFactory.getLogger(CarColorController.class);

    @Resource
    private CarColorService carColorService;


    @ResponseBody
    @RequestMapping(value = "insertCarColor", method = RequestMethod.POST)
    public ControllerResult insertCarColor(CarColor carColor) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        } else {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("添加汽车颜色");
                    carColorService.insert(carColor);
                    return ControllerResult.getSuccessResult("添加汽车颜色成功");
                } else {
                    return ControllerResult.getFailResult("添加失败，你没有权限操作");
                }

            } catch (Exception e) {
                logger.info("添加失败，出现了一个错误");
                return ControllerResult.getFailResult("添加失败，出现了一个错误");
            }
        }

    }

    @ResponseBody
    @RequestMapping(value = "searchByPager", method = RequestMethod.GET)
    public Pager4EasyUI<CarColor> search(@Param("colorName") String colorName, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        Pager pager = new Pager();
        logger.info("模糊查询颜色");
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(carColorService.searchCount(colorName));
        List<CarColor> carColorList = carColorService.searchByPager(colorName, pager);
        return new Pager4EasyUI<CarColor>(pager.getTotalRecords(), carColorList);
    }

    @ResponseBody
    @RequestMapping(value = "uploadCarColor", method = RequestMethod.POST)
    public ControllerResult uploadCarColor(CarColor carColor) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        } else {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    carColorService.update(carColor);
                    System.out.println();
                    logger.info("更新汽车颜色");
                    return ControllerResult.getSuccessResult("更新汽车颜色成功");
                } else {
                    return ControllerResult.getFailResult("更新失败，你没有权限操作");
                }

            } catch (Exception e) {
                logger.info("更新失败，出现了一个错误");
                return ControllerResult.getFailResult("更新失败，出现了一个错误");
            }
        }

    }

    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<CarColor> queryByPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        Pager pager = new Pager();
        User user = SessionGetUtil.getUser();
        logger.info("分页查询所有颜色");
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(carColorService.count(user));
        List<CarColor> carColorList = carColorService.queryByPager(pager, user);
        return new Pager4EasyUI<CarColor>(pager.getTotalRecords(), carColorList);
    }

    @ResponseBody
    @RequestMapping(value = "car_color_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryCarColorAll() {
        logger.info("查询汽车颜色");
        User user = SessionGetUtil.getUser();
        List<CarColor> carColors = carColorService.queryAll(user);
        List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
        for (CarColor carColor : carColors) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(carColor.getColorId());
            comboBox4EasyUI.setText(carColor.getColorName());
            comboBox4EasyUIs.add(comboBox4EasyUI);
        }
        return comboBox4EasyUIs;
    }

    @ResponseBody
    @RequestMapping(value = "colorStatusModify", method = RequestMethod.GET)
    public ControllerResult colorStatusModify(@Param("id") String id, @Param("status") String status) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        } else {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    if (status.equals("Y")) {
                        logger.info("颜色冻结");
                        carColorService.inactive(id);
                    } else if (status.equals("N")) {
                        logger.info("颜色激活");
                        carColorService.active(id);
                    }
                    return ControllerResult.getSuccessResult("操作成功");
                } else {
                    return ControllerResult.getFailResult("操作失败，你没有权限操作");
                }

            } catch (Exception e) {
                logger.info("操作成功，出现了一个错误");
                return ControllerResult.getFailResult("操作成功，出现了一个错误");
            }
        }


    }

    @ResponseBody
    @RequestMapping(value = "queryByStatusPager", method = RequestMethod.GET)
    public Pager4EasyUI<CarColor> queryByStatusPager(@Param("status") String status, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(carColorService.statusCount(status));
        List<CarColor> carColorList = carColorService.queryByColorPager(status, pager);
        return new Pager4EasyUI<CarColor>(pager.getTotalRecords(), carColorList);
    }


}
