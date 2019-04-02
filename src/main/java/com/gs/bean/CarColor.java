package com.gs.bean;

import java.util.Date;

/**
*
*
*@author qm
*@since 2017-04-14 16:16:20
*/
public class CarColor {
	private String colorId;
	private String colorName;
	private String colorRGB;
	private String colorHex;
	private String colorDes;
	private String colorStatus;

	public String getColorId(){
		return this.colorId;
	}
	public void setColorId(String colorId){
		this.colorId=colorId;
	}

	public String getColorName(){
		return this.colorName;
	}
	public void setColorName(String colorName){
		this.colorName=colorName;
	}

	public String getColorRGB(){
		return this.colorRGB;
	}
	public void setColorRGB(String colorRGB){
		this.colorRGB=colorRGB;
	}

	public String getColorHex(){
		return this.colorHex;
	}
	public void setColorHex(String colorHex){
		this.colorHex=colorHex;
	}

	public String getColorDes(){
		return this.colorDes;
	}
	public void setColorDes(String colorDes){
		this.colorDes=colorDes;
	}

	public String getColorStatus(){
		return this.colorStatus;
	}
	public void setColorStatus(String colorStatus){
		this.colorStatus=colorStatus;
	}

	@Override
	public String toString() {
		return "CarColor{" +
				"colorId='" + colorId + '\'' +
				", colorName='" + colorName + '\'' +
				", colorRGB='" + colorRGB + '\'' +
				", colorHex='" + colorHex + '\'' +
				", colorDes='" + colorDes + '\'' +
				", colorStatus='" + colorStatus + '\'' +
				'}';
	}
}