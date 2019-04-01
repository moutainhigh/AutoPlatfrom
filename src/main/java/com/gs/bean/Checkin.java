package com.gs.bean;

import com.gs.common.util.DateParseUtil;

import java.util.Date;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:16:21 维修保养登记表
 */
public class Checkin {
    private String checkinId; // 登记ID
    private String userId; // 车主
    private String appointmentId; // 预约ID
    private String userName; // 车主姓名
    private String userPhone; // 车主电话
    private String brandId; // 品牌ID
    private String modelId; // 车型ID
    private String plateId; // 车牌ID
    private String colorId; // 颜色的ID
    private String carPlate; // 车牌号码
    private Date arriveTime; // 到店时间
    private double carMileage; // 汽车行驶里程
    private String carThings; // 车上物品描述信息
    private String intactDegrees; // 汽车完好度描述
    private String userRequests; // 车主要求描述
    private String maintainOrFix; // 标识，标识是维修该是保养
    private Date checkinCreatedTime; // 记录创建时间
    private String checkinStatus; // 记录的状态
    private String carWash; // 标识是否需要洗车
    private String companyId; // 公司ID
    private double oilCount; // 油量

    private CarBrand brand; // 汽车品牌
    private CarColor color; // 汽车颜色
    private CarModel model; // 汽车车型
    private CarPlate plate; // 汽车车牌

    private Company company; // 汽修公司

    public String getCheckinId() {
        return this.checkinId;
    }

    public void setCheckinId(String checkinId) {
        this.checkinId = checkinId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppointmentId() {
        return this.appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return this.userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getCarPlate() {
        return this.carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public double getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(double carMileage) {
        this.carMileage = carMileage;
    }

    public String getCarThings() {
        return this.carThings;
    }

    public void setCarThings(String carThings) {
        this.carThings = carThings;
    }

    public String getIntactDegrees() {
        return this.intactDegrees;
    }

    public void setIntactDegrees(String intactDegrees) {
        this.intactDegrees = intactDegrees;
    }

    public String getUserRequests() {
        return this.userRequests;
    }

    public void setUserRequests(String userRequests) {
        this.userRequests = userRequests;
    }

    public String getMaintainOrFix() {
        return this.maintainOrFix;
    }

    public void setMaintainOrFix(String maintainOrFix) {
        this.maintainOrFix = maintainOrFix;
    }

    public Date getCheckinCreatedTime() {
        return this.checkinCreatedTime;
    }

    public void setCheckinCreatedTime(Date checkinCreatedTime) {
        this.checkinCreatedTime = checkinCreatedTime;
    }

    public String getCheckinStatus() {
        return this.checkinStatus;
    }

    public void setCheckinStatus(String checkinStatus) {
        this.checkinStatus = checkinStatus;
    }

    public String getCarWash() {
        return carWash;
    }

    public void setCarWash(String carWash) {
        this.carWash = carWash;
    }

    public double getOilCount() {
        return oilCount;
    }

    public void setOilCount(double oilCount) {
        this.oilCount = oilCount;
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

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
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

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

}