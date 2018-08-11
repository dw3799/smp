package com.alipapa.smp.common.enums;

/**
 * 订单状态枚举
 */
public enum OrderOPerateTypeEnum {

    SAVE(1, "SAVE", "暂存"),

    SUBMIT(2, "SUBMIT", "提交");

    OrderOPerateTypeEnum(int code, String codeName, String dec) {
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

    public static OrderOPerateTypeEnum valueOf(int code) {
        OrderOPerateTypeEnum[] consumerScopeEnums = OrderOPerateTypeEnum.values();
        for (OrderOPerateTypeEnum consumerScopeEnum : consumerScopeEnums) {
            if (consumerScopeEnum.getCode() == code) {
                return consumerScopeEnum;
            }
        }
        return null;
    }
}
