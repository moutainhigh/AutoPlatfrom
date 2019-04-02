package com.gs.service;

import com.gs.bean.MessageSend;
import com.gs.bean.User;

import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:36:52
*/
public interface MessageSendService extends BaseService<String, MessageSend>{

     void batchUpdateBySendMsg( String[] idList, String sendMsg);

     void addMessageId(List<MessageSend> msd);

     void batchUpdateBySuccess( String[] idList);

}