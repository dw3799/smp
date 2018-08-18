package com.alipapa.smp.order.vo;

import java.util.Date;

public class OrderWorkFlowVo {

    private String orderNo;

    private String createdTime;

    private String opUserRole;

    private String opUserName;

    private String result;

    private String remark;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getOpUserRole() {
        return opUserRole;
    }

    public void setOpUserRole(String opUserRole) {
        this.opUserRole = opUserRole;
    }

    public String getOpUserName() {
        return opUserName;
    }

    public void setOpUserName(String opUserName) {
        this.opUserName = opUserName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
