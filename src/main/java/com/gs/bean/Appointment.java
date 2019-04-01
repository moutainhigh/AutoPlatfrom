package com.gs.bean;

import java.util.Date;

/**
 *由Wjhsmart技术支持
 *
 *@author Wjhsmart
 *@since 2017-04-14 16:16:20
 */
public class Appointment{
	private String appointmentId; //主键 预约编号
	private String userId; //车主编号
	private String userName; //车主名
	private String userPhone; //车主电话

	private String brandId;  //品牌编号
	private String colorId;   //颜色编号
	private String modelId;   //车型编号
	private String plateId;   //车牌编号
	private String carPlate;  //车牌号
	private String companyId;  //公司编号
	private Date arriveTime;  //预估到店时间
	private String maintainOrFix; //维修或保养
	private Date appCreatedTime;  //预约创建时间
	private String appoitmentStatus; //预约状态
	private String speedStatus;//标识这条记录当前的进度，如预约中，登记完

	private CarBrand brand;  //品牌
	private CarColor color;  //颜色
	private CarModel model;  //车型
	private CarPlate plate;  //车牌

	private Company company;//公司

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getColorId() {
		return colorId;
	}

	public void setColorId(String colorId) {
		this.colorId = colorId;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getPlateId() {
		return plateId;
	}

	public void setPlateId(String plateId) {
		this.plateId = plateId;
	}

	public String getCarPlate() {
		return carPlate;
	}

	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Date getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}

	public String getMaintainOrFix() {
		return maintainOrFix;
	}

	public void setMaintainOrFix(String maintainOrFix) {
		this.maintainOrFix = maintainOrFix;
	}

	public Date getAppCreatedTime() {
		return appCreatedTime;
	}

	public void setAppCreatedTime(Date appCreatedTime) {
		this.appCreatedTime = appCreatedTime;
	}

	public String getAppoitmentStatus() {
		return appoitmentStatus;
	}

	public void setAppoitmentStatus(String appoitmentStatus) {
		this.appoitmentStatus = appoitmentStatus;
	}

	public String getSpeedStatus() {
		return speedStatus;
	}

	public void setSpeedStatus(String speedStatus) {
		this.speedStatus = speedStatus;
	}

	public CarBrand getBrand() {
		return brand;
	}

	public void setBrand(CarBrand brand) {
		this.brand = brand;
	}

	public CarColor getColor() {
		return color;
	}

	public void setColor(CarColor color) {
		this.color = color;
	}

	public CarModel getModel() {
		return model;
	}

	public void setModel(CarModel model) {
		this.model = model;
	}

	public CarPlate getPlate() {
		return plate;
	}

	public void setPlate(CarPlate plate) {
		this.plate = plate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}