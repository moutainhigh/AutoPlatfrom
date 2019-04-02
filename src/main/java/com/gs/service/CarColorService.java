package com.gs.service;

import com.gs.bean.CarBrand;
import com.gs.bean.CarColor;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:36:51
*/
public interface CarColorService extends BaseService<String, CarColor>{
     List<CarColor> queryByColorPager(String status,Pager pager);
     int statusCount(String status);
     List<CarColor> searchByPager(@Param("colorName")String colorName, @Param("pager")Pager pager);
     int searchCount(String colorName);
}