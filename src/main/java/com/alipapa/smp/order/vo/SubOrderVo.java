package com.alipapa.smp.order.vo;

public class SubOrderVo {
    private Long subOrderId;

    private String orderNo;

    private String subOrderNo;

    private String orderType;

    private String productName;

    private String factoryAmount;

    private String expectPurchaseAmount;

    private String productAmount;

    private String consumerName;

    private String salerName;

    private String buyerName;

    private String subOrderStatus;

    private String createdTime;

    private String submitTime;

    private String superApvTime;

    private String finFrontTime;

    private String cashFrontTime;

    private String preFollowTime;

    private String expectFrontAmount;

    private String payedFrontAmount;


    private Long totalCount;

    public String getExpectFrontAmount() {
        return expectFrontAmount;
    }

    public void setExpectFrontAmount(String expectFrontAmount) {
        this.expectFrontAmount = expectFrontAmount;
    }

    public String getPayedFrontAmount() {
        return payedFrontAmount;
    }

    public void setPayedFrontAmount(String payedFrontAmount) {
        this.payedFrontAmount = payedFrontAmount;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getPreFollowTime() {
        return preFollowTime;
    }

    public void setPreFollowTime(String preFollowTime) {
        this.preFollowTime = preFollowTime;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getSuperApvTime() {
        return superApvTime;
    }

    public void setSuperApvTime(String superApvTime) {
        this.superApvTime = superApvTime;
    }

    public String getFinFrontTime() {
        return finFrontTime;
    }

    public void setFinFrontTime(String finFrontTime) {
        this.finFrontTime = finFrontTime;
    }

    public String getCashFrontTime() {
        return cashFrontTime;
    }

    public void setCashFrontTime(String cashFrontTime) {
        this.cashFrontTime = cashFrontTime;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getSubOrderId() {
        return subOrderId;
    }

    public void setSubOrderId(Long subOrderId) {
        this.subOrderId = subOrderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSubOrderNo() {
        return subOrderNo;
    }

    public void setSubOrderNo(String subOrderNo) {
        this.subOrderNo = subOrderNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFactoryAmount() {
        return factoryAmount;
    }

    public void setFactoryAmount(String factoryAmount) {
        this.factoryAmount = factoryAmount;
    }

    public String getExpectPurchaseAmount() {
        return expectPurchaseAmount;
    }

    public void setExpectPurchaseAmount(String expectPurchaseAmount) {
        this.expectPurchaseAmount = expectPurchaseAmount;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public String getSalerName() {
        return salerName;
    }

    public void setSalerName(String salerName) {
        this.salerName = salerName;
    }

    public String getSubOrderStatus() {
        return subOrderStatus;
    }

    public void setSubOrderStatus(String subOrderStatus) {
        this.subOrderStatus = subOrderStatus;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}