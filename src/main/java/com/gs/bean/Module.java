package com.gs.bean;

import java.util.Date;

/**
*
*
*@author qm
*@since 2017-04-14 16:16:21
*/
public class Module{
	private String moduleId;
	private String moduleName;
	private String moduleDes;
	private String moduleStatus;

	public String getModuleId(){
		return this.moduleId;
	}
	public void setModuleId(String moduleId){
		this.moduleId=moduleId;
	}

	public String getModuleName(){
		return this.moduleName;
	}
	public void setModuleName(String moduleName){
		this.moduleName=moduleName;
	}

	public String getModuleDes(){
		return this.moduleDes;
	}
	public void setModuleDes(String moduleDes){
		this.moduleDes=moduleDes;
	}

	public String getModuleStatus(){
		return this.moduleStatus;
	}
	public void setModuleStatus(String moduleStatus){
		this.moduleStatus=moduleStatus;
	}

}