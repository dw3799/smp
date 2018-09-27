package com.alipapa.smp.invoice.vo;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class BasicInvoiceOrderInfo {
    private Long id;

    private String orderNo;

    private String invoiceOrderNo;

    private String invoiceStatus;

    private String createdTime;

    private List<JSONObject> invoiceProducts;

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

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public List<JSONObject> getInvoiceProducts() {
        return invoiceProducts;
    }

    public void setInvoiceProducts(List<JSONObject> invoiceProducts) {
        this.invoiceProducts = invoiceProducts;
    }
}
