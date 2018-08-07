package com.alipapa.smp.order.pojo;

import java.util.Date;

public class OrderWorkFlow {
    private Long id;

    private String orderNo;

    private Integer oldOrderStatus;

    private Integer newOrderStatus;

    private String type;

    private String result;

    private String opUserNo;

    private String opUserName;

    private String opUserRole;

    private String remark;

    private Date createdTime;

    private Date updatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getOldOrderStatus() {
        return oldOrderStatus;
    }

    public void setOldOrderStatus(Integer oldOrderStatus) {
        this.oldOrderStatus = oldOrderStatus;
    }

    public Integer getNewOrderStatus() {
        return newOrderStatus;
    }

    public void setNewOrderStatus(Integer newOrderStatus) {
        this.newOrderStatus = newOrderStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getOpUserNo() {
        return opUserNo;
    }

    public void setOpUserNo(String opUserNo) {
        this.opUserNo = opUserNo == null ? null : opUserNo.trim();
    }

    public String getOpUserName() {
        return opUserName;
    }

    public void setOpUserName(String opUserName) {
        this.opUserName = opUserName == null ? null : opUserName.trim();
    }

    public String getOpUserRole() {
        return opUserRole;
    }

    public void setOpUserRole(String opUserRole) {
        this.opUserRole = opUserRole == null ? null : opUserRole.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}