package com.alipapa.smp.common.enums;

/**
 * 订单状态枚举
 */
public enum OrderTypeEnum {

    SELF_ORDER(1, "SELF_ORDER", "自营订单"),

    AGENT_ORDER(2, "AGENT_ORDER", "代理订单");

    OrderTypeEnum(int code, String codeName, String dec) {
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

    public static OrderTypeEnum valueOf(int code) {
        OrderTypeEnum[] consumerScopeEnums = OrderTypeEnum.values();
        for (OrderTypeEnum consumerScopeEnum : consumerScopeEnums) {
            if (consumerScopeEnum.getCode() == code) {
                return consumerScopeEnum;
            }
        }
        return null;
    }
}
