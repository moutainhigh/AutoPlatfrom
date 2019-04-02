package com.gs.bean;

import java.util.Date;

/**
*
*
*@author qm
*@since 2017-04-14 16:16:21
*/
public class CarPlate {
	private String plateId;
	private String plateName;
	private String plateDes;
	private String plateStatus;

	public String getPlateId(){
		return this.plateId;
	}
	public void setPlateId(String plateId){
		this.plateId=plateId;
	}

	public String getPlateName(){
		return this.plateName;
	}
	public void setPlateName(String plateName){
		this.plateName=plateName;
	}

	public String getPlateDes(){
		return this.plateDes;
	}
	public void setPlateDes(String plateDes){
		this.plateDes=plateDes;
	}

	public String getPlateStatus(){
		return this.plateStatus;
	}
	public void setPlateStatus(String plateStatus){
		this.plateStatus=plateStatus;
	}

}