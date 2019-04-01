package com.gs.dao;

import com.gs.bean.CarBrand;
import com.gs.bean.CarPlate;
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
public interface CarPlateDAO extends BaseDAO<String, CarPlate>{
    public List<CarPlate> byStatusPager(@Param("status") String status, @Param("pager") Pager pager);
    public int countStatus(String status);
    public List<CarPlate> searchByPager(@Param("plateName")String plateName,@Param("pager")Pager pager);
    public int searchCount(String plateName);
}