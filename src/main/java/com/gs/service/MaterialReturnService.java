package com.gs.service;

import com.gs.bean.MaterialReturn;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:36:52
*/
public interface MaterialReturnService extends BaseService<String, MaterialReturn>{

    /*
* 根据年，月，季度，周，日查询配件分类退料数量
* */
     List<MaterialReturn> queryByConditionReturn(String startTime,String endTime,
                                                 String type, String companyId,
                                                 String accTypeId);
}