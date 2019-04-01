package com.gs.service.impl;

import com.gs.bean.MaterialReturn;
import com.gs.bean.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.MaterialReturnDAO;
import com.gs.service.MaterialReturnService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class MaterialReturnServiceImpl implements MaterialReturnService {

	@Resource
	private MaterialReturnDAO materialReturnDAO;

	public int insert(MaterialReturn materialReturn) { return materialReturnDAO.insert(materialReturn); }
	public int batchInsert(List<MaterialReturn> list) { return materialReturnDAO.batchInsert(list); }
	public int delete(MaterialReturn materialReturn) { return materialReturnDAO.delete(materialReturn); }
	public int deleteById(String id) {
        return materialReturnDAO.deleteById(id);
    }
	public int batchDelete(List<MaterialReturn> list) { return materialReturnDAO.batchDelete(list); }
	public int update(MaterialReturn materialReturn) { return materialReturnDAO.update(materialReturn); }
	public int batchUpdate(List<MaterialReturn> list) { return materialReturnDAO.batchUpdate(list); }
	public List<MaterialReturn> queryAll(User user) { return materialReturnDAO.queryAll(user); }
	public List<MaterialReturn> queryByStatus(String status, User user) { return materialReturnDAO.queryByStatus(status, user); }
	public MaterialReturn query(MaterialReturn materialReturn, User user) { return materialReturnDAO.query(materialReturn, user); }
	public MaterialReturn queryById(String id) { return materialReturnDAO.queryById(id); }
	public List<MaterialReturn> queryByPager(Pager pager, User user) { return materialReturnDAO.queryByPager(pager, user); }
	public int count(User user) { return materialReturnDAO.count(user); }
	public int inactive(String id) { return materialReturnDAO.inactive(id); }
	public int active(String id) { return materialReturnDAO.active(id); }

	@Override
	public List<MaterialReturn> queryByConditionReturn(String startTime, String endTime, String type, String companyId, String accTypeId) {
		return materialReturnDAO.queryByConditionReturn(startTime,endTime,type,companyId,accTypeId);
	}
}