package com.gs.bean;

import java.util.Date;
import java.util.List;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class OutgoingType {
	private String outTypeId; 	// 支出类型编号
	private String outTypeName;  // 支出类型名称
	private String outTypeStatus; // 支出类型状态
	private Date outTypeCreatedTime;	// 支出类型创建时间

	private String companyId;		// 所属公司
	private Company company;

	private List<IncomingOutgoing> incomingOutgoingList;

	public List<IncomingOutgoing> getIncomingOutgoingList() {
		return incomingOutgoingList;
	}

	public void setIncomingOutgoingList(List<IncomingOutgoing> incomingOutgoingList) {
		this.incomingOutgoingList = incomingOutgoingList;
	}

	public String getOutTypeId(){
		return this.outTypeId;
	}
	public void setOutTypeId(String outTypeId){
		this.outTypeId=outTypeId;
	}

	public String getOutTypeName(){
		return this.outTypeName;
	}
	public void setOutTypeName(String outTypeName){
		this.outTypeName=outTypeName;
	}

	public String getOutTypeStatus(){
		return this.outTypeStatus;
	}
	public void setOutTypeStatus(String outTypeStatus){
		this.outTypeStatus=outTypeStatus;
	}

	public Date getOutTypeCreatedTime() {
		return outTypeCreatedTime;
	}

	public void setOutTypeCreatedTime(Date outTypeCreatedTime) {
		this.outTypeCreatedTime = outTypeCreatedTime;
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