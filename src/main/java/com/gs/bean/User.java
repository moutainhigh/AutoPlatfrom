package com.gs.bean;

import java.util.Date;

/**
 *@author 温鑫
 *@since 2017-04-14 16:16:21
 */
public class User{
	private String userId; // 用户编号
	private String userEmail; // 用户邮箱
	private String userPhone; // 用户手机号
	private String userPwd; // 用户登录密码
	private String userNickname; // 用户昵称
	private String userIdentity; // 用户身份证号
	private String userName; // 用户真实姓名
	private String userGender; // 用户性别
	private Date userBirthday; // 用户生日
	private String userAddress; // 用户地址
	private String qqOpenId; // QQ
	private String weiboOpenId; // 微博
	private String wechatOpenId; // 微信
	private String userIcon; // 用户头像
	private String userDes; // 用户描述
	private String companyId; // 用户所属公司
	private double userSalary; // 用户基本工资
	private Date userCreatedTime; // 用户创建时间
	private Date userLoginedTime; // 用户最近一次登录时
	private String userStatus; // 用户状态

	private Company company;
	private UserRole userRole;
	private Role role;

	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getUserEmail(){
		return this.userEmail;
	}
	public void setUserEmail(String userEmail){
		this.userEmail=userEmail;
	}

	public String getUserPhone(){
		return this.userPhone;
	}
	public void setUserPhone(String userPhone){
		this.userPhone=userPhone;
	}

	public String getUserPwd(){
		return this.userPwd;
	}
	public void setUserPwd(String userPwd){
		this.userPwd=userPwd;
	}

	public String getUserNickname(){
		return this.userNickname;
	}
	public void setUserNickname(String userNickname){
		this.userNickname=userNickname;
	}

	public String getUserIdentity(){
		return this.userIdentity;
	}
	public void setUserIdentity(String userIdentity){
		this.userIdentity=userIdentity;
	}

	public String getUserName(){
		return this.userName;
	}
	public void setUserName(String userName){
		this.userName=userName;
	}

	public String getUserGender(){
		return this.userGender;
	}
	public void setUserGender(String userGender){
		this.userGender=userGender;
	}

	public Date getUserBirthday(){
		return this.userBirthday;
	}
	public void setUserBirthday(Date userBirthday){
		this.userBirthday=userBirthday;
	}

	public String getUserAddress(){
		return this.userAddress;
	}
	public void setUserAddress(String userAddress){
		this.userAddress=userAddress;
	}

	public String getQqOpenId(){
		return this.qqOpenId;
	}
	public void setQqOpenId(String qqOpenId){
		this.qqOpenId=qqOpenId;
	}

	public String getWeiboOpenId(){
		return this.weiboOpenId;
	}
	public void setWeiboOpenId(String weiboOpenId){
		this.weiboOpenId=weiboOpenId;
	}

	public String getWechatOpenId(){
		return this.wechatOpenId;
	}
	public void setWechatOpenId(String wechatOpenId){
		this.wechatOpenId=wechatOpenId;
	}

	public String getUserIcon(){
		return this.userIcon;
	}
	public void setUserIcon(String userIcon){
		this.userIcon=userIcon;
	}

	public String getUserDes(){
		return this.userDes;
	}
	public void setUserDes(String userDes){
		this.userDes=userDes;
	}

	public String getCompanyId(){
		return this.companyId;
	}
	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public double getUserSalary(){
		return this.userSalary;
	}
	public void setUserSalary(double userSalary){
		this.userSalary=userSalary;
	}

	public Date getUserCreatedTime(){
		return this.userCreatedTime;
	}
	public void setUserCreatedTime(Date userCreatedTime){
		this.userCreatedTime=userCreatedTime;
	}

	public Date getUserLoginedTime(){
		return this.userLoginedTime;
	}
	public void setUserLoginedTime(Date userLoginedTime){
		this.userLoginedTime=userLoginedTime;
	}

	public String getUserStatus(){
		return this.userStatus;
	}
	public void setUserStatus(String userStatus){
		this.userStatus=userStatus;
	}


	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}