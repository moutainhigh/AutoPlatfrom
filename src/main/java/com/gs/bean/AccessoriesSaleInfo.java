package com.gs.bean;

import java.util.Date;

/**
 * Created by Levc on 2017/5/1.
 */
public class AccessoriesSaleInfo {
    private String accSaleId; // 销售编号
    private String accName; // 配件名称
    private String accTypeName; // 配件类别名称
    private String accBuyPrice; // 配件买入价
    private Double accSaledPrice; // 配件销售价
    private Date accSaleTime; // 配件销售时间
    private String userName; // 配件购买人
    private String userPhone; // 配件购买人联系电话

    public String getAccSaleId() {
        return accSaleId;
    }

    public void setAccSaleId(String accSaleId) {
        this.accSaleId = accSaleId;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getAccTypeName() {
        return accTypeName;
    }

    public void setAccTypeName(String accTypeName) {
        this.accTypeName = accTypeName;
    }

    public String getAccBuyPrice() {
        return accBuyPrice;
    }

    public void setAccBuyPrice(String accBuyPrice) {
        this.accBuyPrice = accBuyPrice;
    }

    public Double getAccSaledPrice() {
        return accSaledPrice;
    }

    public void setAccSaledPrice(Double accSaledPrice) {
        this.accSaledPrice = accSaledPrice;
    }

    public Date getAccSaleTime() {
        return accSaleTime;
    }

    public void setAccSaleTime(Date accSaleTime) {
        this.accSaleTime = accSaleTime;
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
}
