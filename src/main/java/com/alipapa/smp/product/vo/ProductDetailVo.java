package com.alipapa.smp.product.vo;

public class ProductDetailVo {

    private Long productId;

    private Long categoryId;

    private String categoryName;

    private String productName;

    private String picNos;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getPicNos() {
        return picNos;
    }

    public void setPicNos(String picNos) {
        this.picNos = picNos;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
