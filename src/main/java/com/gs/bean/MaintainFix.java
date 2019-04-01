package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21 维修保养项目表
*/
public class MaintainFix {
	private String maintainId; //保养项目Id
	private String maintainName; //保养项目名称
	private double maintainHour; //保养工时
	private double maintainMoney; //保养项目基础费用
	private double maintainManHourFee; //保养项目工时费
	private String maintainOrFix; //标识保养还是维修
	private String maintainDes; //维修保养描述
	private String companyId; //维修保养所属Id t_company
	private String maintainStatus; //维修保养状态 Y N

	private Company company;

	public double getMaintainHour() {
		return maintainHour;
	}

	public void setMaintainHour(double maintainHour) {
		this.maintainHour = maintainHour;
	}

	public double getMaintainMoney() {
		return maintainMoney;
	}

	public void setMaintainMoney(double maintainMoney) {
		this.maintainMoney = maintainMoney;
	}

	public double getMaintainManHourFee() {
		return maintainManHourFee;
	}

	public void setMaintainManHourFee(double maintainManHourFee) {
		this.maintainManHourFee = maintainManHourFee;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getMaintainId(){
		return this.maintainId;
	}
	public void setMaintainId(String maintainId){
		this.maintainId=maintainId;
	}

	public String getMaintainName(){
		return this.maintainName;
	}
	public void setMaintainName(String maintainName){
		this.maintainName=maintainName;
	}

	public String getMaintainOrFix(){
		return this.maintainOrFix;
	}
	public void setMaintainOrFix(String maintainOrFix){
		this.maintainOrFix=maintainOrFix;
	}

	public String getMaintainDes(){
		return this.maintainDes;
	}
	public void setMaintainDes(String maintainDes){
		this.maintainDes=maintainDes;
	}

	public String getCompanyId(){
		return this.companyId;
	}
	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getMaintainStatus(){
		return this.maintainStatus;
	}
	public void setMaintainStatus(String maintainStatus){
		this.maintainStatus=maintainStatus;
	}

	@Override
	public String toString() {
		return "MaintainFix{" +
				"maintainId='" + maintainId + '\'' +
				", maintainName='" + maintainName + '\'' +
				", maintainHour=" + maintainHour +
				", maintainMoney=" + maintainMoney +
				", maintainManHourFee=" + maintainManHourFee +
				", maintainOrFix='" + maintainOrFix + '\'' +
				", maintainDes='" + maintainDes + '\'' +
				", companyId='" + companyId + '\'' +
				", maintainStatus='" + maintainStatus + '\'' +
				", company=" + company +
				'}';
	}
}