package com.alipapa.smp.common.enums;

/**
 * 流转记录枚举
 */
public enum OrderWorkFlowTypeEnum {

    M_ORDER(1, "M_ORDER", "主订单"),

    SUB_ORDER(2, "SUB_ORDER", "产品订单");

    OrderWorkFlowTypeEnum(int code, String codeName, String dec) {
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

    public static OrderWorkFlowTypeEnum valueOf(int code) {
        OrderWorkFlowTypeEnum[] consumerScopeEnums = OrderWorkFlowTypeEnum.values();
        for (OrderWorkFlowTypeEnum consumerScopeEnum : consumerScopeEnums) {
            if (consumerScopeEnum.getCode() == code) {
                return consumerScopeEnum;
            }
        }
        return null;
    }
}
