package com.gs.bean.info;

import java.util.Date;

/**
 *
 * @author Xiao-Qiang
 * @date 2017/4/26
 */
public class MaterialListInfo {
    private String materialId;
    private int materialCount;
    private Date materialCreatedTime;
    private String materialStatus;
    private String userName;
    private String userRequests;
    private String maintainName;
    private String accId;
    private double accPrice;
    private String accName;

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public int getMaterialCount() {
        return materialCount;
    }

    public void setMaterialCount(int materialCount) {
        this.materialCount = materialCount;
    }

    public Date getMaterialCreatedTime() {
        return materialCreatedTime;
    }

    public void setMaterialCreatedTime(Date materialCreatedTime) {
        this.materialCreatedTime = materialCreatedTime;
    }

    public String getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(String materialStatus) {
        this.materialStatus = materialStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRequests() {
        return userRequests;
    }

    public void setUserRequests(String userRequests) {
        this.userRequests = userRequests;
    }

    public String getMaintainName() {
        return maintainName;
    }

    public void setMaintainName(String maintainName) {
        this.maintainName = maintainName;
    }

    public double getAccPrice() {
        return accPrice;
    }

    public void setAccPrice(double accPrice) {
        this.accPrice = accPrice;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }
}
