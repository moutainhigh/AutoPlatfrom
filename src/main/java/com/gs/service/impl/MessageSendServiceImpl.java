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
*
*
*@author qm
*@since 2017-04-14 16:58:54
*/
@Service
public class MessageSendServiceImpl implements MessageSendService {


	@Resource
	private MessageSendDAO messageSendDAO;

	@Override
	public int insert(MessageSend messageSend) { return messageSendDAO.insert(messageSend); }
	@Override
	public int batchInsert(List<MessageSend> list) { return messageSendDAO.batchInsert(list); }
	@Override
	public int delete(MessageSend messageSend) { return messageSendDAO.delete(messageSend); }
	@Override
	public int deleteById(String id) {
        return messageSendDAO.deleteById(id);
    }
	@Override
	public int batchDelete(List<MessageSend> list) { return messageSendDAO.batchDelete(list); }
	@Override
	public int update(MessageSend messageSend) { return messageSendDAO.update(messageSend); }
	@Override
	public int batchUpdate(List<MessageSend> list) { return messageSendDAO.batchUpdate(list); }
	@Override
	public List<MessageSend> queryAll(User user) { return messageSendDAO.queryAll(user); }
	@Override
	public List<MessageSend> queryByStatus(String status, User user) { return messageSendDAO.queryByStatus(status,user); }
	@Override
	public MessageSend query(MessageSend messageSend, User user) { return messageSendDAO.query(messageSend,user); }
	@Override
	public MessageSend queryById(String id) { return messageSendDAO.queryById(id); }
	@Override
	public List<MessageSend> queryByPager(Pager pager, User user) { return messageSendDAO.queryByPager(pager,user); }
	@Override
	public int count(User user) { return messageSendDAO.count(user); }
	@Override
	public int inactive(String id) { return messageSendDAO.inactive(id); }
	@Override
	public int active(String id) { return messageSendDAO.active(id); }

	@Override
	public void batchUpdateBySendMsg(String[] idList, String sendMsg) {
		messageSendDAO.batchUpdateBySendMsg(idList, sendMsg);
	}

	@Override
	public void addMessageId(List<MessageSend> msd) {
		messageSendDAO.addMessageId(msd);
	}

	@Override
	public void batchUpdateBySuccess(String[] idList) {
		messageSendDAO.batchUpdateBySuccess(idList);
	}
}