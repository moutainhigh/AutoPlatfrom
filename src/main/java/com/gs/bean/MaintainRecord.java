package com.gs.bean;

import java.util.Date;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:16:21  维修保养记录表
 */
public class MaintainRecord {
    private String recordId;  // 维修保养记录编号
    private String checkinId; // 维修保养登记编号 来源于chechin表
    private Date startTime;  // 维修保养开始时间
    private Date endTime;  // 维修保养预估结束时间
    private Date actualEndTime; // 维修保养实际结束时间
    private Date recordCreatedTime; // 维修保养创建时间
    private Date pickupTime;  // 车主提车时间
    private String recordDes;  // 维修保养记录描述
    private String trackStatus; // 是否回访，默认是N，回访则改为Y
    private String companyId; // 公司的ID
    private String recordStatus;  // 维修保养记录状态
    private String speedStatus; // 标识当前进度
    private String pickingStatus; // 标识这条记录的领料状态
    private String signStatus; // 标识是否用户已经签字
    private double coont;

    private Checkin checkin; // 登记表
    private Company company; // 公司
    private WorkInfo workInfo; // 工单
    private ChargeBill chargeBill; // 收费单据
    private User user; // 人员信息

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Checkin getCheckin() {
        return checkin;
    }

    public void setCheckin(Checkin checkin) {
        this.checkin = checkin;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getCheckinId() {
        return this.checkinId;
    }

    public void setCheckinId(String checkinId) {
        this.checkinId = checkinId;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getActualEndTime() {
        return this.actualEndTime;
    }

    public void setActualEndTime(Date actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public Date getRecordCreatedTime() {
        return this.recordCreatedTime;
    }

    public void setRecordCreatedTime(Date recordCreatedTime) {
        this.recordCreatedTime = recordCreatedTime;
    }

    public Date getPickupTime() {
        return this.pickupTime;
    }

    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getRecordDes() {
        return this.recordDes;
    }

    public void setRecordDes(String recordDes) {
        this.recordDes = recordDes;
    }

    public String getRecordStatus() {
        return this.recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTrackStatus() {
        return trackStatus;
    }

    public void setTrackStatus(String trackStatus) {
        this.trackStatus = trackStatus;
    }

    public String getSpeedStatus() {
        return speedStatus;
    }

    public void setSpeedStatus(String speedStatus) {
        this.speedStatus = speedStatus;
    }

    public double getCoont() {
        return coont;
    }

    public void setCoont(double coont) {
        this.coont = coont;
    }

    public String getPickingStatus() {
        return pickingStatus;
    }

    public void setPickingStatus(String pickingStatus) {
        this.pickingStatus = pickingStatus;
    }

    public WorkInfo getWorkInfo() {
        return workInfo;
    }

    public void setWorkInfo(WorkInfo workInfo) {
        this.workInfo = workInfo;
    }

    public ChargeBill getChargeBill() {
        return chargeBill;
    }

    public void setChargeBill(ChargeBill chargeBill) {
        this.chargeBill = chargeBill;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(String signStatus) {
        this.signStatus = signStatus;
    }
}