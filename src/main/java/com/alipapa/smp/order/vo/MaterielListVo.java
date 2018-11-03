package com.alipapa.smp.order.vo;

import com.alipapa.smp.order.pojo.MaterielOrder;

import java.util.List;

public class MaterielListVo {

    private String subOrderNo;

    private String expectPurchaseAmount;

    private String totalPurchaseAmount;

    private String purchaseFrontAmount;

    private String payedAmount;

    private String totalRestAmount;

    private String needRestAmount;

    private String rmbDec;

    public String getRmbDec() {
        return rmbDec;
    }

    public void setRmbDec(String rmbDec) {
        this.rmbDec = rmbDec;
    }

    public String getExpectPurchaseAmount() {
        return expectPurchaseAmount;
    }

    public void setExpectPurchaseAmount(String expectPurchaseAmount) {
        this.expectPurchaseAmount = expectPurchaseAmount;
    }

    public String getNeedRestAmount() {
        return needRestAmount;
    }

    public void setNeedRestAmount(String needRestAmount) {
        this.needRestAmount = needRestAmount;
    }

    public String getTotalRestAmount() {
        return totalRestAmount;
    }

    public void setTotalRestAmount(String totalRestAmount) {
        this.totalRestAmount = totalRestAmount;
    }

    private List<MaterielOrderVo> materielOrders;


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

    public List<MaterielOrderVo> getMaterielOrders() {
        return materielOrders;
    }

    public void setMaterielOrders(List<MaterielOrderVo> materielOrders) {
        this.materielOrders = materielOrders;
    }
}
