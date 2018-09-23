package com.alipapa.smp.invoice.vo;


public class QualityCheckInfoVo {
    private Long id;

    private String subOrderNo;

    private String orderNo;

    private String result;

    private String arrivalTime;

    private Integer checkNumber;

    private Integer badNumber;

    private String printingQuality;

    private String packagingQuality;

    private String suturingQuality;

    private String remark;


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
        this.subOrderNo = subOrderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(Integer checkNumber) {
        this.checkNumber = checkNumber;
    }

    public Integer getBadNumber() {
        return badNumber;
    }

    public void setBadNumber(Integer badNumber) {
        this.badNumber = badNumber;
    }

    public String getPrintingQuality() {
        return printingQuality;
    }

    public void setPrintingQuality(String printingQuality) {
        this.printingQuality = printingQuality;
    }

    public String getPackagingQuality() {
        return packagingQuality;
    }

    public void setPackagingQuality(String packagingQuality) {
        this.packagingQuality = packagingQuality;
    }

    public String getSuturingQuality() {
        return suturingQuality;
    }

    public void setSuturingQuality(String suturingQuality) {
        this.suturingQuality = suturingQuality;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
