package com.gs.bean;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
*
*
*@author qm
*@since 2017-04-14 16:16:21
*/
public class Company implements Serializable{
	private String companyId;//公司编号
	private String companyName;//公司名称
	private String companyAddress;//公司地址
	private String companyTel;//公司联系方式
	private String companyPricipal;//公司负责人
	private String companyPricipalPhone; // 公司负责人电话
	private String companyWebsite;//公司官方网址
	private String companyLogo;//公司头像
	private Date companyOpenDate;//公司创建时间
	private String companySize;//公司规模
	private String companyLongitude;//纬度
	private String companyLatitude;//经度
	private String companyDes;//公司描述
	private String companyStatus;//公司状态
	private String companyImg;//公司图片
	private Timestamp createTime;//创建时间

	public String getCompanyId(){
		return this.companyId;
	}
	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getCompanyName(){
		return this.companyName;
	}
	public void setCompanyName(String companyName){
		this.companyName=companyName;
	}

	public String getCompanyAddress(){
		return this.companyAddress;
	}
	public void setCompanyAddress(String companyAddress){
		this.companyAddress=companyAddress;
	}

	public String getCompanyTel(){
		return this.companyTel;
	}
	public void setCompanyTel(String companyTel){
		this.companyTel=companyTel;
	}

	public String getCompanyPricipal(){
		return this.companyPricipal;
	}
	public void setCompanyPricipal(String companyPricipal){
		this.companyPricipal=companyPricipal;
	}

	public String getCompanyWebsite(){
		return this.companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite){
		this.companyWebsite=companyWebsite;
	}

	public String getCompanyLogo(){
		return this.companyLogo;
	}
	public void setCompanyLogo(String companyLogo){
		this.companyLogo=companyLogo;
	}

	public Date getCompanyOpenDate(){
		return this.companyOpenDate;
	}
	public void setCompanyOpenDate(Date companyOpenDate){
		this.companyOpenDate=companyOpenDate;
	}

	public String getCompanySize(){
		return this.companySize;
	}
	public void setCompanySize(String companySize){
		this.companySize=companySize;
	}

	public String getCompanyLongitude(){
		return this.companyLongitude;
	}
	public void setCompanyLongitude(String companyLongitude){
		this.companyLongitude=companyLongitude;
	}

	public String getCompanyLatitude(){
		return this.companyLatitude;
	}
	public void setCompanyLatitude(String companyLatitude){
		this.companyLatitude=companyLatitude;
	}

	public String getCompanyDes(){
		return this.companyDes;
	}
	public void setCompanyDes(String companyDes){
		this.companyDes=companyDes;
	}

	public String getCompanyStatus(){
		return this.companyStatus;
	}
	public void setCompanyStatus(String companyStatus){
		this.companyStatus=companyStatus;
	}

	public String getCompanyPricipalPhone() {
		return companyPricipalPhone;
	}

	public void setCompanyPricipalPhone(String companyPricipalPhone) {
		this.companyPricipalPhone = companyPricipalPhone;
	}

	public String getCompanyImg() {
		return companyImg;
	}

	public void setCompanyImg(String companyImg) {
		this.companyImg = companyImg;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}