package com.gs.service.impl;

import com.gs.bean.MaterialList;
import com.gs.bean.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.MaterialListDAO;
import com.gs.service.MaterialListService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class MaterialListServiceImpl implements MaterialListService {

	@Resource
	private MaterialListDAO materialListDAO;

	public int insert(MaterialList materialList) { return materialListDAO.insert(materialList); }
	public int batchInsert(List<MaterialList> list) { return materialListDAO.batchInsert(list); }
	public int delete(MaterialList materialList) { return materialListDAO.delete(materialList); }
	public int deleteById(String id) {
        return materialListDAO.deleteById(id);
    }
	public int batchDelete(List<MaterialList> list) { return materialListDAO.batchDelete(list); }
	public int update(MaterialList materialList) { return materialListDAO.update(materialList); }
	public int batchUpdate(List<MaterialList> list) { return materialListDAO.batchUpdate(list); }
	public List<MaterialList> queryAll(User user) { return materialListDAO.queryAll(user); }
	public List<MaterialList> queryByStatus(String status, User user) { return materialListDAO.queryByStatus(status, user); }
	public MaterialList query(MaterialList materialList, User user) { return materialListDAO.query(materialList, user); }
	public MaterialList queryById(String id) { return materialListDAO.queryById(id); }
	public List<MaterialList> queryByPager(Pager pager, User user) { return materialListDAO.queryByPager(pager, user); }
	public int count(User user) { return materialListDAO.count(user); }
	public int inactive(String id) { return materialListDAO.inactive(id); }
	public int active(String id) { return materialListDAO.active(id); }

}