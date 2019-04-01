package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class MessageSend {
	private String messageId; //短信发送记录
	private String userId;  //用户Id
	private String sendMsg;  //发送内容
	private Date sendTime; //预计发送时间
	private String companyId; //公司Id
	private Date sendCreatedTime; //发送记录创建创建时间
	private Checkin checkin;//用户登记表
	private User user;
	private Company company;
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Checkin getCheckin() {
		return checkin;
	}
	public void setCheckin(Checkin checkin) {
		this.checkin = checkin;
	}


	public String getMessageId(){
		return this.messageId;
	}
	public void setMessageId(String messageId){
		this.messageId=messageId;
	}

	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getSendMsg(){
		return this.sendMsg;
	}
	public void setSendMsg(String sendMsg){
		this.sendMsg=sendMsg;
	}

	public Date getSendTime(){
		return this.sendTime;
	}
	public void setSendTime(Date sendTime){
		this.sendTime=sendTime;
	}

	public Date getSendCreatedTime(){
		return this.sendCreatedTime;
	}
	public void setSendCreatedTime(Date sendCreatedTime){
		this.sendCreatedTime=sendCreatedTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}