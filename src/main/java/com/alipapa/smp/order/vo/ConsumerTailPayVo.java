package com.alipapa.smp.order.vo;

import java.util.Date;

public class ConsumerTailPayVo {

    private String orderNo;

    private String actualAmount;

    private String orderAmount;

    // ---------存在审核中订单尾款时会返回--------------
    private Long id;

    private String tailAmount;

    private String receiptChannel;

    private String receiptNo;

    private String payChannel;

    private String payNo;


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

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getTailAmount() {
        return tailAmount;
    }

    public void setTailAmount(String tailAmount) {
        this.tailAmount = tailAmount;
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
}