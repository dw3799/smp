package com.alipapa.smp.invoice.pojo;

import java.util.Date;

public class InvoiceOrderExt {
    private Long id;

    private String orderNo;

    private String invoiceOrderNo;

    private String storageUserNo;

    private String storageUserName;

    private Date submitTime;

    private Date finApvTime;

    private Date docTime;

    private Date checkOutTime;

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

    public String getInvoiceOrderNo() {
        return invoiceOrderNo;
    }

    public void setInvoiceOrderNo(String invoiceOrderNo) {
        this.invoiceOrderNo = invoiceOrderNo == null ? null : invoiceOrderNo.trim();
    }

    public String getStorageUserNo() {
        return storageUserNo;
    }

    public void setStorageUserNo(String storageUserNo) {
        this.storageUserNo = storageUserNo == null ? null : storageUserNo.trim();
    }

    public String getStorageUserName() {
        return storageUserName;
    }

    public void setStorageUserName(String storageUserName) {
        this.storageUserName = storageUserName == null ? null : storageUserName.trim();
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getFinApvTime() {
        return finApvTime;
    }

    public void setFinApvTime(Date finApvTime) {
        this.finApvTime = finApvTime;
    }

    public Date getDocTime() {
        return docTime;
    }

    public void setDocTime(Date docTime) {
        this.docTime = docTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
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