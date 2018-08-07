package com.alipapa.smp.order.pojo;

import java.util.Date;

public class Order {
    private Long id;

    private String orderNo;

    private Integer orderType;

    private Integer orderStatus;

    private Integer payStatus;

    private String consumerNo;

    private String consumerName;

    private String consumerCountry;

    private String salerUserNo;

    private String salerUserName;

    private String buyerUserNo;

    private String buyerUserName;

    private String productionCycle;

    private String currency;

    private Long productAmount;

    private Long orderAmount;

    private Long expectPurchaseAmount;

    private Long actualPurchaseAmount;

    private Long receiptAmount;

    private Long cnReceiptAmount;

    private String orderVolume;

    private String orderWeight;

    private Date submitTime;

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

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
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

    public String getProductionCycle() {
        return productionCycle;
    }

    public void setProductionCycle(String productionCycle) {
        this.productionCycle = productionCycle == null ? null : productionCycle.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Long getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Long productAmount) {
        this.productAmount = productAmount;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getExpectPurchaseAmount() {
        return expectPurchaseAmount;
    }

    public void setExpectPurchaseAmount(Long expectPurchaseAmount) {
        this.expectPurchaseAmount = expectPurchaseAmount;
    }

    public Long getActualPurchaseAmount() {
        return actualPurchaseAmount;
    }

    public void setActualPurchaseAmount(Long actualPurchaseAmount) {
        this.actualPurchaseAmount = actualPurchaseAmount;
    }

    public Long getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(Long receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public Long getCnReceiptAmount() {
        return cnReceiptAmount;
    }

    public void setCnReceiptAmount(Long cnReceiptAmount) {
        this.cnReceiptAmount = cnReceiptAmount;
    }

    public String getOrderVolume() {
        return orderVolume;
    }

    public void setOrderVolume(String orderVolume) {
        this.orderVolume = orderVolume == null ? null : orderVolume.trim();
    }

    public String getOrderWeight() {
        return orderWeight;
    }

    public void setOrderWeight(String orderWeight) {
        this.orderWeight = orderWeight == null ? null : orderWeight.trim();
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
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