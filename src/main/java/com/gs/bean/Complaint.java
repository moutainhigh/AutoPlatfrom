package com.gs.bean;

import java.util.Date;

/**
*
*
*@author qm
*@since 2017-04-14 16:16:21  投诉表
*/
public class Complaint{
	private String complaintId; 		//投诉编号
	private String userId;  			//用户编号
	private String complaintContent; 	//投诉内容
	private Date complaintCreatedTime; 	//投诉时间
	private String complaintReply; 		//投诉回复内容
	private Date complaintReplyTime; 	//投诉回复时间
	private String complaintReplyUser;  //投诉回复人
	private String companyId; 			//公司Id
	private User admin; 				//管理员的User
	private User customer;				//顾客的User
	private Company company;		// 投诉公司

	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public User getCustomer() {
		return customer;
	}
	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public User getAdmin() {
		return admin;
	}
	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public String getComplaintId(){
		return this.complaintId;
	}
	public void setComplaintId(String complaintId){
		this.complaintId=complaintId;
	}

	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getComplaintContent(){
		return this.complaintContent;
	}
	public void setComplaintContent(String complaintContent){
		this.complaintContent=complaintContent;
	}

	public Date getComplaintCreatedTime(){
		return this.complaintCreatedTime;
	}
	public void setComplaintCreatedTime(Date complaintCreatedTime){
		this.complaintCreatedTime=complaintCreatedTime;
	}

	public String getComplaintReply(){
		return this.complaintReply;
	}
	public void setComplaintReply(String complaintReply){
		this.complaintReply=complaintReply;
	}

	public Date getComplaintReplyTime(){
		return this.complaintReplyTime;
	}
	public void setComplaintReplyTime(Date complaintReplyTime){
		this.complaintReplyTime=complaintReplyTime;
	}

	public String getComplaintReplyUser(){
		return this.complaintReplyUser;
	}
	public void setComplaintReplyUser(String complaintReplyUser){
		this.complaintReplyUser=complaintReplyUser;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Complaint{" +
				"complaintId='" + complaintId + '\'' +
				", userId='" + userId + '\'' +
				", complaintContent='" + complaintContent + '\'' +
				", complaintCreatedTime=" + complaintCreatedTime +
				", complaintReply='" + complaintReply + '\'' +
				", complaintReplyTime=" + complaintReplyTime +
				", complaintReplyUser='" + complaintReplyUser + '\'' +
				", companyId='" + companyId + '\'' +
				", admin=" + admin +
				", customer=" + customer +
				'}';
	}
}