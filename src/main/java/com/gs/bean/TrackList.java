package com.gs.bean;

import java.util.Date;

/**
*
*
*@author qm
*@since 2017-04-14 16:16:21  跟踪回访表
*/
public class TrackList {
	private String trackId; //跟踪回访编号Id
	private String userId; //用户编号 user表
	private String trackContent; //回访问题
	private int serviceEvaluate; //本次服务评价 1-10分
	private String trackUser; //跟踪回访用户 来源t_user表
	private Date trackCreatedTime;  //回访时间
	private User admin;//管理员
	private String companyId; //公司Id
	private MaintainRecord maintainRecord; // 维修保养记录表
	private Checkin checkin;//登记表
	private String userPhone; //用户手机号

	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public MaintainRecord getMaintainRecord() {
		return maintainRecord;
	}
	public void setMaintainRecord(MaintainRecord maintainRecord) {
		this.maintainRecord = maintainRecord;
	}

	public Checkin getCheckin() {
		return checkin;
	}
	public void setCheckin(Checkin checkin) {
		this.checkin = checkin;
	}

	public User getAdmin() {
		return admin;
	}
	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public String getTrackId(){
		return this.trackId;
	}
	public void setTrackId(String trackId){
		this.trackId=trackId;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTrackContent(){
		return this.trackContent;
	}
	public void setTrackContent(String trackContent){
		this.trackContent=trackContent;
	}

	public int getServiceEvaluate(){
		return this.serviceEvaluate;
	}
	public void setServiceEvaluate(int serviceEvaluate){
		this.serviceEvaluate=serviceEvaluate;
	}

	public String getTrackUser(){
		return this.trackUser;
	}
	public void setTrackUser(String trackUser){
		this.trackUser=trackUser;
	}

	public Date getTrackCreatedTime(){
		return this.trackCreatedTime;
	}
	public void setTrackCreatedTime(Date trackCreatedTime){
		this.trackCreatedTime=trackCreatedTime;
	}

}