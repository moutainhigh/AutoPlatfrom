package com.gs.bean;

import java.util.Date;

/**
*
*
*@author qm
*@since 2017-04-14 16:16:21
*/
public class MaterialReturn {
	private String materialReturnId;
	private String recordId;
	private String accId;
	private int accCount;
	private Date mrCreatedDate;
	private Date mrReturnDate;

	private Accessories accessories;

	public String getMaterialReturnId(){
		return this.materialReturnId;
	}
	public void setMaterialReturnId(String materialReturnId){
		this.materialReturnId=materialReturnId;
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

	public Date getMrCreatedDate(){
		return this.mrCreatedDate;
	}
	public void setMrCreatedDate(Date mrCreatedDate){
		this.mrCreatedDate=mrCreatedDate;
	}

	public Date getMrReturnDate(){
		return this.mrReturnDate;
	}
	public void setMrReturnDate(Date mrReturnDate){
		this.mrReturnDate=mrReturnDate;
	}

	public Accessories getAccessories() {
		return accessories;
	}

	public void setAccessories(Accessories accessories) {
		this.accessories = accessories;
	}
}