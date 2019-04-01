package com.gs.bean;

import javax.xml.bind.annotation.XmlAccessOrder;
import java.util.Date;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04-14 16:16:20
 */
public class AccessoriesSale {
    private String accSaleId; // 配件销售编号
    private String accId; // 配件编号，来源于t_accessories表
    private Date accSaledTime; // 配件销售时间
    private int accSaleCount; // 配件销售数量
    private double accSalePrice; // 配件销售单价
    private double accSaleTotal; // 配件销售总价
    private double accSaleDiscount; // 配件销售折扣
    private double accSaleMoney; // 配件销售最终价
    private Date accSaleCreatedTime; // 配件销售记录创建时间
    private String companyId; // 配件销售记录所属公司，来源于t_company表
    private String accSaleStatus; // 配件销售记录状态
    private String userId;
    private String userName;
    private String userPhone;

    private Accessories accessories;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Accessories getAccessories() {
        return accessories;
    }

    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }

    public String getAccSaleId() {
        return accSaleId;
    }

    public void setAccSaleId(String accSaleId) {
        this.accSaleId = accSaleId;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public Date getAccSaledTime() {
        return accSaledTime;
    }

    public void setAccSaledTime(Date accSaledTime) {
        this.accSaledTime = accSaledTime;
    }

    public int getAccSaleCount() {
        return accSaleCount;
    }

    public void setAccSaleCount(int accSaleCount) {
        this.accSaleCount = accSaleCount;
    }

    public double getAccSalePrice() {
        return accSalePrice;
    }

    public void setAccSalePrice(double accSalePrice) {
        this.accSalePrice = accSalePrice;
    }

    public double getAccSaleTotal() {
        return accSaleTotal;
    }

    public void setAccSaleTotal(double accSaleTotal) {
        this.accSaleTotal = accSaleTotal;
    }

    public double getAccSaleDiscount() {
        return accSaleDiscount;
    }

    public void setAccSaleDiscount(double accSaleDiscount) {
        this.accSaleDiscount = accSaleDiscount;
    }

    public double getAccSaleMoney() {
        return accSaleMoney;
    }

    public void setAccSaleMoney(double accSaleMoney) {
        this.accSaleMoney = accSaleMoney;
    }

    public Date getAccSaleCreatedTime() {
        return accSaleCreatedTime;
    }

    public void setAccSaleCreatedTime(Date accSaleCreatedTime) {
        this.accSaleCreatedTime = accSaleCreatedTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAccSaleStatus() {
        return accSaleStatus;
    }

    public void setAccSaleStatus(String accSaleStatus) {
        this.accSaleStatus = accSaleStatus;
    }

    @Override
    public String toString() {
        return "AccessoriesSale{" +
                "accSaleId='" + accSaleId + '\'' +
                ", accId='" + accId + '\'' +
                ", accSaledTime=" + accSaledTime +
                ", accSaleCount=" + accSaleCount +
                ", accSalePrice=" + accSalePrice +
                ", accSaleTotal=" + accSaleTotal +
                ", accSaleDiscount=" + accSaleDiscount +
                ", accSaleMoney=" + accSaleMoney +
                ", accSaleCreatedTime=" + accSaleCreatedTime +
                ", companyId='" + companyId + '\'' +
                ", accSaleStatus='" + accSaleStatus + '\'' +
                '}';
    }
}