package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class WorkInfo {
	private String workId;
	private String recordId;
	private String userId;
	private Date workAssignTime;
	private Date workCreatedTime;
	private String workStatus;
	private double coont; // 用于员工工单统计报表记录数
	private User user;
	private MaintainRecord maintainRecord;
	private Checkin checkin;
	private Company company;

	public String getWorkId(){
		return this.workId;
	}
	public void setWorkId(String workId){
		this.workId=workId;
	}

	public String getRecordId(){
		return this.recordId;
	}
	public void setRecordId(String recordId){
		this.recordId=recordId;
	}

	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}

	public Date getWorkAssignTime(){
		return this.workAssignTime;
	}
	public void setWorkAssignTime(Date workAssignTime){
		this.workAssignTime=workAssignTime;
	}

	public Date getWorkCreatedTime(){
		return this.workCreatedTime;
	}
	public void setWorkCreatedTime(Date workCreatedTime){
		this.workCreatedTime=workCreatedTime;
	}

	public String getWorkStatus(){
		return this.workStatus;
	}
	public void setWorkStatus(String workStatus){
		this.workStatus=workStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public double getCoont() {
		return coont;
	}

	public void setCoont(double coont) {
		this.coont = coont;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}