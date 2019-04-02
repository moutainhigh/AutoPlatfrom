package com.gs.service;

import com.gs.bean.Module;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *
 * @author qm
 * @since 2017-04-14 16:36:52
 */
public interface ModuleService extends BaseService<String, Module> {

    /**根据状态来分页*/
     List<Module> queryByStatusPager(String moduleStatus, Pager pager);

    /**根据状态来统计*/
     int countByStatus(String moduleStatus);

    /**根据一段字符串查询模块表是否有这个模块名*/
     int queryModuleNameIsExist(String moduleName);
}