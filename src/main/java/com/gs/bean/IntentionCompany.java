package com.gs.bean;

import java.util.Date;

/**
 *
 *
 *@author qm
 *@since 2017-05-17 20:30:41 意向公司表
 */
public class IntentionCompany{
    private String intentionId; // 意向公司的ID
    private String name;	// 意向人名字
    private String email; // 意向人邮箱
    private String phone; // 意向人电话
    private Date createdTime; // 记录创建时间
    private String intentionStatus; // 记录的状态
    private String des; // 记录描述
    private String userId; // 员工的ID，来源于t_user表

    public String getIntentionId(){
        return this.intentionId;
    }
    public void setIntentionId(String intentionId){
        this.intentionId=intentionId;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email=email;
    }

    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }

    public Date getCreatedTime(){
        return this.createdTime;
    }
    public void setCreatedTime(Date createdTime){
        this.createdTime=createdTime;
    }

    public String getDes(){
        return this.des;
    }
    public void setDes(String des){
        this.des=des;
    }

    public String getIntentionStatus() {
        return intentionStatus;
    }

    public void setIntentionStatus(String intentionStatus) {
        this.intentionStatus = intentionStatus;
    }
}