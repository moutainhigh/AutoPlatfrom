package com.gs.dao;

import com.gs.bean.CarBrand;
import com.gs.bean.CarColor;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:35:15
*/
@Repository
public interface CarColorDAO extends BaseDAO<String, CarColor>{
    public List<CarColor> queryByColorPager(@Param("status")String status, @Param("pager")Pager pager);
    public int statusCount(String status);
    public List<CarColor> searchByPager(@Param("colorName")String colorName, @Param("pager")Pager pager);
    public int searchCount(String colorName);
}