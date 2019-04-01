package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:20
*/
public class CarModel {
	private String modelId;
	private String modelName;
	private String modelDes;
	private String brandId;
	private String modelStatus;
	private CarBrand brand;

	public CarBrand getBrand() {
		return brand;
	}

	public void setBrand(CarBrand brand) {
		this.brand = brand;
	}

	public String getModelId(){
		return this.modelId;
	}
	public void setModelId(String modelId){
		this.modelId=modelId;
	}

	public String getModelName(){
		return this.modelName;
	}
	public void setModelName(String modelName){
		this.modelName=modelName;
	}

	public String getModelDes(){
		return this.modelDes;
	}
	public void setModelDes(String modelDes){
		this.modelDes=modelDes;
	}

	public String getBrandId(){
		return this.brandId;
	}
	public void setBrandId(String brandId){
		this.brandId=brandId;
	}

	public String getModelStatus(){
		return this.modelStatus;
	}
	public void setModelStatus(String modelStatus){
		this.modelStatus=modelStatus;
	}

	@Override
	public String toString() {
		return "CarModel{" +
				"modelId='" + modelId + '\'' +
				", modelName='" + modelName + '\'' +
				", modelDes='" + modelDes + '\'' +
				", brandId='" + brandId + '\'' +
				", modelStatus='" + modelStatus + '\'' +
				", brand=" + brand +
				'}';
	}
}