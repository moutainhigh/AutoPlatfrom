package com.gs.bean;

import java.util.Date;

/**
*
*
*@author qm
*@since 2017-04-14 16:16:21
*/
public class IncomingOutgoing {
	private String inOutId;			// 收支编号
	private String inTypeId;		// 收入类型编号
	private String outTypeId;		//  支出类型编号
	private String inOutCreatedUser; 	// 收支记录创建人
	private double inOutMoney;		//  收支金额
	private Date inOutCreatedTime;		// 收支记录创建时间
	private String inOutStatus;			// 收支记录状态
	private String companyId;			// 所属公司

	private String inOutType;		// 用来区分查询时候是支出还是收入
	/*
	* 关联表
	* */
	private IncomingType incomingType;
	private OutgoingType outgoingType;
	private User user;

	private Company company ;


	public String getInOutId(){
		return this.inOutId;
	}

	public void setInOutId(String inOutId){
		this.inOutId=inOutId;
	}

	public String getInTypeId(){
		return this.inTypeId;
	}

	public void setInTypeId(String inTypeId){
		this.inTypeId=inTypeId;
	}

	public String getOutTypeId(){
		return this.outTypeId;
	}

	public void setOutTypeId(String outTypeId){
		this.outTypeId=outTypeId;
	}

	public double getInOutMoney(){
		return this.inOutMoney;
	}

	public void setInOutMoney(double inOutMoney){
		this.inOutMoney=inOutMoney;
	}

	public String getInOutCreatedUser(){
		return this.inOutCreatedUser;
	}

	public void setInOutCreatedUser(String inOutCreatedUser){
		this.inOutCreatedUser=inOutCreatedUser;
	}

	public Date getInOutCreatedTime(){
		return this.inOutCreatedTime;
	}

	public void setInOutCreatedTime(Date inOutCreatedTime){
		this.inOutCreatedTime=inOutCreatedTime;
	}

	public String getInOutStatus(){
		return this.inOutStatus;
	}

	public void setInOutStatus(String inOutStatus){
		this.inOutStatus=inOutStatus;
	}

	public String getInOutType() {
		return inOutType;
	}

	public void setInOutType(String inOutType) {
		this.inOutType = inOutType;
	}

	public IncomingType getIncomingType() {
		return incomingType;
	}

	public void setIncomingType(IncomingType incomingType) {
		this.incomingType = incomingType;
	}

	public OutgoingType getOutgoingType() {
		return outgoingType;
	}

	public void setOutgoingType(OutgoingType outgoingType) {
		this.outgoingType = outgoingType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}