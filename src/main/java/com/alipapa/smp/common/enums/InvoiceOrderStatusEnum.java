package com.alipapa.smp.common.enums;

/**
 * 发货单状态枚举
 */
public enum InvoiceOrderStatusEnum {

    CREATE(0, "CREATE", "已创建发货单"),

    UN_SUBMIT(1, "UN_SUBMIT", "待提交"),

    FIN_APV(2, "FIN_APV", "待财务审核"),

    DOC_DELIVER_INFO(3, "DOC_DELIVER_INFO", "待单证补充发货信息"),

    OUT_READY(4, "OUT_READY", "待出库"),

    COMPLETE(5, "COMPLETE", "发货单完成"),

    DISCARD(6, "DISCARD", "已废弃");

    InvoiceOrderStatusEnum(int code, String codeName, String dec) {
        this.code = code;
        this.codeName = codeName;
        this.dec = dec;
    }

    private int code;

    private String codeName;

    private String dec;

    public int getCode() {
        return code;
    }

    public String getCodeName() {
        return codeName;
    }

    public String getDec() {
        return dec;
    }

    public static InvoiceOrderStatusEnum valueOf(int code) {
        InvoiceOrderStatusEnum[] consumerScopeEnums = InvoiceOrderStatusEnum.values();
        for (InvoiceOrderStatusEnum consumerScopeEnum : consumerScopeEnums) {
            if (consumerScopeEnum.getCode() == code) {
                return consumerScopeEnum;
            }
        }
        return null;
    }
}
