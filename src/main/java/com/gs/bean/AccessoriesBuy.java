package com.gs.bean;

import java.util.Date;

/**
 * 由Wjhsmart技术支持
 *
 * @author Wjhsmart
 * @since 2017-04
 * 14 16:16:20
 */
public class AccessoriesBuy {
    private String accBuyId; // 购买ID
    private String accUnit; // 配件计量单位
    private int accBuyCount; // 配件购买数量
    private double accBuyPrice; // 配件购买单价
    private double accBuyTotal; // 配件购买总价
    private double accBuyDiscount; // 配件购买折扣
    private double accBuyMoney; // 配件购买最终价
    private Date accBuyTime; // 配件购买时间
    private Date accBuyCreatedTime; // 配件购买记录创建时间
    private String accBuyStatus; // 购买状态
    private String accId; // 配件ID
    private String companyId; // 公司ID
    private String accBuyCheck; // 审核状态
    private String accIsBuy; // 配件是否购买状态

    private double coont;       // 用于下单统计报表记录数

    private Accessories accessories; // 关联配件表
    private Company company; // 关联公司表

    public String getAccIsBuy() {
        return accIsBuy;
    }

    public void setAccIsBuy(String accIsBuy) {
        this.accIsBuy = accIsBuy;
    }

    public String getAccBuyId() {
        return accBuyId;
    }

    public void setAccBuyId(String accBuyId) {
        this.accBuyId = accBuyId;
    }

    public String getAccUnit() {
        return accUnit;
    }

    public void setAccUnit(String accUnit) {
        this.accUnit = accUnit;
    }

    public int getAccBuyCount() {
        return accBuyCount;
    }

    public void setAccBuyCount(int accBuyCount) {
        this.accBuyCount = accBuyCount;
    }

    public double getAccBuyPrice() {
        return accBuyPrice;
    }

    public void setAccBuyPrice(double accBuyPrice) {
        this.accBuyPrice = accBuyPrice;
    }

    public double getAccBuyTotal() {
        return accBuyTotal;
    }

    public void setAccBuyTotal(double accBuyTotal) {
        this.accBuyTotal = accBuyTotal;
    }

    public double getAccBuyDiscount() {
        return accBuyDiscount;
    }

    public void setAccBuyDiscount(double accBuyDiscount) {
        this.accBuyDiscount = accBuyDiscount;
    }

    public double getAccBuyMoney() {
        return accBuyMoney;
    }

    public void setAccBuyMoney(double accBuyMoney) {
        this.accBuyMoney = accBuyMoney;
    }

    public Date getAccBuyTime() {
        return accBuyTime;
    }

    public void setAccBuyTime(Date accBuyTime) {
        this.accBuyTime = accBuyTime;
    }

    public Date getAccBuyCreatedTime() {
        return accBuyCreatedTime;
    }

    public void setAccBuyCreatedTime(Date accBuyCreatedTime) {
        this.accBuyCreatedTime = accBuyCreatedTime;
    }

    public String getAccBuyStatus() {
        return accBuyStatus;
    }

    public void setAccBuyStatus(String accBuyStatus) {
        this.accBuyStatus = accBuyStatus;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAccBuyCheck() {
        return accBuyCheck;
    }

    public void setAccBuyCheck(String accBuyCheck) {
        this.accBuyCheck = accBuyCheck;
    }

    public Accessories getAccessories() {
        return accessories;
    }

    public void setAccessories(Accessories accessories) {
        this.accessories = accessories;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public double getCoont() {
        return coont;
    }

    public void setCoont(double coont) {
        this.coont = coont;
    }

    @Override
    public String toString() {
        return "AccessoriesBuy{" +
                "accBuyId='" + accBuyId + '\'' +
                ", accUnit='" + accUnit + '\'' +
                ", accBuyCount=" + accBuyCount +
                ", accBuyPrice=" + accBuyPrice +
                ", accBuyTotal=" + accBuyTotal +
                ", accBuyDiscount=" + accBuyDiscount +
                ", accBuyMoney=" + accBuyMoney +
                ", accBuyTime=" + accBuyTime +
                ", accBuyCreatedTime=" + accBuyCreatedTime +
                ", accBuyStatus='" + accBuyStatus + '\'' +
                ", accId='" + accId + '\'' +
                ", companyId='" + companyId + '\'' +
                ", accBuyCheck='" + accBuyCheck + '\'' +
                ", accIsBuy='" + accIsBuy + '\'' +
                ", coont=" + coont +
                ", accessories=" + accessories +
                ", company=" + company +
                '}';
    }
}