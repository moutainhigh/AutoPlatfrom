package com.gs.bean;

import java.util.Date;

/**
*由Wjhsmart技术支持
*
*@author Wjhsmart
*@since 2017-04-14 16:16:21
*/
public class UserRole {
	private String userRoleId;
	private String userId;
	private String roleId;
	private Date urCreatedTime;
	private User user;
	private Role role;

	public String getUserRoleId(){
		return this.userRoleId;
	}
	public void setUserRoleId(String userRoleId){
		this.userRoleId=userRoleId;
	}

	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getRoleId(){
		return this.roleId;
	}
	public void setRoleId(String roleId){
		this.roleId=roleId;
	}

	public Date getUrCreatedTime(){
		return this.urCreatedTime;
	}
	public void setUrCreatedTime(Date urCreatedTime){
		this.urCreatedTime=urCreatedTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}