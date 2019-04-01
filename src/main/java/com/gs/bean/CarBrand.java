package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:20
*/
public class CarBrand {
	private String brandId;
	private String brandName;
	private String brandDes;
	private String brandStatus;

	public String getBrandId(){
		return this.brandId;
	}
	public void setBrandId(String brandId){
		this.brandId=brandId;
	}

	public String getBrandName(){
		return this.brandName;
	}
	public void setBrandName(String brandName){
		this.brandName=brandName;
	}

	public String getBrandDes(){
		return this.brandDes;
	}
	public void setBrandDes(String brandDes){
		this.brandDes=brandDes;
	}

	@Override
	public String toString() {
		return "CarBrand{" +
				"brandId='" + brandId + '\'' +
				", brandName='" + brandName + '\'' +
				", brandDes='" + brandDes + '\'' +
				", brandStatus='" + brandStatus + '\'' +
				'}';
	}

	public String getBrandStatus(){
		return this.brandStatus;
	}
	public void setBrandStatus(String brandStatus){
		this.brandStatus=brandStatus;
	}



}