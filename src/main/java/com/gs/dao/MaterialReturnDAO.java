package com.gs.dao;

import com.gs.bean.MaterialReturn;
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
public interface MaterialReturnDAO extends BaseDAO<String, MaterialReturn>{

    /*
* 根据年，月，季度，周，日查询配件分类退料数量
* */
    public List<MaterialReturn> queryByConditionReturn(@Param("startTime")String startTime, @Param("endTime")String endTime,
                                                 @Param("type")String type, @Param("companyId")String companyId,
                                                 @Param("accTypeId")String accTypeId);
}