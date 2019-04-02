package com.gs.bean;

import java.util.Date;

/**
*
*
*@author qm
*@since 2017-04-14 16:16:21 收费单据bean
*/
public class ChargeBill {
	private String chargeBillId; // 收费单据的id
	private String recordId; // 维修保养记录的ID
	private double chargeBillMoney; // 收费总金额
	private String paymentMethod; // 付款方式
	private double actualPayment; // 实付款
	private Date chargeTime; // 收款时间
	private Date chargeCreatedTime; // 收费单据创建时间
	private String companyId; // 所属公司的id
	private String chargeBillDes; // 收费单据描述
	private String chargeBillStatus; // 收费单据状态

	private MaintainRecord record;
	private Company company;

	public String getChargeBillId(){
		return this.chargeBillId;
	}
	public void setChargeBillId(String chargeBillId){
		this.chargeBillId=chargeBillId;
	}

	public String getPaymentMethod(){
		return this.paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod){
		this.paymentMethod=paymentMethod;
	}

	public Date getChargeTime(){
		return this.chargeTime;
	}
	public void setChargeTime(Date chargeTime){
		this.chargeTime=chargeTime;
	}

	public Date getChargeCreatedTime(){
		return this.chargeCreatedTime;
	}
	public void setChargeCreatedTime(Date chargeCreatedTime){
		this.chargeCreatedTime=chargeCreatedTime;
	}

	public String getChargeBillDes(){
		return this.chargeBillDes;
	}
	public void setChargeBillDes(String chargeBillDes){
		this.chargeBillDes=chargeBillDes;
	}

	public String getChargeBillStatus(){
		return this.chargeBillStatus;
	}
	public void setChargeBillStatus(String chargeBillStatus){
		this.chargeBillStatus=chargeBillStatus;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public MaintainRecord getRecord() {
		return record;
	}

	public void setRecord(MaintainRecord record) {
		this.record = record;
	}

	public double getChargeBillMoney() {
		return chargeBillMoney;
	}

	public void setChargeBillMoney(double chargeBillMoney) {
		this.chargeBillMoney = chargeBillMoney;
	}

	public double getActualPayment() {
		return actualPayment;
	}

	public void setActualPayment(double actualPayment) {
		this.actualPayment = actualPayment;
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