package com.gs.service;

import com.gs.bean.CarBrand;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:36:51
*/
public interface CarBrandService extends BaseService<String, CarBrand>{
     String queryNameById(String brandId);
     List<CarBrand> queryByBrandPager(@Param("status")String status, @Param("pager")Pager pager);
     List<CarBrand> searchByPager(@Param("brandName")String brandName, @Param("pager")Pager pager);
     int statusCount(String status);
     int searchCount(String brandName);
}