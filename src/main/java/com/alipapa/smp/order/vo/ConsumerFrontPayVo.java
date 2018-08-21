package com.alipapa.smp.order.vo;

import java.util.Date;

public class ConsumerFrontPayVo {

    private String orderNo;

    private String royaltyAmount;

    private String freightAmount;

    private String bankFee;

    private String othersFee;

    private String receiptChannel;

    private String receiptNo;

    private String payChannel;

    private String payNo;

    private String frontAmount;

    private String actualAmount;

    private String exchangeRate;

    private String cnActualAmount;

    private String productAmount; //订单产品总金额

    private String orderAmount; //订单总金额

    private String payTime;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRoyaltyAmount() {
        return royaltyAmount;
    }

    public void setRoyaltyAmount(String royaltyAmount) {
        this.royaltyAmount = royaltyAmount;
    }

    public String getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(String freightAmount) {
        this.freightAmount = freightAmount;
    }

    public String getBankFee() {
        return bankFee;
    }

    public void setBankFee(String bankFee) {
        this.bankFee = bankFee;
    }

    public String getOthersFee() {
        return othersFee;
    }

    public void setOthersFee(String othersFee) {
        this.othersFee = othersFee;
    }

    public String getReceiptChannel() {
        return receiptChannel;
    }

    public void setReceiptChannel(String receiptChannel) {
        this.receiptChannel = receiptChannel;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getFrontAmount() {
        return frontAmount;
    }

    public void setFrontAmount(String frontAmount) {
        this.frontAmount = frontAmount;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getCnActualAmount() {
        return cnActualAmount;
    }

    public void setCnActualAmount(String cnActualAmount) {
        this.cnActualAmount = cnActualAmount;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }
}
