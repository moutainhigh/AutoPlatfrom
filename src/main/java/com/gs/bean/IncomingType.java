package com.gs.bean;

import java.util.Date;
import java.util.List;

/**
*
*
*@author qm
*@since 2017-04-14 16:16:21
*/
public class IncomingType {
	private String inTypeId;  	// 收入类型编号
	private String inTypeName;	// 收入类型名称
	private String inTypeStatus;	// 收入类型状态
	private Date inTypeCreatedTime;	// 收入类型创建时间
	private String companyId;		// 所属公司
	private Company company;

	private List<IncomingOutgoing> incomingOutgoingList;

	public List<IncomingOutgoing> getIncomingOutgoingList() {
		return incomingOutgoingList;
	}

	public void setIncomingOutgoingList(List<IncomingOutgoing> incomingOutgoingList) {
		this.incomingOutgoingList = incomingOutgoingList;
	}

	public String getInTypeId(){
		return this.inTypeId;
	}
	public void setInTypeId(String inTypeId){
		this.inTypeId=inTypeId;
	}

	public String getInTypeName(){
		return this.inTypeName;
	}
	public void setInTypeName(String inTypeName){
		this.inTypeName=inTypeName;
	}

	public String getInTypeStatus(){
		return this.inTypeStatus;
	}
	public void setInTypeStatus(String inTypeStatus){
		this.inTypeStatus=inTypeStatus;
	}

	public Date getInTypeCreatedTime() {
		return inTypeCreatedTime;
	}

	public void setInTypeCreatedTime(Date inTypeCreatedTime) {
		this.inTypeCreatedTime = inTypeCreatedTime;
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