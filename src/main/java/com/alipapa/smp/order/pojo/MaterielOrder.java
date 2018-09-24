package com.alipapa.smp.order.pojo;

import java.util.Date;

public class MaterielOrder {
    private Long id;

    private String orderNo;

    private String subOrderNo;

    private Long productCategoryId;

    private String productCategory;

    private Long productId;

    private String productName;

    private String pic;

    private String miniPic;

    private Integer materielOrderStatus;

    private Integer payStatus;

    private Long supplierId;

    private String supplierName;

    private String supplierCharge;

    private String supplierMobile;

    private String supplierBankName;

    private String supplierBankAccount;

    private String supplierBankNo;

    private Long purchaseAmount;

    private Long purchaseFrontAmount;

    private String remark;

    private Date createdTime;

    private Date updatedTime;

    private Long actualPurchaseAmount;

    private Long actualTailAmount;

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

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory == null ? null : productCategory.trim();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getMiniPic() {
        return miniPic;
    }

    public void setMiniPic(String miniPic) {
        this.miniPic = miniPic == null ? null : miniPic.trim();
    }

    public Integer getMaterielOrderStatus() {
        return materielOrderStatus;
    }

    public void setMaterielOrderStatus(Integer materielOrderStatus) {
        this.materielOrderStatus = materielOrderStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public String getSupplierCharge() {
        return supplierCharge;
    }

    public void setSupplierCharge(String supplierCharge) {
        this.supplierCharge = supplierCharge == null ? null : supplierCharge.trim();
    }

    public String getSupplierMobile() {
        return supplierMobile;
    }

    public void setSupplierMobile(String supplierMobile) {
        this.supplierMobile = supplierMobile == null ? null : supplierMobile.trim();
    }

    public String getSupplierBankName() {
        return supplierBankName;
    }

    public void setSupplierBankName(String supplierBankName) {
        this.supplierBankName = supplierBankName == null ? null : supplierBankName.trim();
    }

    public String getSupplierBankAccount() {
        return supplierBankAccount;
    }

    public void setSupplierBankAccount(String supplierBankAccount) {
        this.supplierBankAccount = supplierBankAccount == null ? null : supplierBankAccount.trim();
    }

    public String getSupplierBankNo() {
        return supplierBankNo;
    }

    public void setSupplierBankNo(String supplierBankNo) {
        this.supplierBankNo = supplierBankNo == null ? null : supplierBankNo.trim();
    }

    public Long getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(Long purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public Long getPurchaseFrontAmount() {
        return purchaseFrontAmount;
    }

    public void setPurchaseFrontAmount(Long purchaseFrontAmount) {
        this.purchaseFrontAmount = purchaseFrontAmount;
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

    public Long getActualPurchaseAmount() {
        return actualPurchaseAmount;
    }

    public void setActualPurchaseAmount(Long actualPurchaseAmount) {
        this.actualPurchaseAmount = actualPurchaseAmount;
    }

    public Long getActualTailAmount() {
        return actualTailAmount;
    }

    public void setActualTailAmount(Long actualTailAmount) {
        this.actualTailAmount = actualTailAmount;
    }
}