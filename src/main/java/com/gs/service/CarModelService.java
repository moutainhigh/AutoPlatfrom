package com.gs.service;

import com.gs.bean.CarModel;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:36:51
*/
public interface CarModelService extends BaseService<String, CarModel>{

     List<CarModel> queryByBrandId(String brandId);
     List<CarModel> queryByModelStatusPager(@Param("status")String status, @Param("pager")Pager pager);
     int statusCount(String status);
     List<CarModel> searchByPager(@Param("brandId")String brandId,@Param("pager")Pager pager);
     int searchCount(@Param("brandId")String brandId);
}