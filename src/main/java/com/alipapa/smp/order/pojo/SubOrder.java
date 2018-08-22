package com.alipapa.smp.order.pojo;

import java.util.Date;

public class SubOrder {
    private Long id;

    private String subOrderNo;

    private String orderNo;

    private Integer subOrderStatus;

    private Integer subPayStatus;

    private Long productCategoryId;

    private String productCategory;

    private Long productId;

    private String productName;

    private String pic;

    private String miniPic;

    private Long productAmount;

    private Long expectPurchaseAmount;

    private Long actualPurchaseAmount;

    private Long productFrontAmount;

    private Long payedAmount;

    private String saleNo;

    private Long saleAmount;

    private Long factoryAmount;

    private String productRemark;

    private String remark;

    private Date createdTime;

    private Date updatedTime;

    // 自营订单明细
    private SelfOrderDetail selfOrderDetail;

    // 代理订单明细
    private AgentOrderDetail agentOrderDetail;

    public SelfOrderDetail getSelfOrderDetail() {
        return selfOrderDetail;
    }

    public void setSelfOrderDetail(SelfOrderDetail selfOrderDetail) {
        this.selfOrderDetail = selfOrderDetail;
    }

    public AgentOrderDetail getAgentOrderDetail() {
        return agentOrderDetail;
    }

    public void setAgentOrderDetail(AgentOrderDetail agentOrderDetail) {
        this.agentOrderDetail = agentOrderDetail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubOrderNo() {
        return subOrderNo;
    }

    public void setSubOrderNo(String subOrderNo) {
        this.subOrderNo = subOrderNo == null ? null : subOrderNo.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public Integer getSubOrderStatus() {
        return subOrderStatus;
    }

    public void setSubOrderStatus(Integer subOrderStatus) {
        this.subOrderStatus = subOrderStatus;
    }

    public Integer getSubPayStatus() {
        return subPayStatus;
    }

    public void setSubPayStatus(Integer subPayStatus) {
        this.subPayStatus = subPayStatus;
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

    public Long getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Long productAmount) {
        this.productAmount = productAmount;
    }

    public Long getExpectPurchaseAmount() {
        return expectPurchaseAmount;
    }

    public void setExpectPurchaseAmount(Long expectPurchaseAmount) {
        this.expectPurchaseAmount = expectPurchaseAmount;
    }

    public Long getActualPurchaseAmount() {
        return actualPurchaseAmount;
    }

    public void setActualPurchaseAmount(Long actualPurchaseAmount) {
        this.actualPurchaseAmount = actualPurchaseAmount;
    }

    public Long getProductFrontAmount() {
        return productFrontAmount;
    }

    public void setProductFrontAmount(Long productFrontAmount) {
        this.productFrontAmount = productFrontAmount;
    }

    public Long getPayedAmount() {
        return payedAmount;
    }

    public void setPayedAmount(Long payedAmount) {
        this.payedAmount = payedAmount;
    }

    public String getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo == null ? null : saleNo.trim();
    }

    public Long getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(Long saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Long getFactoryAmount() {
        return factoryAmount;
    }

    public void setFactoryAmount(Long factoryAmount) {
        this.factoryAmount = factoryAmount;
    }

    public String getProductRemark() {
        return productRemark;
    }

    public void setProductRemark(String productRemark) {
        this.productRemark = productRemark == null ? null : productRemark.trim();
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
}