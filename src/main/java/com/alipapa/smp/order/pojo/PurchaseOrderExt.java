package com.alipapa.smp.order.pojo;

import java.util.Date;

public class PurchaseOrderExt {
    private Long id;

    private String orderNo;

    private String subOrderNo;

    private Long purchaseFrontAmount;

    private String payChannel;

    private String payNo;

    private Date submitTime;

    private Date superApvTime;

    private Date finFrontTime;

    private Date cashFrontTime;

    private Date finTailTime;

    private Date cashTailTime;

    private Date completeTime;

    private String remark;

    private Integer isDel;

    private Date createdTime;

    private Date updatedTime;

    private Date submitQualityCheckTime;

    private Date completeQualityCheckTime;

    private Date invoiceApplyTime;

    private Date finInvoiceApplyTime;

    private Date docTime;

    private Date outTime;

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

    public String getSubOrderNo() {
        return subOrderNo;
    }

    public void setSubOrderNo(String subOrderNo) {
        this.subOrderNo = subOrderNo == null ? null : subOrderNo.trim();
    }

    public Long getPurchaseFrontAmount() {
        return purchaseFrontAmount;
    }

    public void setPurchaseFrontAmount(Long purchaseFrontAmount) {
        this.purchaseFrontAmount = purchaseFrontAmount;
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

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getSuperApvTime() {
        return superApvTime;
    }

    public void setSuperApvTime(Date superApvTime) {
        this.superApvTime = superApvTime;
    }

    public Date getFinFrontTime() {
        return finFrontTime;
    }

    public void setFinFrontTime(Date finFrontTime) {
        this.finFrontTime = finFrontTime;
    }

    public Date getCashFrontTime() {
        return cashFrontTime;
    }

    public void setCashFrontTime(Date cashFrontTime) {
        this.cashFrontTime = cashFrontTime;
    }

    public Date getFinTailTime() {
        return finTailTime;
    }

    public void setFinTailTime(Date finTailTime) {
        this.finTailTime = finTailTime;
    }

    public Date getCashTailTime() {
        return cashTailTime;
    }

    public void setCashTailTime(Date cashTailTime) {
        this.cashTailTime = cashTailTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
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

    public Date getSubmitQualityCheckTime() {
        return submitQualityCheckTime;
    }

    public void setSubmitQualityCheckTime(Date submitQualityCheckTime) {
        this.submitQualityCheckTime = submitQualityCheckTime;
    }

    public Date getCompleteQualityCheckTime() {
        return completeQualityCheckTime;
    }

    public void setCompleteQualityCheckTime(Date completeQualityCheckTime) {
        this.completeQualityCheckTime = completeQualityCheckTime;
    }

    public Date getInvoiceApplyTime() {
        return invoiceApplyTime;
    }

    public void setInvoiceApplyTime(Date invoiceApplyTime) {
        this.invoiceApplyTime = invoiceApplyTime;
    }

    public Date getFinInvoiceApplyTime() {
        return finInvoiceApplyTime;
    }

    public void setFinInvoiceApplyTime(Date finInvoiceApplyTime) {
        this.finInvoiceApplyTime = finInvoiceApplyTime;
    }

    public Date getDocTime() {
        return docTime;
    }

    public void setDocTime(Date docTime) {
        this.docTime = docTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }
}