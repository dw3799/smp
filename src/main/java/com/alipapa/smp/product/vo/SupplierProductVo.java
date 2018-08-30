package com.alipapa.smp.product.vo;

import java.util.Date;

public class SupplierProductVo {

    private Long id;

    private Long productId;

    private String productName;

    private Long productCategoryId;

    private String productCategoryName;

    private Long supplierId;

    private String supplierName;

    private String picNo1;

    private String picNo2;

    private String picNo3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
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

    public String getPicNo1() {
        return picNo1;
    }

    public void setPicNo1(String picNo1) {
        this.picNo1 = picNo1;
    }

    public String getPicNo2() {
        return picNo2;
    }

    public void setPicNo2(String picNo2) {
        this.picNo2 = picNo2;
    }

    public String getPicNo3() {
        return picNo3;
    }

    public void setPicNo3(String picNo3) {
        this.picNo3 = picNo3;
    }
}
