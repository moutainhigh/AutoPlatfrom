package com.gs.service.impl;

import com.gs.bean.MaintainFix;
import com.gs.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.MaintainFixDAO;
import com.gs.service.MaintainFixService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class MaintainFixServiceImpl implements MaintainFixService {

	@Resource
	private MaintainFixDAO maintainFixDAO;

	public int insert(MaintainFix maintainFix) { return maintainFixDAO.insert(maintainFix); }
	public int batchInsert(List<MaintainFix> list) { return maintainFixDAO.batchInsert(list); }
	public int delete(MaintainFix maintainFix) { return maintainFixDAO.delete(maintainFix); }
	public int deleteById(String id) {
        return maintainFixDAO.deleteById(id);
    }
	public int batchDelete(List<MaintainFix> list) { return maintainFixDAO.batchDelete(list); }
	public int update(MaintainFix maintainFix) { return maintainFixDAO.update(maintainFix); }
	public int batchUpdate(List<MaintainFix> list) { return maintainFixDAO.batchUpdate(list); }
	public List<MaintainFix> queryAll(User user) {
		return maintainFixDAO.queryAll(user);
	}
	public List<MaintainFix> queryByStatus(String status, User user) {
		return maintainFixDAO.queryByStatus(status, user);
	}
	public MaintainFix query(MaintainFix maintainFix, User user) {
		return maintainFixDAO.query(maintainFix, user);
	}
	public MaintainFix queryById(String id) { return maintainFixDAO.queryById(id); }
	public List<MaintainFix> queryByPager(Pager pager, User user) {
		return maintainFixDAO.queryByPager(pager,user);
	}
	public int count(User user) {
		return maintainFixDAO.count(user);
	}
	public int inactive(String id) { return maintainFixDAO.inactive(id); }
	public int active(String id) { return maintainFixDAO.active(id); }
	public List<MaintainFix> queryBymaintainPager(Pager pager, User user) {
		return maintainFixDAO.queryBymaintainPager(pager, user);
	}
	public int MaintainCont(User user) {
		return maintainFixDAO.MaintainCont(user);
	}

	public List<MaintainFix> byStatusPager(String status, Pager pager,User user) {
		return maintainFixDAO.byStatusPager(status, pager, user);
	}


	public int countStatus(String status,User user) {
		return maintainFixDAO.countStatus(status, user);
	}

	public List<MaintainFix> searchByPager(String name,Pager pager,User user) {
		return maintainFixDAO.searchByPager(name, pager, user);
	}

	public int searCount(String name,User user) {
		return maintainFixDAO.searCount(name, user);
	}


	public List<MaintainFix> repairByStatusPager(String status,Pager pager,User user) {
		return maintainFixDAO.repairByStatusPager(status, pager, user);
	}


	public int repairCountStatus(String status,User user) {
		return maintainFixDAO.repairCountStatus(status, user);
	}


	public List<MaintainFix> searchByRepairPager(String name,Pager pager,User user) {
		return maintainFixDAO.searchByRepairPager(name, pager, user);
	}


	public int searRepairCount(String name,User user) {
		return maintainFixDAO.searRepairCount(name, user);
	}

	public List<MaintainFix> queryByType(String companyId, String maintainOrFix) {
		return maintainFixDAO.queryByType(companyId,maintainOrFix);
	}
}