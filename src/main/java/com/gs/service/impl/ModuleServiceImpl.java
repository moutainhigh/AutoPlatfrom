package com.gs.service.impl;

import com.gs.bean.Module;
import com.gs.bean.User;
import com.gs.common.bean.Pager4EasyUI;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.ModuleDAO;
import com.gs.service.ModuleService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class ModuleServiceImpl implements ModuleService {

	@Resource
	private ModuleDAO moduleDAO;

	public int insert(Module module) { return moduleDAO.insert(module); }
	public int batchInsert(List<Module> list) { return moduleDAO.batchInsert(list); }
	public int delete(Module module) { return moduleDAO.delete(module); }
	public int deleteById(String id) {
        return moduleDAO.deleteById(id);
    }
	public int batchDelete(List<Module> list) { return moduleDAO.batchDelete(list); }
	public int update(Module module) { return moduleDAO.update(module); }
	public int batchUpdate(List<Module> list) { return moduleDAO.batchUpdate(list); }
	public List<Module> queryAll(User user) { return moduleDAO.queryAll(user); }
	public List<Module> queryByStatus(String status, User user) { return moduleDAO.queryByStatus(status, user); }
	public Module query(Module module, User user) { return moduleDAO.query(module, user); }
	public Module queryById(String id) { return moduleDAO.queryById(id); }
	public List<Module> queryByPager(Pager pager, User user) { return moduleDAO.queryByPager(pager, user); }
	public int count(User user) { return moduleDAO.count(user); }
	public int inactive(String id) { return moduleDAO.inactive(id); }
	public int active(String id) { return moduleDAO.active(id); }
	public List<Module> queryByStatusPager(String moduleStatus, Pager pager) {
		return moduleDAO.queryByStatusPager(moduleStatus, pager);
	}
	public int countByStatus(String moduleStatus) {
		return moduleDAO.countByStatus(moduleStatus);
	}

	public int queryModuleNameIsExist(String moduleName) {
		return moduleDAO.queryModuleNameIsExist(moduleName);
	}
}