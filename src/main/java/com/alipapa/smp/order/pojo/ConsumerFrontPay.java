package com.alipapa.smp.order.pojo;

import java.util.Date;

public class ConsumerFrontPay {
    private Long id;

    private String orderNo;

    private String consumerNo;

    private String consumerName;

    private String consumerCountry;

    private Long royaltyAmount;

    private Long freightAmount;

    private Long bankFee;

    private Long othersFee;

    private String receiptChannel;

    private String receiptNo;

    private String payChannel;

    private String payNo;

    private Long frontAmount;

    private Long actualAmount;

    private String exchangeRate;

    private Long cnActualAmount;

    private Date payTime;

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

    public Long getRoyaltyAmount() {
        return royaltyAmount;
    }

    public void setRoyaltyAmount(Long royaltyAmount) {
        this.royaltyAmount = royaltyAmount;
    }

    public Long getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(Long freightAmount) {
        this.freightAmount = freightAmount;
    }

    public Long getBankFee() {
        return bankFee;
    }

    public void setBankFee(Long bankFee) {
        this.bankFee = bankFee;
    }

    public Long getOthersFee() {
        return othersFee;
    }

    public void setOthersFee(Long othersFee) {
        this.othersFee = othersFee;
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

    public Long getFrontAmount() {
        return frontAmount;
    }

    public void setFrontAmount(Long frontAmount) {
        this.frontAmount = frontAmount;
    }

    public Long getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Long actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate == null ? null : exchangeRate.trim();
    }

    public Long getCnActualAmount() {
        return cnActualAmount;
    }

    public void setCnActualAmount(Long cnActualAmount) {
        this.cnActualAmount = cnActualAmount;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
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