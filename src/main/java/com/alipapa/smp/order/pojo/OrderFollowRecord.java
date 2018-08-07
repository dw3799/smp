package com.alipapa.smp.order.pojo;

import java.util.Date;

public class OrderFollowRecord {
    private Long id;

    private String orderNo;

    private String subOrderNo;

    private String materielOrderNo;

    private String title;

    private Integer sort;

    private String opUserNo;

    private String opUserName;

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

    public String getSubOrderNo() {
        return subOrderNo;
    }

    public void setSubOrderNo(String subOrderNo) {
        this.subOrderNo = subOrderNo == null ? null : subOrderNo.trim();
    }

    public String getMaterielOrderNo() {
        return materielOrderNo;
    }

    public void setMaterielOrderNo(String materielOrderNo) {
        this.materielOrderNo = materielOrderNo == null ? null : materielOrderNo.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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