package com.alipapa.smp.order.vo;

public class MaterielOrderVo {

    private Long materielOrderId;

    private String orderNo;

    private String subOrderNo;

    private Long productCategoryId;

    private String productCategory;

    private Long productId;

    private String productName;

    private Integer materielOrderStatusCode;

    private String materielOrderStatus;

    private Long supplierId;

    private String supplierName;

    private String supplierCharge;

    private String supplierMobile;

    private String supplierBankName;

    private String supplierBankAccount;

    private String supplierBankNo;

    private String purchaseAmount;

    private String purchaseFrontAmount;

    private String restAmount;

    private String remark;

    public String getRestAmount() {
        return restAmount;
    }

    public void setRestAmount(String restAmount) {
        this.restAmount = restAmount;
    }

    public Integer getMaterielOrderStatusCode() {
        return materielOrderStatusCode;
    }

    public void setMaterielOrderStatusCode(Integer materielOrderStatusCode) {
        this.materielOrderStatusCode = materielOrderStatusCode;
    }

    public Long getMaterielOrderId() {
        return materielOrderId;
    }

    public void setMaterielOrderId(Long materielOrderId) {
        this.materielOrderId = materielOrderId;
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
        this.productCategory = productCategory;
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
        this.productName = productName;
    }

    public String getMaterielOrderStatus() {
        return materielOrderStatus;
    }

    public void setMaterielOrderStatus(String materielOrderStatus) {
        this.materielOrderStatus = materielOrderStatus;
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
        this.supplierName = supplierName;
    }

    public String getSupplierCharge() {
        return supplierCharge;
    }

    public void setSupplierCharge(String supplierCharge) {
        this.supplierCharge = supplierCharge;
    }

    public String getSupplierMobile() {
        return supplierMobile;
    }

    public void setSupplierMobile(String supplierMobile) {
        this.supplierMobile = supplierMobile;
    }

    public String getSupplierBankName() {
        return supplierBankName;
    }

    public void setSupplierBankName(String supplierBankName) {
        this.supplierBankName = supplierBankName;
    }

    public String getSupplierBankAccount() {
        return supplierBankAccount;
    }

    public void setSupplierBankAccount(String supplierBankAccount) {
        this.supplierBankAccount = supplierBankAccount;
    }

    public String getSupplierBankNo() {
        return supplierBankNo;
    }

    public void setSupplierBankNo(String supplierBankNo) {
        this.supplierBankNo = supplierBankNo;
    }

    public String getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(String purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public String getPurchaseFrontAmount() {
        return purchaseFrontAmount;
    }

    public void setPurchaseFrontAmount(String purchaseFrontAmount) {
        this.purchaseFrontAmount = purchaseFrontAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
