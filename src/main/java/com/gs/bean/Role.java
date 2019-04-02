package com.gs.bean;

import java.util.Date;

/**
*
*
*@author qm
*@since 2017-04-14 16:16:21
*/
public class Role{
	private String roleId;
	private String roleName;
	private String roleDes;
	private String roleStatus;

	public String getRoleId(){
		return this.roleId;
	}
	public void setRoleId(String roleId){
		this.roleId=roleId;
	}

	public String getRoleName(){
		return this.roleName;
	}
	public void setRoleName(String roleName){
		this.roleName=roleName;
	}

	public String getRoleDes(){
		return this.roleDes;
	}
	public void setRoleDes(String roleDes){
		this.roleDes=roleDes;
	}

	public String getRoleStatus(){
		return this.roleStatus;
	}
	public void setRoleStatus(String roleStatus){
		this.roleStatus=roleStatus;
	}

}