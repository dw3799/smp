package com.alipapa.smp.invoice.pojo;

import java.util.Date;

public class InvoiceOrder {
    private Long id;

    private String orderNo;

    private String invoiceNo;

    private Integer invoiceStatus;

    private String consumerNo;

    private String consumerName;

    private String consumerCountry;

    private String salerUserNo;

    private String salerUserName;

    private String buyerUserNo;

    private String buyerUserName;

    private String consignee;

    private String mobile;

    private String address;

    private String postalCode;

    private Integer deliverType;

    private Date deliverTime;

    private Date actaulDeliverTime;

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

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo == null ? null : invoiceNo.trim();
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getConsumerNo() {
        return consumerNo;
    }

    public void setConsumerNo(String consumerNo) {
        this.consumerNo = consumerNo == null ? null : consumerNo.trim();
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName == null ? null : consumerName.trim();
    }

    public String getConsumerCountry() {
        return consumerCountry;
    }

    public void setConsumerCountry(String consumerCountry) {
        this.consumerCountry = consumerCountry == null ? null : consumerCountry.trim();
    }

    public String getSalerUserNo() {
        return salerUserNo;
    }

    public void setSalerUserNo(String salerUserNo) {
        this.salerUserNo = salerUserNo == null ? null : salerUserNo.trim();
    }

    public String getSalerUserName() {
        return salerUserName;
    }

    public void setSalerUserName(String salerUserName) {
        this.salerUserName = salerUserName == null ? null : salerUserName.trim();
    }

    public String getBuyerUserNo() {
        return buyerUserNo;
    }

    public void setBuyerUserNo(String buyerUserNo) {
        this.buyerUserNo = buyerUserNo == null ? null : buyerUserNo.trim();
    }

    public String getBuyerUserName() {
        return buyerUserName;
    }

    public void setBuyerUserName(String buyerUserName) {
        this.buyerUserName = buyerUserName == null ? null : buyerUserName.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode == null ? null : postalCode.trim();
    }

    public Integer getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(Integer deliverType) {
        this.deliverType = deliverType;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Date getActaulDeliverTime() {
        return actaulDeliverTime;
    }

    public void setActaulDeliverTime(Date actaulDeliverTime) {
        this.actaulDeliverTime = actaulDeliverTime;
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