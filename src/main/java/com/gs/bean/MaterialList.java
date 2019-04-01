package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class MaterialList {
	private String materialId;
	private String recordId;
	private String accId;
	private int materialCount;
	private Date materialCreatedTime;
	private String materialStatus;

	public String getMaterialId(){
		return this.materialId;
	}
	public void setMaterialId(String materialId){
		this.materialId=materialId;
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

	public int getMaterialCount(){
		return this.materialCount;
	}
	public void setMaterialCount(int materialCount){
		this.materialCount=materialCount;
	}

	public Date getMaterialCreatedTime(){
		return this.materialCreatedTime;
	}
	public void setMaterialCreatedTime(Date materialCreatedTime){
		this.materialCreatedTime=materialCreatedTime;
	}

	public String getMaterialStatus(){
		return this.materialStatus;
	}
	public void setMaterialStatus(String materialStatus){
		this.materialStatus=materialStatus;
	}

}