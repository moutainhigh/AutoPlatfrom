package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.gs.bean.CarBrand;
import com.gs.bean.User;
import com.gs.common.Constants;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.CheckRoleUtil;
import com.gs.common.util.SessionGetUtil;
import com.gs.service.CarBrandService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by root on 2017/4/16.
 */
@Controller
@RequestMapping("/carBrand")
public class CarBrandController {

    private String queryRole = Constants.COMPANY_ADMIN + ","
            + Constants.SYSTEM_SUPER_ADMIN + ","
            + Constants.SYSTEM_ORDINARY_ADMIN + ","
            + Constants.COMPANY_HUMAN_MANAGER + ","
            + Constants.COMPANY_ACCOUNTING + ","
            + Constants.COMPANY_EMP + ","
            + Constants.COMPANY_SALES;

    private String editRole = Constants.SYSTEM_ORDINARY_ADMIN + "," + Constants.SYSTEM_SUPER_ADMIN;

    @Resource
    private CarBrandService carBrandService;

    private Logger logger = (Logger) LoggerFactory.getLogger(CarBrandController.class);

    /***
     *
     * @param carBrand
     * @return 添加汽车品牌
     */
    @ResponseBody
    @RequestMapping(value = "insertCarBrand", method = RequestMethod.POST)
    public ControllerResult insertCarBrand(CarBrand carBrand) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        } else {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    logger.info("添加汽车品牌");
                    carBrandService.insert(carBrand);
                    return ControllerResult.getSuccessResult("添加汽车品牌成功");
                } else {
                    return ControllerResult.getFailResult("添加失败，你没有权限操作");
                }

            } catch (Exception e) {
                logger.info("添加失败，出现了一个错误");
                return ControllerResult.getFailResult("添加失败，出现了一个错误");
            }
        }

    }

    /**
     * @param brandName
     * @param pageNumber
     * @param pageSize
     * @return 条件查询
     */
    @ResponseBody
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public Pager4EasyUI<CarBrand> searchByPager(@Param("brandName") String brandName, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(carBrandService.searchCount(brandName));
        List<CarBrand> carBrandList = carBrandService.searchByPager(brandName, pager);
        return new Pager4EasyUI<CarBrand>(pager.getTotalRecords(), carBrandList);
    }

    /**
     * @param carBrand
     * @return 更新汽车品牌
     */
    @ResponseBody
    @RequestMapping(value = "uploadCarBrand", method = RequestMethod.POST)
    public ControllerResult uploadCarBrand(CarBrand carBrand) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        } else {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    carBrandService.update(carBrand);
                    logger.info("更新汽车品牌");
                    return ControllerResult.getSuccessResult("更新汽车品牌成功");
                } else {
                    return ControllerResult.getFailResult("更新失败,你没有权限操作");
                }

            } catch (Exception e) {
                logger.info("更新失败，出现了一个错误");
                return ControllerResult.getFailResult("更新失败，出现了一个错误");
            }
        }

    }

    /**
     * @param pageNumber
     * @param pageSize
     * @return 分页查询
     */
    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<CarBrand> queryByPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        Pager pager = new Pager();
        logger.info("分页查询所有汽车品牌");
        User user = SessionGetUtil.getUser();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(carBrandService.count(user));
        List<CarBrand> carBrandList = carBrandService.queryByPager(pager, user);
        return new Pager4EasyUI<CarBrand>(pager.getTotalRecords(), carBrandList);
    }

    /**
     * @return 用于显示下拉菜单
     */
    @ResponseBody
    @RequestMapping(value = "car_brand_all", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryCarBrandAll() {
        logger.info("查询汽车品牌");
        User user = SessionGetUtil.getUser();
        List<CarBrand> carBrands = carBrandService.queryAll(user);
        List<ComboBox4EasyUI> comboBox4EasyUIs = new ArrayList<ComboBox4EasyUI>();
        for (CarBrand carBrand : carBrands) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(carBrand.getBrandId());
            comboBox4EasyUI.setText(carBrand.getBrandName());
            comboBox4EasyUIs.add(comboBox4EasyUI);
        }
        return comboBox4EasyUIs;
    }

    /**
     * @param brandId
     * @return 根据汽车id查询汽车品牌
     */
    @ResponseBody
    @RequestMapping(value = "name", method = RequestMethod.GET)
    public String queryName(String brandId) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        logger.info("根据id查询品牌名称");
        return carBrandService.queryNameById(brandId);
    }

    /**
     * @param id
     * @param status
     * @return 冻结激活
     */
    @ResponseBody
    @RequestMapping(value = "brandStatusModify", method = RequestMethod.GET)
    public ControllerResult brandStatusModify(@Param("id") String id, @Param("status") String status) {
        if (!SessionGetUtil.isUser()) {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        } else {
            try {
                if (CheckRoleUtil.checkRoles(editRole)) {
                    if (status.equals("Y")) {
                        logger.info("品牌冻结");
                        carBrandService.inactive(id);
                    } else if (status.equals("N")) {
                        logger.info("品牌激活");
                        carBrandService.active(id);
                    }
                    return ControllerResult.getSuccessResult("操作成功");
                } else {
                    return ControllerResult.getFailResult("操作失败，你没有权限操作");
                }

            } catch (Exception e) {
                logger.info("操作失败，出现了一个错误");
                return ControllerResult.getFailResult("操作失败，出现了一个错误");
            }
        }

    }

    /**
     * @param status
     * @param pageNumber
     * @param pageSize
     * @return 根据状态查询出汽车品牌
     */
    @ResponseBody
    @RequestMapping(value = "queryByStatusPager", method = RequestMethod.GET)
    public Pager4EasyUI<CarBrand> queryByStatusPager(@Param("status") String status, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (!SessionGetUtil.isUser() || !CheckRoleUtil.checkRoles(queryRole)) {
            logger.info("Session已失效或者权限不足");
            return null;
        }
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        pager.setTotalRecords(carBrandService.statusCount(status));
        List<CarBrand> carBrandList = carBrandService.queryByBrandPager(status, pager);
        return new Pager4EasyUI<CarBrand>(pager.getTotalRecords(), carBrandList);
    }

}
