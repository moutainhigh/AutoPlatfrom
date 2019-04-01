package com.gs.service;

import com.gs.bean.CarBrand;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:51
*/
public interface CarBrandService extends BaseService<String, CarBrand>{
    public String queryNameById(String brandId);
    public List<CarBrand> queryByBrandPager(@Param("status")String status, @Param("pager")Pager pager);
    public List<CarBrand> searchByPager(@Param("brandName")String brandName, @Param("pager")Pager pager);
    public int statusCount(String status);
    public int searchCount(String brandName);
}