package com.gs.dao;

import com.gs.bean.AccessoriesSale;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:35:15
 */
@Repository
public interface AccessoriesSaleDAO extends BaseDAO<String, AccessoriesSale> {
     int queryByUserIdIsSameResult(@Param("id") String id, @Param("userName") String userName, @Param("user") User user);

     List<AccessoriesSale> queryByName(@Param("pager") Pager pager, @Param("name") String name, @Param("user") User user);

     int byNameCount(@Param("user") User user);

     List<AccessoriesSale> queryOnlySale(@Param("pager") Pager pager, @Param("user") User user);

     int onlySaleCount(@Param("user") User user);

    // 在选择购买时间范围内根据名称来查找
     List<AccessoriesSale> queryBySaleTimeScopeByAccNamePager(@Param("pager") Pager pager, @Param("accName") String accName, @Param("userName") String userName, @Param("saleTimeStart") String saleTimeStart, @Param("saleTimeEnd") String saleTimeEnd, @Param("user") User user);

     int bySaleTimeScopeCount(@Param("accName") String accName, @Param("userName") String userName, @Param("saleTimeStart") String saleTimeStart, @Param("saleTimeEnd") String saleTimeEnd, @Param("user") User user);

}
