package com.alipapa.smp.invoice.vo;

import java.util.Date;

public class InvoiceOrderVo {


    private Long totalCount;

    private Long id;

    private String orderNo;

    private String invoiceNo;

    private String invoiceStatus;

    private String consumerNo;

    private String consumerName;

    private String consumerCountry;

    private String salerUserNo;

    private String salerUserName;

    private String buyerUserNo;

    private String buyerUserName;

    private String createdTime;


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
        this.orderNo = orderNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getConsumerNo() {
        return consumerNo;
    }

    public void setConsumerNo(String consumerNo) {
        this.consumerNo = consumerNo;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerCountry() {
        return consumerCountry;
    }

    public void setConsumerCountry(String consumerCountry) {
        this.consumerCountry = consumerCountry;
    }

    public String getSalerUserNo() {
        return salerUserNo;
    }

    public void setSalerUserNo(String salerUserNo) {
        this.salerUserNo = salerUserNo;
    }

    public String getSalerUserName() {
        return salerUserName;
    }

    public void setSalerUserName(String salerUserName) {
        this.salerUserName = salerUserName;
    }

    public String getBuyerUserNo() {
        return buyerUserNo;
    }

    public void setBuyerUserNo(String buyerUserNo) {
        this.buyerUserNo = buyerUserNo;
    }

    public String getBuyerUserName() {
        return buyerUserName;
    }

    public void setBuyerUserName(String buyerUserName) {
        this.buyerUserName = buyerUserName;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}
