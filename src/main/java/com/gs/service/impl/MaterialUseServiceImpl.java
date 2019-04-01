package com.gs.service.impl;

import com.gs.bean.MaterialUse;
import com.gs.bean.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.MaterialUseDAO;
import com.gs.service.MaterialUseService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class MaterialUseServiceImpl implements MaterialUseService {

	@Resource
	private MaterialUseDAO materialUseDAO;

	public int insert(MaterialUse materialUse) { return materialUseDAO.insert(materialUse); }
	public int batchInsert(List<MaterialUse> list) { return materialUseDAO.batchInsert(list); }
	public int delete(MaterialUse materialUse) { return materialUseDAO.delete(materialUse); }
	public int deleteById(String id) {
        return materialUseDAO.deleteById(id);
    }
	public int batchDelete(List<MaterialUse> list) { return materialUseDAO.batchDelete(list); }
	public int update(MaterialUse materialUse) { return materialUseDAO.update(materialUse); }
	public int batchUpdate(List<MaterialUse> list) { return materialUseDAO.batchUpdate(list); }
	public List<MaterialUse> queryAll(User user) { return materialUseDAO.queryAll(user); }
	public List<MaterialUse> queryByStatus(String status, User user) { return materialUseDAO.queryByStatus(status, user); }
	public MaterialUse query(MaterialUse materialUse, User user) { return materialUseDAO.query(materialUse, user); }
	public MaterialUse queryById(String id) { return materialUseDAO.queryById(id); }
	public List<MaterialUse> queryByPager(Pager pager, User user) { return materialUseDAO.queryByPager(pager, user); }
	public int count(User user) { return materialUseDAO.count(user); }
	public int inactive(String id) { return materialUseDAO.inactive(id); }
	public int active(String id) { return materialUseDAO.active(id); }

	@Override
	public List<MaterialUse> queryByConditionUse(String startTime, String endTime, String type, String companyId, String accTypeId) {
		return materialUseDAO.queryByConditionUse(startTime,endTime,type,companyId,accTypeId);
	}
}