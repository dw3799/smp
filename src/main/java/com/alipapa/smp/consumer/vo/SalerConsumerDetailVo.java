package com.alipapa.smp.consumer.vo;

public class SalerConsumerDetailVo {

    private Long consumerId;

    private String consumerNo;

    private String name;

    private String country;

    private String email;

    private String level;

    private String hasOrder;

    private String contactTime;

    private String nextContactTime;

    private Integer isDiscard;//是否可抛弃，管理员及业务主管不可抛弃非本人的客户

    private Integer totalOrder;

    private Long orderAmount;

    private Long totalCount;

/*    consumerId:"2",//客户ID
    consumerNo:"201803160002",//客户编号
    name:"Tony2",//客户名称
    country："中国",//客户国籍
    level："B",//客户等级
    hasOrder:"无订单",
    contactTime:"2018/03/01",//上次联系时间
    nextContactTime:"2018/03/01",//下次联系时间
    totalOrder:"23",//订单总数
    orderAmount:"200万",//订单总额
    totalCount:124//总数，分页用*/


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsDiscard() {
        return isDiscard;
    }

    public void setIsDiscard(Integer isDiscard) {
        this.isDiscard = isDiscard;
    }

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }

    public String getConsumerNo() {
        return consumerNo;
    }

    public void setConsumerNo(String consumerNo) {
        this.consumerNo = consumerNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getHasOrder() {
        return hasOrder;
    }

    public void setHasOrder(String hasOrder) {
        this.hasOrder = hasOrder;
    }

    public String getContactTime() {
        return contactTime;
    }

    public void setContactTime(String contactTime) {
        this.contactTime = contactTime;
    }

    public String getNextContactTime() {
        return nextContactTime;
    }

    public void setNextContactTime(String nextContactTime) {
        this.nextContactTime = nextContactTime;
    }

    public Integer getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Integer totalOrder) {
        this.totalOrder = totalOrder;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}
