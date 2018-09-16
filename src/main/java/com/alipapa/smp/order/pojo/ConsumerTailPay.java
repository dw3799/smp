package com.alipapa.smp.order.pojo;

import java.util.Date;

public class ConsumerTailPay {
    private Long id;

    private String orderNo;

    private String consumerNo;

    private String consumerName;

    private String consumerCountry;

    private String receiptChannel;

    private String receiptNo;

    private String payChannel;

    private String payNo;

    private Long tailAmount;

    private Long actualTailAmount;

    private String exchangeRate;

    private Long cnActualTailAmount;

    private Date tailPayTime;

    private String remark;

    private Date createdTime;

    private Date updatedTime;

    private Integer payStatus;

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

    public String getReceiptChannel() {
        return receiptChannel;
    }

    public void setReceiptChannel(String receiptChannel) {
        this.receiptChannel = receiptChannel == null ? null : receiptChannel.trim();
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo == null ? null : receiptNo.trim();
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel == null ? null : payChannel.trim();
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo == null ? null : payNo.trim();
    }

    public Long getTailAmount() {
        return tailAmount;
    }

    public void setTailAmount(Long tailAmount) {
        this.tailAmount = tailAmount;
    }

    public Long getActualTailAmount() {
        return actualTailAmount;
    }

    public void setActualTailAmount(Long actualTailAmount) {
        this.actualTailAmount = actualTailAmount;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate == null ? null : exchangeRate.trim();
    }

    public Long getCnActualTailAmount() {
        return cnActualTailAmount;
    }

    public void setCnActualTailAmount(Long cnActualTailAmount) {
        this.cnActualTailAmount = cnActualTailAmount;
    }

    public Date getTailPayTime() {
        return tailPayTime;
    }

    public void setTailPayTime(Date tailPayTime) {
        this.tailPayTime = tailPayTime;
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

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }
}