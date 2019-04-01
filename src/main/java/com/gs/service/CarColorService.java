package com.gs.service;

import com.gs.bean.CarBrand;
import com.gs.bean.CarColor;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:51
*/
public interface CarColorService extends BaseService<String, CarColor>{
    public List<CarColor> queryByColorPager(String status,Pager pager);
    public int statusCount(String status);
    public List<CarColor> searchByPager(@Param("colorName")String colorName, @Param("pager")Pager pager);
    public int searchCount(String colorName);
}