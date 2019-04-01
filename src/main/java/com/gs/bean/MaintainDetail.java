package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-24 11:38:22 维修保养明细表
*/
public class MaintainDetail{
	private String detailId; // 明细id
	private String recordId; // 维修保养记录id
	private String maintainId; // 项目id
	private double maintainDiscount; // 维修保养折扣或减价，小于1，则是折扣，大于或等于1则是减价
	private Date detailCreatedTime; // 明细创建时间
	private double price; // 现价

	private double coont;		// 统计维修保养项目记录数

	private MaintainRecord record; // 记录
	private MaintainFix maintain; // 项目

	public String getDetailId(){
		return this.detailId;
	}
	public void setDetailId(String detailId){
		this.detailId=detailId;
	}

	public String getRecordId(){
		return this.recordId;
	}
	public void setRecordId(String recordId){
		this.recordId=recordId;
	}

	public String getMaintainId(){
		return this.maintainId;
	}
	public void setMaintainId(String maintainId){
		this.maintainId=maintainId;
	}

	public double getMaintainDiscount(){
		return this.maintainDiscount;
	}
	public void setMaintainDiscount(double maintainDiscount){
		this.maintainDiscount=maintainDiscount;
	}

	public Date getDetailCreatedTime(){
		return this.detailCreatedTime;
	}
	public void setDetailCreatedTime(Date detailCreatedTime){
		this.detailCreatedTime=detailCreatedTime;
	}

	public MaintainRecord getRecord() {
		return record;
	}

	public void setRecord(MaintainRecord record) {
		this.record = record;
	}

	public MaintainFix getMaintain() {
		return maintain;
	}

	public void setMaintain(MaintainFix maintain) {
		this.maintain = maintain;
	}

	public double getPrice() {
		if (maintainDiscount < 1) {
			if (maintainDiscount == 0) {
				return maintain.getMaintainMoney();
			} else {
				return maintain.getMaintainMoney() * maintainDiscount;
			}
		} else {
			double temp = maintain.getMaintainMoney() - maintainDiscount;
			return temp > 0 ? + temp : 0;
		}
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getCoont() {
		return coont;
	}

	public void setCoont(double coont) {
		this.coont = coont;
	}
}