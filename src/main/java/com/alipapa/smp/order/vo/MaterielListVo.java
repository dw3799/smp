package com.alipapa.smp.order.vo;

import com.alipapa.smp.order.pojo.MaterielOrder;

import java.util.List;

public class MaterielListVo {

    private String subOrderNo;

    private String totalPurchaseAmount;

    private String purchaseFrontAmount;

    private String payedAmount;

    private List<MaterielOrder> materielOrders;


    public String getSubOrderNo() {
        return subOrderNo;
    }

    public void setSubOrderNo(String subOrderNo) {
        this.subOrderNo = subOrderNo;
    }

    public String getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    public void setTotalPurchaseAmount(String totalPurchaseAmount) {
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public String getPurchaseFrontAmount() {
        return purchaseFrontAmount;
    }

    public void setPurchaseFrontAmount(String purchaseFrontAmount) {
        this.purchaseFrontAmount = purchaseFrontAmount;
    }

    public String getPayedAmount() {
        return payedAmount;
    }

    public void setPayedAmount(String payedAmount) {
        this.payedAmount = payedAmount;
    }

    public List<MaterielOrder> getMaterielOrders() {
        return materielOrders;
    }

    public void setMaterielOrders(List<MaterielOrder> materielOrders) {
        this.materielOrders = materielOrders;
    }
}
