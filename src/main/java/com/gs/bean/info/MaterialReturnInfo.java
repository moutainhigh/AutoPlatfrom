package com.gs.bean.info;

import java.util.Date;

/**
 *
 * @author Xiao-Qiang
 * @date 2017/5/16
 */
public class MaterialReturnInfo {

    private String materialReturnId;
    private String recordId;
    private String userName;
    private String userRequests;
    private String empName;
    private String maintainName;
    private String accId;
    private int accCount;
    private double accPrice;
    private String accName;
    private Date mrCreatedTime;
    private String mrReturnDate;

    public String getMaterialReturnId() {
        return materialReturnId;
    }

    public void setMaterialReturnId(String materialReturnId) {
        this.materialReturnId = materialReturnId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
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

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getMaintainName() {
        return maintainName;
    }

    public void setMaintainName(String maintainName) {
        this.maintainName = maintainName;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public int getAccCount() {
        return accCount;
    }

    public void setAccCount(int accCount) {
        this.accCount = accCount;
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

    public Date getMrCreatedTime() {
        return mrCreatedTime;
    }

    public void setMrCreatedTime(Date mrCreatedTime) {
        this.mrCreatedTime = mrCreatedTime;
    }

    public String getMrReturnDate() {
        return mrReturnDate;
    }

    public void setMrReturnDate(String mrReturnDate) {
        this.mrReturnDate = mrReturnDate;
    }
}
