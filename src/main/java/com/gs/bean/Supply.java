package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class Supply{
	private String supplyId; //供应商编号
	private String supplyName; //供应商名称
	private String supplyTel; //供应商联系电话
	private String supplyPricipal; //供应商负责人
	private String supplyAddress; //供应商地址
	private String supplyBank; //供应商开户银行全称
	private String supplyBankAccount; //供应商开户人姓名
	private String supplyBankNo; //供应商开户卡号
	private String supplyAlipay; //供应商支付宝
	private String supplyWechat; //供应商微信
	private Date supplyCreatedTime; //供应商创建时间
	private String supplyTypeId; //供应商分类编号
	private String companyId; //供应商所属公司
	private String supplyStatus; //供应商状态，Y表示可用，N表示不可用
	private SupplyType supplyType;
	private Company company;

	public String getSupplyId(){
		return this.supplyId;
	}
	public void setSupplyId(String supplyId){
		this.supplyId=supplyId;
	}

	public String getSupplyName(){
		return this.supplyName;
	}
	public void setSupplyName(String supplyName){
		this.supplyName=supplyName;
	}

	public String getSupplyTel(){
		return this.supplyTel;
	}
	public void setSupplyTel(String supplyTel){
		this.supplyTel=supplyTel;
	}

	public String getSupplyPricipal(){
		return this.supplyPricipal;
	}
	public void setSupplyPricipal(String supplyPricipal){
		this.supplyPricipal=supplyPricipal;
	}

	public String getSupplyAddress(){
		return this.supplyAddress;
	}
	public void setSupplyAddress(String supplyAddress){
		this.supplyAddress=supplyAddress;
	}

	public String getSupplyBank(){
		return this.supplyBank;
	}
	public void setSupplyBank(String supplyBank){
		this.supplyBank=supplyBank;
	}

	public String getSupplyBankAccount(){
		return this.supplyBankAccount;
	}
	public void setSupplyBankAccount(String supplyBankAccount){
		this.supplyBankAccount=supplyBankAccount;
	}

	public String getSupplyBankNo(){
		return this.supplyBankNo;
	}
	public void setSupplyBankNo(String supplyBankNo){
		this.supplyBankNo=supplyBankNo;
	}

	public String getSupplyAlipay(){
		return this.supplyAlipay;
	}
	public void setSupplyAlipay(String supplyAlipay){
		this.supplyAlipay=supplyAlipay;
	}

	public String getSupplyWechat(){
		return this.supplyWechat;
	}
	public void setSupplyWechat(String supplyWechat){
		this.supplyWechat=supplyWechat;
	}

	public Date getSupplyCreatedTime(){
		return this.supplyCreatedTime;
	}
	public void setSupplyCreatedTime(Date supplyCreatedTime){
		this.supplyCreatedTime=supplyCreatedTime;
	}

	public String getSupplyTypeId(){
		return this.supplyTypeId;
	}
	public void setSupplyTypeId(String supplyTypeId){
		this.supplyTypeId=supplyTypeId;
	}

	public String getCompanyId(){
		return this.companyId;
	}
	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getSupplyStatus(){
		return this.supplyStatus;
	}
	public void setSupplyStatus(String supplyStatus){
		this.supplyStatus=supplyStatus;
	}

	public SupplyType getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(SupplyType supplyType) {
		this.supplyType = supplyType;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}