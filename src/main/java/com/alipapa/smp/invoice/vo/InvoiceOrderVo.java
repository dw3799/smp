package com.alipapa.smp.invoice.vo;

public class InvoiceOrderVo {


    private Long totalCount;

    private Long id;

    private String orderNo;

    private String invoiceOrderNo;

    private String invoiceStatus;

    private String consumerNo;

    private String consumerName;

    private String consumerCountry;

    private String salerUserNo;

    private String salerUserName;

    private String buyerUserNo;

    private String buyerUserName;

    private String createdTime;


    private String storageUserNo;

    private String storageUserName;

    private String submitTime;

    private String finApvTime;

    private String docTime;

    private String checkOutTime;

    private String deliverType;

    private String transportChannel;


    public String getTransportChannel() {
        return transportChannel;
    }

    public void setTransportChannel(String transportChannel) {
        this.transportChannel = transportChannel;
    }

    public String getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(String deliverType) {
        this.deliverType = deliverType;
    }

    public String getStorageUserNo() {
        return storageUserNo;
    }

    public void setStorageUserNo(String storageUserNo) {
        this.storageUserNo = storageUserNo;
    }

    public String getStorageUserName() {
        return storageUserName;
    }

    public void setStorageUserName(String storageUserName) {
        this.storageUserName = storageUserName;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getFinApvTime() {
        return finApvTime;
    }

    public void setFinApvTime(String finApvTime) {
        this.finApvTime = finApvTime;
    }

    public String getDocTime() {
        return docTime;
    }

    public void setDocTime(String docTime) {
        this.docTime = docTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

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

    public String getInvoiceOrderNo() {
        return invoiceOrderNo;
    }

    public void setInvoiceOrderNo(String invoiceOrderNo) {
        this.invoiceOrderNo = invoiceOrderNo;
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
