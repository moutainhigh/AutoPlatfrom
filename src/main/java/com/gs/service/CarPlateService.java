package com.gs.service;

import com.gs.bean.CarPlate;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:36:51
*/
public interface CarPlateService extends BaseService<String, CarPlate>{
     List<CarPlate> byStatusPager(String status,Pager pager);
     int countStatus(String status);
     List<CarPlate> searchByPager(@Param("plateName")String plateName,@Param("pager")Pager pager);
     int searchCount(String plateName);
}