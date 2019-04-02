package com.gs.bean;

import java.util.Date;

/**
*
*
*@author qm
*@since 2017-04-14 16:16:21
*/
public class RolePermission {
	private String rpId;
	private String roleId;
	private String permissionId;
	private Date rpCreatedTime;

	public String getRpId(){
		return this.rpId;
	}
	public void setRpId(String rpId){
		this.rpId=rpId;
	}

	public String getRoleId(){
		return this.roleId;
	}
	public void setRoleId(String roleId){
		this.roleId=roleId;
	}

	public String getPermissionId(){
		return this.permissionId;
	}
	public void setPermissionId(String permissionId){
		this.permissionId=permissionId;
	}

	public Date getRpCreatedTime(){
		return this.rpCreatedTime;
	}
	public void setRpCreatedTime(Date rpCreatedTime){
		this.rpCreatedTime=rpCreatedTime;
	}

}