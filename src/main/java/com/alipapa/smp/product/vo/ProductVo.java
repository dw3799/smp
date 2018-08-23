package com.alipapa.smp.product.vo;

public class ProductVo {

    private Long productId;

    private String categoryName;

    private String productName;

    private String pic;

    private String newFactoryAmount; //最新单价

    private String mostFactoryAmount; //三月内的最高报价

    private String saleNo;

    private Long totalCount;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getNewFactoryAmount() {
        return newFactoryAmount;
    }

    public void setNewFactoryAmount(String newFactoryAmount) {
        this.newFactoryAmount = newFactoryAmount;
    }

    public String getMostFactoryAmount() {
        return mostFactoryAmount;
    }

    public void setMostFactoryAmount(String mostFactoryAmount) {
        this.mostFactoryAmount = mostFactoryAmount;
    }
}
