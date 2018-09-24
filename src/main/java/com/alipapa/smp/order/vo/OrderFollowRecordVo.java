package com.alipapa.smp.order.vo;

public class OrderFollowRecordVo {

    private Long materielOrderId;

    private String materielName;

    private String materielOrderStatus;

    private String factoryOrderedTime;

    private String factoryMaterielTime;

    private String factoryPrintingTime;

    private String factoryPackageTime;


    public String getMaterielName() {
        return materielName;
    }

    public void setMaterielName(String materielName) {
        this.materielName = materielName;
    }

    public Long getMaterielOrderId() {
        return materielOrderId;
    }

    public void setMaterielOrderId(Long materielOrderId) {
        this.materielOrderId = materielOrderId;
    }

    public String getMaterielOrderStatus() {
        return materielOrderStatus;
    }

    public void setMaterielOrderStatus(String materielOrderStatus) {
        this.materielOrderStatus = materielOrderStatus;
    }

    public String getFactoryOrderedTime() {
        return factoryOrderedTime;
    }

    public void setFactoryOrderedTime(String factoryOrderedTime) {
        this.factoryOrderedTime = factoryOrderedTime;
    }

    public String getFactoryMaterielTime() {
        return factoryMaterielTime;
    }

    public void setFactoryMaterielTime(String factoryMaterielTime) {
        this.factoryMaterielTime = factoryMaterielTime;
    }

    public String getFactoryPrintingTime() {
        return factoryPrintingTime;
    }

    public void setFactoryPrintingTime(String factoryPrintingTime) {
        this.factoryPrintingTime = factoryPrintingTime;
    }

    public String getFactoryPackageTime() {
        return factoryPackageTime;
    }

    public void setFactoryPackageTime(String factoryPackageTime) {
        this.factoryPackageTime = factoryPackageTime;
    }
}
