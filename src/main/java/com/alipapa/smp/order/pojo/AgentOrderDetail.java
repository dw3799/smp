package com.alipapa.smp.order.pojo;

import java.util.Date;

public class AgentOrderDetail {
    private Long id;

    private String subOrderNo;

    private String unit;

    private Integer singlePackageCount;

    private Integer packageNumber;

    private String singleVolume;

    private String singleWeight;

    private String totalVolume;

    private String totalWeight;

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getSinglePackageCount() {
        return singlePackageCount;
    }

    public void setSinglePackageCount(Integer singlePackageCount) {
        this.singlePackageCount = singlePackageCount;
    }

    public Integer getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(Integer packageNumber) {
        this.packageNumber = packageNumber;
    }

    public String getSingleVolume() {
        return singleVolume;
    }

    public void setSingleVolume(String singleVolume) {
        this.singleVolume = singleVolume == null ? null : singleVolume.trim();
    }

    public String getSingleWeight() {
        return singleWeight;
    }

    public void setSingleWeight(String singleWeight) {
        this.singleWeight = singleWeight == null ? null : singleWeight.trim();
    }

    public String getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(String totalVolume) {
        this.totalVolume = totalVolume == null ? null : totalVolume.trim();
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight == null ? null : totalWeight.trim();
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