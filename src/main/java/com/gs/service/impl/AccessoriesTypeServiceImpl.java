package com.gs.service.impl;

import com.gs.bean.AccessoriesType;
import com.gs.bean.Module;
import com.gs.bean.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.AccessoriesTypeDAO;
import com.gs.service.AccessoriesTypeService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:53
*/
@Service
public class AccessoriesTypeServiceImpl implements AccessoriesTypeService {

	@Resource
	private AccessoriesTypeDAO accessoriesTypeDAO;

	public int insert(AccessoriesType accessoriesType) { return accessoriesTypeDAO.insert(accessoriesType); }
	public int batchInsert(List<AccessoriesType> list) { return accessoriesTypeDAO.batchInsert(list); }
	public int delete(AccessoriesType accessoriesType) { return accessoriesTypeDAO.delete(accessoriesType); }
	public int deleteById(String id) {
        return accessoriesTypeDAO.deleteById(id);
    }
	public int batchDelete(List<AccessoriesType> list) { return accessoriesTypeDAO.batchDelete(list); }
	public int update(AccessoriesType accessoriesType) { return accessoriesTypeDAO.update(accessoriesType); }
	public int batchUpdate(List<AccessoriesType> list) { return accessoriesTypeDAO.batchUpdate(list); }
	public List<AccessoriesType> queryAll(User user) { return accessoriesTypeDAO.queryAll(user); }
	public List<AccessoriesType> queryByStatus(String status, User user) { return accessoriesTypeDAO.queryByStatus(status, user); }
	public AccessoriesType query(AccessoriesType accessoriesType, User user) { return accessoriesTypeDAO.query(accessoriesType, user); }
	public AccessoriesType queryById(String id) { return accessoriesTypeDAO.queryById(id); }
	public List<AccessoriesType> queryByPager(Pager pager, User user) { return accessoriesTypeDAO.queryByPager(pager, user); }
	public int count(User user) { return accessoriesTypeDAO.count(user); }
	public int inactive(String id) { return accessoriesTypeDAO.inactive(id); }
	public int active(String id) { return accessoriesTypeDAO.active(id); }
	public List<AccessoriesType> queryByStatusPager(String accTypeStatus, Pager pager, User user) {
		return accessoriesTypeDAO.queryByStatusPager(accTypeStatus, pager, user);
	}
	public int countByStatus(String status, User user) {
		return accessoriesTypeDAO.countByStatus(status, user);
	}

	public int countByCondition(AccessoriesType accessoriesType, User user) { return accessoriesTypeDAO.countByCondition(accessoriesType, user); }

	public List<AccessoriesType> queryByCondition (Pager pager, AccessoriesType accessoriesType, User user) {
		return accessoriesTypeDAO.queryByCondition(pager, accessoriesType, user);
	}

	@Override
	public List<AccessoriesType> queryByCompany(String companyId) {
		return accessoriesTypeDAO.queryByCompany(companyId);
	}

}