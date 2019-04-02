package com.gs.dao;

import com.gs.bean.CarModel;
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
public interface CarModelDAO extends BaseDAO<String, CarModel>{

     List<CarModel> queryByBrandId(String brandId);
     List<CarModel> queryByModelStatusPager(@Param("status")String status, @Param("pager")Pager pager);
     int statusCount(String status);
     List<CarModel> searchByPager(@Param("brandId")String brandId,@Param("pager")Pager pager);
     int searchCount(@Param("brandId")String brandId);
}