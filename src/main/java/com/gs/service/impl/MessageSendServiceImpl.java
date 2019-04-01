package com.gs.service.impl;

import com.gs.bean.MessageSend;
import com.gs.bean.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.gs.dao.MessageSendDAO;
import com.gs.service.MessageSendService;
import com.gs.common.bean.Pager;
/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:58:54
*/
@Service
public class MessageSendServiceImpl implements MessageSendService {


	@Resource
	private MessageSendDAO messageSendDAO;

	public int insert(MessageSend messageSend) { return messageSendDAO.insert(messageSend); }
	public int batchInsert(List<MessageSend> list) { return messageSendDAO.batchInsert(list); }
	public int delete(MessageSend messageSend) { return messageSendDAO.delete(messageSend); }
	public int deleteById(String id) {
        return messageSendDAO.deleteById(id);
    }
	public int batchDelete(List<MessageSend> list) { return messageSendDAO.batchDelete(list); }
	public int update(MessageSend messageSend) { return messageSendDAO.update(messageSend); }
	public int batchUpdate(List<MessageSend> list) { return messageSendDAO.batchUpdate(list); }
	public List<MessageSend> queryAll(User user) { return messageSendDAO.queryAll(user); }
	public List<MessageSend> queryByStatus(String status,User user) { return messageSendDAO.queryByStatus(status,user); }
	public MessageSend query(MessageSend messageSend,User user) { return messageSendDAO.query(messageSend,user); }
	public MessageSend queryById(String id) { return messageSendDAO.queryById(id); }
	public List<MessageSend> queryByPager(Pager pager,User user) { return messageSendDAO.queryByPager(pager,user); }
	public int count(User user) { return messageSendDAO.count(user); }
	public int inactive(String id) { return messageSendDAO.inactive(id); }
	public int active(String id) { return messageSendDAO.active(id); }

	public void batchUpdateBySendMsg(String[] idList, String sendMsg) {
		messageSendDAO.batchUpdateBySendMsg(idList, sendMsg);
	}

	public void addMessageId(List<MessageSend> msd) {
		messageSendDAO.addMessageId(msd);
	}

	@Override
	public void batchUpdateBySuccess(String[] idList) {
		messageSendDAO.batchUpdateBySuccess(idList);
	}
}