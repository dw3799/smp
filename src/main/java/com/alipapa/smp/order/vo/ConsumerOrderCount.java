package com.alipapa.smp.order.vo;


/**
 * 客户订单详情汇总
 */
public class ConsumerOrderCount {

    private String consumerNo;

    private String consumerName;

    private Integer dealOrderCount;

    private String dealOrderAmount;


    public String getConsumerNo() {
        return consumerNo;
    }

    public void setConsumerNo(String consumerNo) {
        this.consumerNo = consumerNo;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public Integer getDealOrderCount() {
        return dealOrderCount;
    }

    public void setDealOrderCount(Integer dealOrderCount) {
        this.dealOrderCount = dealOrderCount;
    }

    public String getDealOrderAmount() {
        return dealOrderAmount;
    }

    public void setDealOrderAmount(String dealOrderAmount) {
        this.dealOrderAmount = dealOrderAmount;
    }
}
