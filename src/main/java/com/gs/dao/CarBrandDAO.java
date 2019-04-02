package com.gs.dao;

import com.gs.bean.CarBrand;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:35:15
*/
@Repository
public interface CarBrandDAO extends BaseDAO<String, CarBrand>{
     String queryNameById(String brandId);
     List<CarBrand> queryByBrandPager(@Param("status")String status, @Param("pager")Pager pager);
     List<CarBrand> searchByPager(@Param("brandName")String brandName, @Param("pager")Pager pager);
     int statusCount(String status);
     int searchCount(String brandName);
}