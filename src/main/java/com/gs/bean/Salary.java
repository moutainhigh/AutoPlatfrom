package com.gs.bean;

import java.util.Date;

/**
*
*
*@author qm
*@since 2017-04-14 16:16:21
*/
public class Salary{
	private String salaryId; // 工资发放编号
	private double prizeSalary;  // 奖金
	private double minusSalary;   // 罚金
	private double totalSalary;   // 总工资
	private String salaryDes;	// 工资描述
	private Date salaryTime;    // 发放时间
	private Date salaryCreatedTime; // 创建时间

	private String salaryRange;		// 工资范围，用以查询条件查询

	private String userId;		//

	private User user;



	public String getSalaryId(){
		return this.salaryId;
	}
	public void setSalaryId(String salaryId){
		this.salaryId=salaryId;
	}

	public String getUserId(){
		return this.userId;
	}
	public void setUserId(String userId){
		this.userId=userId;
	}

	public double getPrizeSalary() {
		return prizeSalary;
	}

	public void setPrizeSalary(double prizeSalary) {
		this.prizeSalary = prizeSalary;
	}

	public double getMinusSalary() {
		return minusSalary;
	}

	public void setMinusSalary(double minusSalary) {
		this.minusSalary = minusSalary;
	}

	public double getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(double totalSalary) {
		this.totalSalary = totalSalary;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSalaryDes(){
		return this.salaryDes;
	}
	public void setSalaryDes(String salaryDes){
		this.salaryDes=salaryDes;
	}

	public Date getSalaryTime(){
		return this.salaryTime;
	}
	public void setSalaryTime(Date salaryTime){
		this.salaryTime=salaryTime;
	}

	public Date getSalaryCreatedTime(){
		return this.salaryCreatedTime;
	}
	public void setSalaryCreatedTime(Date salaryCreatedTime){
		this.salaryCreatedTime=salaryCreatedTime;
	}

	public String getSalaryRange() {
		return salaryRange;
	}

	public void setSalaryRange(String salaryRange) {
		this.salaryRange = salaryRange;
	}
}