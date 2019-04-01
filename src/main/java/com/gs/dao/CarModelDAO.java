package com.gs.dao;

import com.gs.bean.CarModel;
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
public interface CarModelDAO extends BaseDAO<String, CarModel>{

    public List<CarModel> queryByBrandId(String brandId);
    public List<CarModel> queryByModelStatusPager(@Param("status")String status, @Param("pager")Pager pager);
    public int statusCount(String status);
    public List<CarModel> searchByPager(@Param("brandId")String brandId,@Param("pager")Pager pager);
    public int searchCount(@Param("brandId")String brandId);
}