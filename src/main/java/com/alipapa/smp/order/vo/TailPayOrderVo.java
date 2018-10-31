package com.alipapa.smp.order.vo;

/**
 * 订单摘要信息，页面显示用
 */
public class TailPayOrderVo {

    private String orderNo;

    private String amount; //订单总金额，外币

    private String consumerNo;

    private String consumerName;

    private String consumerCountry;

    private String orderType;

    private String submitDateTime;

    private String orderStatus;

    private String createDateTime;

    private String receiptAmount; //已收款，外币

    private String receiptFrontPay; //已支付定金,外币

    private String receiptTailPay; //已支付尾款，外币

    private String tailPay; //本次支付的尾款，外币

    private String resTailPay; //剩余尾款，外币

    private Long totalCount;

    private String buyerUserName;  //采购员

    public String getBuyerUserName() {
        return buyerUserName;
    }

    public void setBuyerUserName(String buyerUserName) {
        this.buyerUserName = buyerUserName;
    }

    public String getResTailPay() {
        return resTailPay;
    }

    public void setResTailPay(String resTailPay) {
        this.resTailPay = resTailPay;
    }

    public String getReceiptFrontPay() {
        return receiptFrontPay;
    }

    public void setReceiptFrontPay(String receiptFrontPay) {
        this.receiptFrontPay = receiptFrontPay;
    }

    public String getReceiptTailPay() {
        return receiptTailPay;
    }

    public void setReceiptTailPay(String receiptTailPay) {
        this.receiptTailPay = receiptTailPay;
    }

    public String getTailPay() {
        return tailPay;
    }

    public void setTailPay(String tailPay) {
        this.tailPay = tailPay;
    }

    public String getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(String receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public String getConsumerCountry() {
        return consumerCountry;
    }

    public void setConsumerCountry(String consumerCountry) {
        this.consumerCountry = consumerCountry;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getSubmitDateTime() {
        return submitDateTime;
    }

    public void setSubmitDateTime(String submitDateTime) {
        this.submitDateTime = submitDateTime;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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
}
