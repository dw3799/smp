package com.alipapa.smp.invoice.pojo;

import java.util.Date;

public class ProductQualityInfo {
    private Long id;

    private String subOrderNo;

    private String orderNo;

    private Integer checkStatus;

    private Date arrivalTime;

    private Integer checkNumber;

    private Integer badNumber;

    private String printingQuality;

    private String packagingQuality;

    private String suturingQuality;

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

    public String getSubOrderNo() {
        return subOrderNo;
    }

    public void setSubOrderNo(String subOrderNo) {
        this.subOrderNo = subOrderNo == null ? null : subOrderNo.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(Integer checkNumber) {
        this.checkNumber = checkNumber;
    }

    public Integer getBadNumber() {
        return badNumber;
    }

    public void setBadNumber(Integer badNumber) {
        this.badNumber = badNumber;
    }

    public String getPrintingQuality() {
        return printingQuality;
    }

    public void setPrintingQuality(String printingQuality) {
        this.printingQuality = printingQuality == null ? null : printingQuality.trim();
    }

    public String getPackagingQuality() {
        return packagingQuality;
    }

    public void setPackagingQuality(String packagingQuality) {
        this.packagingQuality = packagingQuality == null ? null : packagingQuality.trim();
    }

    public String getSuturingQuality() {
        return suturingQuality;
    }

    public void setSuturingQuality(String suturingQuality) {
        this.suturingQuality = suturingQuality == null ? null : suturingQuality.trim();
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