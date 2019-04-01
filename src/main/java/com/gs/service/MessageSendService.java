package com.gs.service;

import com.gs.bean.MessageSend;
import com.gs.bean.User;

import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:36:52
*/
public interface MessageSendService extends BaseService<String, MessageSend>{

    public void batchUpdateBySendMsg( String[] idList, String sendMsg);

    public void addMessageId(List<MessageSend> msd);

    public void batchUpdateBySuccess( String[] idList);

}