package com.gs.dao;

import com.gs.bean.MaterialUse;
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
public interface MaterialUseDAO extends BaseDAO<String, MaterialUse>{


    /*
    * 根据年，月，季度，周，日查询配件分类领料数量
    * */
     List<MaterialUse> queryByConditionUse(@Param("startTime")String startTime, @Param("endTime")String endTime,
                                                 @Param("type")String type, @Param("companyId")String companyId,
                                                 @Param("accTypeId")String accTypeId);
}