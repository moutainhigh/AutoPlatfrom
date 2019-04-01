package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class MaterialUse {
	private String materialUseId;
	private String recordId;
	private String accId;
	private int accCount;
	private Date muCreatedTime;
	private Date muUseDate;
	private Accessories accessories;

	public String getMaterialUseId(){
		return this.materialUseId;
	}
	public void setMaterialUseId(String materialUseId){
		this.materialUseId=materialUseId;
	}

	public String getRecordId(){
		return this.recordId;
	}
	public void setRecordId(String recordId){
		this.recordId=recordId;
	}

	public String getAccId(){
		return this.accId;
	}
	public void setAccId(String accId){
		this.accId=accId;
	}

	public int getAccCount(){
		return this.accCount;
	}
	public void setAccCount(int accCount){
		this.accCount=accCount;
	}

	public Date getMuCreatedTime(){
		return this.muCreatedTime;
	}
	public void setMuCreatedTime(Date muCreatedTime){
		this.muCreatedTime=muCreatedTime;
	}

	public Date getMuUseDate(){
		return this.muUseDate;
	}
	public void setMuUseDate(Date muUseDate){
		this.muUseDate=muUseDate;
	}

	public Accessories getAccessories() {
		return accessories;
	}

	public void setAccessories(Accessories accessories) {
		this.accessories = accessories;
	}
}